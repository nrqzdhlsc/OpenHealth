import torch
import torch.nn as nn
import torch.nn.functional as F
import torch.optim as optim
import logging
import argparse
import sys
import numpy as np

import syft as sy
from syft.workers import WebsocketClientWorker
from syft.workers import VirtualWorker
from syft.frameworks.torch.federated import utils
from syft.frameworks.torch.federated.dataset import FederatedDataset, BaseDataset
from torchvision import datasets, transforms, models

import resnet

logger = logging.getLogger(__name__)

LOG_INTERVAL = 25

def fl_get_next_batches(fdataloader: sy.FederatedDataLoader, nr_batches: int):
    """retrieve next nr_batches of the federated data loader and group the batches by worker

    Args:
        fdataloader (sy.FederatedDataLoader): federated data loader over which the function will iterate
        nr_batches (int): number of batches (per worker) to retrieve

    Returns:
        Dict[syft.workers.BaseWorker, List[batches]]

    """
    batches = {}
    for worker_id in fdataloader.workers:
        worker = fdataloader.federated_dataset.datasets[worker_id].location
        batches[worker] = []
    try:
        for i in range(nr_batches):
            next_batches = next(fdataloader)
            for worker in next_batches:
                batches[worker].append(next_batches[worker])
    except StopIteration:
        pass
    return batches

def fl_train_on_batches(worker, batches, model_in, device, lr):
    """Train the model on the worker on the provided batches

    Args:
        worker(syft.workers.BaseWorker): worker on which the training will be executed
        batches: batches of data of this worker
        model_in: machine learning model, training will be done on a copy
        device (torch.device): where to run the training
        lr: learning rate of the training steps

    Returns:
        model, loss: obtained model and loss after training

    """
    model = model_in.copy()
    optimizer = optim.Adam(model.parameters(), lr=lr)  # TODO momentum is not supported at the moment
    scheduler = optim.lr_scheduler.StepLR(optimizer, 4)
    criterion = nn.NLLLoss()

    logger.debug(
                "    fl on worker: {}".format(
                    worker.id,
                )
            )

    scheduler.step()
    model.train()
    model.send(worker)
    
    logger.debug(
                "     sending model graph to worker: {}".format(
                    worker.id,
                )
            )

    loss_local = False

    for batch_idx, (data, target) in enumerate(batches):

        loss_local = False

        data, target = data.to(device), target.to(device)

        optimizer.zero_grad()

        output = model(data)
        # _, preds = torch.max(output, -1)
        # print(preds.get())

        loss = criterion(output, target)

        loss.backward()
        optimizer.step()
        if batch_idx % LOG_INTERVAL == 0:
            loss = loss.get()  # <-- NEW: get the loss back
            loss_local = True
            logger.debug(
                "     remote training: [{}/{} ({:.0f}%)]\tLoss: {:.6f}".format(
                    batch_idx,
                    len(batches),
                    100.0 * batch_idx / len(batches),
                    loss.item(),
                )
            )
    if not loss_local:
        loss = loss.get()  # <-- NEW: get the loss back
    model.get()  # <-- NEW: get the model back

    logger.debug(
                "     getting trained model from worker: {}".format(
                    worker.id,
                )
            )


    return model, loss


def fl_train(model, device, federated_train_loader, lr, federate_after_n_batches):
    model.train()

    nr_batches = federate_after_n_batches

    models = {}
    loss_values = {}

    iter(federated_train_loader)  # initialize iterators
    batches = fl_get_next_batches(federated_train_loader, nr_batches)
    counter = 0

    while True:
        logger.debug("  starting training round, batches [%s, %s]", counter, counter + nr_batches)
        data_for_all_workers = True
        for worker in batches:
            curr_batches = batches[worker]
            if curr_batches:
                models[worker], loss_values[worker] = fl_train_on_batches(
                    worker, curr_batches, model, device, lr
                )
            else:
                data_for_all_workers = False
        counter += nr_batches
        if not data_for_all_workers:
            logger.debug("At least one worker ran out of data, stopping.")
            break

        model = utils.federated_avg(models)
        batches = fl_get_next_batches(federated_train_loader, nr_batches)
    return model


def local_test(model, device, test_loader):
    model.eval()
    correct = 0
    with torch.no_grad():
        for data, target in test_loader:
            data, target = data.to(device), target.to(device)
            output = model(data)

            output = np.round(np.squeeze(output))  # >0.5 pneumonia  <=0.5 normal
            output = output.type(torch.IntTensor)
            target = target.type(torch.IntTensor)

            correct += output.eq(target).sum().item()

            break

    logger.info("\n")
    accuracy = 100.0 * correct / len(test_loader.dataset)
    logger.info(
        "Test set: Accuracy: {}/{} ({:.0f}%)\n".format(
            correct, len(test_loader.dataset), accuracy
        )
    )

def define_and_get_arguments(args=sys.argv[1:]):
    parser = argparse.ArgumentParser(
        description="Run federated learning using websocket client workers."
    )
    parser.add_argument("--batch_size", type=int, default=15, help="batch size of the training")
    parser.add_argument(
        "--test_batch_size", type=int, default=100, help="batch size used for the test data"
    )
    parser.add_argument("--epochs", type=int, default=20, help="number of epochs to train")
    parser.add_argument(
        "--federate_after_n_batches",
        type=int,
        default=1,
        help="number of training steps performed on each remote worker before averaging",
    )

    parser.add_argument("--lr", type=float, default=0.001, help="learning rate")
    parser.add_argument("--cuda", action="store_true", help="use cuda")
    parser.add_argument("--seed", type=int, default=1, help="seed used for randomization")
    parser.add_argument("--save_model", action="store_true", help="if set, model will be saved")
    parser.add_argument(
        "--verbose",
        "-v",
        action="store_true",
        help="if set, websocket client workers will be started in verbose mode",
    )
    parser.add_argument(
        "--use_virtual", action="store_true", help="if set, virtual workers will be used"
    )

    args = parser.parse_args(args=args)
    return args


def main():
    args = define_and_get_arguments()

    hook = sy.TorchHook(torch)

    host = "localhost"

    if args.use_virtual:
        alice = VirtualWorker(id="hospital_a", hook=hook, verbose=args.verbose)
        bob = VirtualWorker(id="hospital_b", hook=hook, verbose=args.verbose)
        charlie = VirtualWorker(id="hospital_c", hook=hook, verbose=args.verbose)
    else:
        kwargs_websocket = {"host": host, "hook": hook, "verbose": args.verbose}
        hospital_a = WebsocketClientWorker(id="hospital_a", port=8777, **kwargs_websocket)
        hospital_b = WebsocketClientWorker(id="hospital_b", port=8778, **kwargs_websocket)
        hospital_c = WebsocketClientWorker(id="hospital_c", port=8779, **kwargs_websocket)

        print()
        print("*******************************************************************************************************")
        print("building training channels ...")
        print(" #hospital_a, remote tensor reference: ", hospital_a)
        print(" #hospital_b, remote tensor reference: ", hospital_b)
        print(" #hospital_c, remote tensor reference: ", hospital_c)
        print()

    workers = [hospital_a,hospital_b,hospital_c]

    use_cuda = args.cuda and torch.cuda.is_available()

    torch.manual_seed(args.seed)

    device = torch.device("cuda" if use_cuda else "cpu")

    kwargs = {"num_workers": 1, "pin_memory": True} if use_cuda else {}

    # Search multiple times should still work
    tr_hospital_a = hospital_a.search("#chest_xray", "#hospital_a", "#train_tag")
    tr_hospital_b = hospital_b.search("#chest_xray", "#hospital_b", "#train_tag")
    tr_hospital_c = hospital_c.search("#chest_xray", "#hospital_c", "#train_tag")

    base_data = []
    base_data.append(BaseDataset(tr_hospital_a[0], tr_hospital_a[1]))
    base_data.append(BaseDataset(tr_hospital_b[0], tr_hospital_b[1]))
    base_data.append(BaseDataset(tr_hospital_c[0], tr_hospital_c[1]))


    federated_train_loader = sy.FederatedDataLoader(
        FederatedDataset(base_data),
        batch_size=args.batch_size,
        shuffle=True,
        iter_per_worker=True,
        **kwargs,
    )

    data_transforms = transforms.Compose([
        transforms.Resize(224),
        transforms.CenterCrop(224),
        transforms.RandomRotation(20),
        transforms.RandomHorizontalFlip(),
        transforms.ToTensor(),
        transforms.Normalize([0.5,0.5,0.5],[0.5,0.5,0.5])
    ])
    test = datasets.ImageFolder('chest_xray/small', data_transforms)

    local_test_loader = torch.utils.data.DataLoader(test, 
        batch_size=args.test_batch_size, shuffle=True, **kwargs)

    model = resnet.resnet18_simple()
    # print("*******************************************************************************************************")
    # print("model architecture")
    # print(model)
    # print()

    print("*******************************************************************************************************")
    print("starting federated learning ...")
    for epoch in range(1, args.epochs + 1):
        logger.info(" starting fl training epoch %s/%s", epoch, args.epochs)
        model = fl_train(model, device, federated_train_loader, args.lr, args.federate_after_n_batches)
        
        logger.info(" starting local inference")
        local_test(model, device, local_test_loader)

    if args.save_model:
        torch.save(model.state_dict(), "./log/chest_xray_resnet18.pt")


if __name__ == "__main__":
    FORMAT = "%(asctime)s %(levelname)s %(filename)s(l:%(lineno)d) - %(message)s"
    LOG_LEVEL = logging.DEBUG
    logging.basicConfig(format=FORMAT, level=LOG_LEVEL)

    websockets_logger = logging.getLogger("websockets")
    websockets_logger.setLevel(logging.DEBUG)
    websockets_logger.addHandler(logging.StreamHandler())

    main()
