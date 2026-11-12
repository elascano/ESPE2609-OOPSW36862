import json
import os

from src.models.picture import Picture


class PictureRepository:
    """Keeps pictures in a list (ArrayList equivalent) and persists them to a JSON file."""

    def __init__(self, file_path: str):
        self._file_path = file_path
        self._pictures: list[Picture] = []

    def add(self, picture: Picture) -> None:
        if any(p.id == picture.id for p in self._pictures):
            raise ValueError(f"A picture with id {picture.id} already exists.")
        self._pictures.append(picture)

    def get_all(self) -> list[Picture]:
        return list(self._pictures)

    def save(self) -> None:
        os.makedirs(os.path.dirname(self._file_path), exist_ok=True)
        with open(self._file_path, "w", encoding="utf-8") as f:
            json.dump([p.to_dict() for p in self._pictures], f, indent=2, ensure_ascii=False)

    def load(self) -> list[Picture]:
        if not os.path.exists(self._file_path):
            raise FileNotFoundError(f"File not found: {self._file_path}")
        with open(self._file_path, "r", encoding="utf-8") as f:
            data = json.load(f)
        self._pictures = [Picture.from_dict(d) for d in data]
        return self.get_all()
