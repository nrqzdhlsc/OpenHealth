# Federated Learning

## Introduction

Combined Federated Learning (Sybt) with blockChain technology(SmartContract & Private Data Platform). In this project, we use Ficos Bcos as blockchain platform to build a private data platform. And we modify the architecture of OpenMined' syft(Federated Learning Architecture), and combined it with smart contract.

In this demo, we create three roles of Worker and one role of Client in FL.



### HOW TO RUN

    cd WorkerServer
    python start_websocket_servers.py
    cd ..
    python run_websocket_client

## Architecture

flow process:
- worker process 
    - run server and prepare data (**DONE**)
    - register in the smart contract(**DONE**)

- client process
    - match data source in smart contract & get data's information(host|port)(**DONE**)
    - run client, connect to worker(**DONE**)
    - federated-learning(**DONE**)


