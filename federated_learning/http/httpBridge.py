from flask import Flask

app = Flask(__name__)

def test():
	return "I am your father"

@app.route('/')
def hello_world():
    return test()




app.run()