import json
import os
from model.notebook import Notebook

BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
DATA_DIR = os.path.join(BASE_DIR, "data")
FILE_PATH = os.path.join(DATA_DIR, "notebooks.json")


def save(notebook_list):

    os.makedirs(DATA_DIR, exist_ok=True)
    with open(FILE_PATH, "w", encoding="utf-8") as file:
        json.dump([n.to_dict() for n in notebook_list], file,
                  indent=4, ensure_ascii=False)


def load():

    if not os.path.exists(FILE_PATH):
        return []
    with open(FILE_PATH, "r", encoding="utf-8") as file:
        return [Notebook.from_dict(item) for item in json.load(file)]