from multiprocessing import Process
import syft as sy
from syft.workers import WebsocketServerWorker
import torch
import argparse
import math
from torchvision import datasets, transforms
from torch.utils.data import TensorDataset

hook = sy.TorchHook(torch)

def start_proc(participant, kwargs):  # pragma: no cover
    """ helper function for spinning up a websocket participant """

    def target():
        server = participant(**kwargs)
        server.start()

    p = Process(target=target)
    p.start()
    return p

parser = argparse.ArgumentParser(description="Run websocket server worker.")
parser.add_argument(
    "--port", "-p", type=int, help="port number of the websocket server worker, e.g. --port 8777"
)
parser.add_argument("--host", type=str, default="localhost", help="host for the connection")
parser.add_argument(
    "--id", type=str, help="name (id) of the websocket server worker, e.g. --id alice"
)
parser.add_argument(
    "--verbose",
    "-v",
    action="store_true",
    help="if set, websocket server worker will be started in verbose mode",
)
parser.add_argument(
    "--split", "-s", type=int, help="data split"
)

args = parser.parse_args()

train_samples = 16

# # directory and image information
# train_data_dir = 'chest_xray/small'

# data_transforms = transforms.Compose([
#     transforms.Resize(224),
#     transforms.CenterCrop(224),
#     transforms.RandomRotation(20),
#     transforms.RandomHorizontalFlip(),
#     transforms.ToTensor(),
#     transforms.Normalize([0.5,0.5,0.5],[0.5,0.5,0.5])
# ])

# train = datasets.ImageFolder(train_data_dir, data_transforms)

# print(len(train.imgs))
# print(train.class_to_idx)

inputs = torch.load('chest_xray/preprocessed/input_small.pt')
targets = torch.load('chest_xray/preprocessed/target_small.pt')
train = TensorDataset(inputs, targets)

data_loader = torch.utils.data.DataLoader(train, batch_size=math.floor(train_samples/3))

if args.split == 0:
    worker_tag = "#hospital_a"
elif args.split == 1:
    worker_tag = "#hospital_b"
else:
    worker_tag = "#hospital_c"

for dataset_idx, (data, targets) in enumerate(data_loader):

    # torch.save(data,'chest_xray/preprocessed/input_small.pt')
    # torch.save(targets,'chest_xray/preprocessed/target_small.pt')

    if dataset_idx != args.split:
        continue

    train_data = data.tag("#chest_xray", worker_tag, "#train_tag")
    train_targets = targets.tag("#chest_xray", worker_tag, "#train_tag")

kwargs = {
    "id": args.id,
    "host": args.host,
    "port": args.port,
    "hook": hook,
    "verbose": args.verbose,
}


kwargs["data"] = [train_data, train_targets]

server = start_proc(WebsocketServerWorker, kwargs)






