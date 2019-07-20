import requests

JAVAserverIP = "http://localhost:3000"

def sendData(host,data):
	print("in Bridge:",host,data)
	requests.post(host,data=data)