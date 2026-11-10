"""
Data persistence
@author Andres Pachacama, Byte Work, @ESPE
"""
import json
import os

from model.doll import Doll


class JsonFileManager:

    @staticmethod
    def save(dolls, file_name):
        dictionaries = []
        for doll in dolls:
            dictionaries.append(doll.to_dictionary())

        with open(file_name, "w", encoding="utf-8") as file:
            json.dump(dictionaries, file, indent=4, ensure_ascii=False)

    @staticmethod
    def load(file_name):
        dolls = []
        if not JsonFileManager.exists(file_name):
            return dolls
        with open(file_name, "r", encoding="utf-8") as file:
            dictionaries = json.load(file)
        for dictionary in dictionaries:
            try:
                dolls.append(Doll.from_dictionary(dictionary))
            except (KeyError, ValueError, TypeError):
                pass        # a broken doll in the file is skipped
        return dolls

    @staticmethod
    def exists(file_name):
        return os.path.exists(file_name)
    
    @staticmethod
    def full_path(file_name):
        return os.path.abspath(file_name)
