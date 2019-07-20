from flask import Flask,url_for,request,render_template

import requests

app  =  Flask(__name__)


def sendData(data):
	JAVAserverIP = "http://localhost:3000"
	requests.post(JAVAserverIP,data)

@app.route('/', methods=['POST', 'GET'])
def receiveData():
    if request.method == 'POST':
        data = request.form
        print(data)
        return data
    return "hello"

if __name__ == "__main__":
    app.run(debug=True)