from flask_cors import CORS, cross_origin
from flask import Flask, jsonify, abort
from flask import request as flask_request
from urlProcessor import predict, predictNlp
from functools import wraps
# import jwt

app = Flask(__name__)
CORS(app, support_credentials=True)

map = {
    -1:True,
    1:False
}

def mapBlacklist(x):
    return x == -1

def mapSpam(x):
    return x == "spam"

@app.route('/api/process-url', methods=['GET'])
@cross_origin()
def processUrl():
    url = flask_request.args.get('url')
    res = predict(url)[0]
    # print(res)
    return {'spam':bool(mapBlacklist(res))}

@app.route('/api/process-nlp', methods=['POST'])
@cross_origin()
def processNlp():
    text = flask_request.json['text']
    return {'spam':mapSpam(predictNlp([text])[0])}


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
