import json
import os
from model.notebook import Notebook

class NotebookController:
    def __init__(self, file_path: str = "notebooks.json"):
        self.file_path = file_path
        self.notebooks = []

    def load_notebooks(self):
        if os.path.exists(self.file_path):
            try:
                with open(self.file_path, "r") as file:
                    data = json.load(file)
                    self.notebooks = [Notebook.from_dict(item) for item in data]
            except (json.JSONDecodeError, KeyError):
                self.notebooks = []
        else:
            self.notebooks = []

    def save_notebooks(self):
        with open(self.file_path, "w") as file:
            json.dump([n.to_dict() for n in self.notebooks], file, indent=4)

    def add_notebook(self, id: int, brand: str, ram_gb: int, price: float):
        notebook = Notebook(id, brand, ram_gb, price)
        self.notebooks.append(notebook)
        self.save_notebooks()

    def get_all_notebooks(self):
        self.load_notebooks()
        return self.notebooks