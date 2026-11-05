import json
import os

from src.model.video_projector import VideoProjector


class VideoProjectorJsonFile:
    

    def __init__(self, file_path: str):
        self.__file_path = file_path

    def get_file_path(self) -> str:
        return self.__file_path

    def save(self, video_projector_list: list) -> None:
        """Writes the whole list into the JSON file"""
        dictionary_list = []

        for video_projector in video_projector_list:
            dictionary_list.append(video_projector.to_dictionary())

        with open(self.__file_path, "w", encoding="utf-8") as file:
            json.dump(dictionary_list, file, indent=4)

    def read(self) -> list:
        """Reads the JSON file and returns the list of VideoProjector objects"""
        video_projector_list = []

        if not os.path.exists(self.__file_path):
            return video_projector_list

        with open(self.__file_path, "r", encoding="utf-8") as file:
            json_text = file.read()

        if json_text.strip() == "":
            return video_projector_list

        dictionary_list = json.loads(json_text)
        for item in dictionary_list:
            video_projector = VideoProjector(item["id"], item["brand"],
                                             item["lumens"], item["resolution"])
            video_projector_list.append(video_projector)

        return video_projector_list
