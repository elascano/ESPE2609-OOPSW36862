"""Repository layer: reads and writes SoccerPlayer objects in a JSON file."""

import json
import os

from src.model.soccer_player import SoccerPlayer


class SoccerPlayerJsonRepository:
    """Handles persistence of soccer players in a JSON file."""

    def __init__(self, file_path: str):
        self.file_path = file_path
        self._ensure_file_exists()

    def _ensure_file_exists(self) -> None:
        """Creates the folder and an empty JSON list if the file does not exist."""
        folder = os.path.dirname(self.file_path)
        if folder:
            os.makedirs(folder, exist_ok=True)
        if not os.path.exists(self.file_path):
            self.save_all([])

    def load_all(self) -> list[SoccerPlayer]:
        """Reads the JSON file and returns a list (ArrayList) of SoccerPlayer."""
        try:
            with open(self.file_path, "r", encoding="utf-8") as file:
                data = json.load(file)
        except json.JSONDecodeError:
            print("Warning: the JSON file is corrupted. Starting with an empty list.")
            return []
        return [SoccerPlayer.from_dict(item) for item in data]

    def save_all(self, players: list[SoccerPlayer]) -> None:
        """Writes the whole list of players into the JSON file."""
        with open(self.file_path, "w", encoding="utf-8") as file:
            json.dump([p.to_dict() for p in players], file,
                      indent=4, ensure_ascii=False)
