import json
import os
from model.chicken_coop import ChickenCoop

class JsonManager:
    FILE_NAME = "coops.json"

    @staticmethod
    def save_coops(coops):
        data = [coop.to_dict() for coop in coops]
        with open(JsonManager.FILE_NAME, "w") as f:
            json.dump(data, f, indent=4)

    @staticmethod
    def load_coops():
        if not os.path.exists(JsonManager.FILE_NAME):
            return []
        try:
            with open(JsonManager.FILE_NAME, "r") as f:
                data = json.load(f)
                return [ChickenCoop.from_dict(d) for d in data]
        except Exception:
            return []