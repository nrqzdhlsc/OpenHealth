# Federated Learning

    cd WorkerServer
    python start_websocket_servers.py
    cd ..
    python run_websocket_client

## Architecture

flow process:
- worker process 
    - run server and prepare data
    - register in the smart contract

- client process
    - match data source in smart contract & get data's information(host|port)
    - run client, connect to worker
    - federated-learning


