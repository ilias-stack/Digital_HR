from flask_cors import CORS, cross_origin
from flask import Flask, jsonify, abort, request
from flask import request as flask_request
from urlProcessor import predict, predictNlp
from functools import wraps
from io import BytesIO
from cv_analyser import *
from werkzeug.utils import secure_filename

# import jwt

app = Flask(__name__)
CORS(app,)

map = {
    -1:True,
    1:False
}

#! Phishing Analysis Part

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


#! CV Analysis Part
ALLOWED_EXTENSIONS = {'pdf'}

def allowed_file(filename):
    return '.' in filename and filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

@app.route("/analyse_cvs",methods = ["POST"])
def analyse_cvs():
    if 'files[]' not in request.files:
        return jsonify({'error': 'No files were uploaded for processing.'}), 400
    
    files = request.files.getlist('files[]')
    wanted_skills = request.form.getlist('skills')
    
    if not files:
        return jsonify({'error': 'No selected files'}), 400
    
    if not wanted_skills:
        return jsonify({'error': 'No wanted skills provided'}), 400

    uploaded_files = []
    for file in files:
        if file and allowed_file(file.filename):
            filename = secure_filename(file.filename)
            file_in_memory = BytesIO(file.read())
            uploaded_files.append(file_in_memory)

    if not uploaded_files:
        return jsonify({'error': 'Invalid file type'}), 400

    extracted_skills = get_skills(uploaded_files)
    results = {}
    for email, skills in extracted_skills.items():
        score = skills_match_score(wanted_skills, skills)
        results[email] = score
        
    return jsonify(results), 200


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
