import subprocess

call_alice = ["python", "run_websocket_server.py", "--port", "8777", "--id", "alice", "--split", "0"]

call_bob = ["python", "run_websocket_server.py", "--port", "8778", "--id", "bob", "--split", "1"]

call_charlie = ["python", "run_websocket_server.py", "--port", "8779", "--id", "charlie", "--split", "2"]


# --host", type=str, default="localhost",

print("Starting server for Alice")
subprocess.Popen(call_alice)

print("Starting server for Bob")
subprocess.Popen(call_bob)

print("Starting server for Charlie")
subprocess.Popen(call_charlie)
