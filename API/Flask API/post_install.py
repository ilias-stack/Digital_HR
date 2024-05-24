import subprocess
import sys

def download_spacy_model(model_name):
    subprocess.run([sys.executable, "-m", "spacy", "download", model_name])

def download_nltk_data(data_name):
    import nltk
    nltk.download(data_name)

if __name__ == "__main__":
    download_spacy_model("en_core_web_sm")
    download_nltk_data("stopwords")