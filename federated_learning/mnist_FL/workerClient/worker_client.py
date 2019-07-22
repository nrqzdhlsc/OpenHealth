from flask import Flask,url_for,request,render_template
import sys
sys.path.append("..")
from Bridge.httpBridge import sendData

app  =  Flask(__name__)


@app.route('/', methods=['POST', 'GET'])
def receiveData():
    if request.method == 'POST':
        data = request.form
        print(data)
        sendData(data)
        return data
    return "hello"

if __name__ == "__main__":
    app.run(debug=True)