import subprocess

call_a = ["python", "run_hospital_data_woker.py", "--port", "8777", "--id", "hospital_a", "--split", "0"]

call_b = ["python", "run_hospital_data_woker.py", "--port", "8778", "--id", "hospital_b", "--split", "1"]

call_c = ["python", "run_hospital_data_woker.py", "--port", "8779", "--id", "hospital_c", "--split", "2"]


print("Starting server for hospital A")
subprocess.Popen(call_a)

print("Starting server for hospital B")
subprocess.Popen(call_b)

print("Starting server for hospital C")
subprocess.Popen(call_c)
