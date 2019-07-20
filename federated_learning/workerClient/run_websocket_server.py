from multiprocessing import Process

import syft as sy
from syft.workers import WebsocketServerWorker
import torch
import argparse

from torchvision import datasets, transforms
import numpy as np

import requests

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


batch_size = 20000
data_format = 1
'''
train = datasets.MNIST("../data", train=True, download=True, transform=transforms.Compose(
                [transforms.ToTensor(), transforms.Normalize((0.1307,), (0.3081,))]
                ))

data_loader = torch.utils.data.DataLoader(train, batch_size=batch_size)
'''
if args.split == 0:
    worker_tag = "#alice"
elif args.split == 1:
    worker_tag = "#bob"
else:
    worker_tag = "#charlie"


'''
for dataset_idx, (data, targets) in enumerate(data_loader):
    if dataset_idx != args.split:
        continue

    train_data = data.tag("#mnist", worker_tag, "#train_tag")
    train_targets = targets.tag("#mnist", worker_tag, "#train_tag")

'''

httpData = {}
httpData['host'] = args.host
httpData['port'] = args.port
httpData['amount'] = batch_size
httpData['totalSize'] = batch_size*256 #TODO
httpData['format'] = data_format # 0 is text; 1 is img
httpData['description'] = str(args.id)
httpData['info'] = "GET "+ str(httpData['host']) + ":" + str(httpData['port']) + "/workers/" + str(args.id) + " HTTP/1.1"

print(httpData)

server_ip = "http://127.0.0.1:5000"

requests.post(server_ip,data=httpData)

kwargs = {
    "id": args.id,
    "host": args.host,
    "port": args.port,
    "hook": hook,
    "verbose": args.verbose,
}

#kwargs["data"] = [train_data, train_targets]

#print(np.shape(train_data),np.shape(train_targets))

#server = start_proc(WebsocketServerWorker, kwargs)


