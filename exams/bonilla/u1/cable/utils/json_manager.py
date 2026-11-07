import json
import os
from ec.edu.espe.cable.model.cable import Cable

class CableManager:
    def __init__(self, file_path: str = "cables.json"):
        self.file_path = file_path

    def save_cables(self, cables_list: list[Cable]) -> None:
        data = [cable.to_dict() for cable in cables_list]
        with open(self.file_path, "w", encoding="utf-8") as file:
            json.dump(data, file, indent=4, ensure_ascii=False)

    def load_cables(self) -> list[Cable]:
        if not os.path.exists(self.file_path):
            return []
        try:
            with open(self.file_path, "r", encoding="utf-8") as file:
                data = json.load(file)
                return [Cable.from_dict(item) for item in data]
        except (json.JSONDecodeError, KeyError):
            return []