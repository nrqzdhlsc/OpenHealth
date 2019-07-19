from flask import Flask
import re_encryption
import json

app = Flask(__name__)


@app.route('/')
def hello_world():
    return 'Hello World'


@app.route('/gen_key')
def gen_key(request):
    params = json.load(request)
    return re_encryption.ReEncryption.generateKeys(params['account'])


if __name__ == '__main__':
    app.run()
