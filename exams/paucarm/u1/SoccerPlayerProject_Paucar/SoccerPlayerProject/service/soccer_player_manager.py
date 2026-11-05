import json
import os

from model.soccer_player import SoccerPlayer


class SoccerPlayerManager:

    def __init__(self, file_path):
        self.__players = []         
        self.__file_path = file_path

    def get_players(self):
        return self.__players

    def exists_id(self, id):
        for player in self.__players:
            if player.get_id() == id:
                return True
        return False

    def add_player(self, player):
        if self.exists_id(player.get_id()):
            return False
        self.__players.append(player)
        return True

    def save_to_json(self):
        data = []
        for player in self.__players:
            data.append(player.to_dictionary())
        with open(self.__file_path, "w", encoding="utf-8") as file:
            json.dump(data, file, indent=4, ensure_ascii=False)

    def read_from_json(self):
        self.__players = []
        if not os.path.exists(self.__file_path):
            return
        with open(self.__file_path, "r", encoding="utf-8") as file:
            content = file.read().strip()
            if content == "":
                return
            data = json.loads(content)
        for item in data:
            self.__players.append(SoccerPlayer.from_dictionary(item))
