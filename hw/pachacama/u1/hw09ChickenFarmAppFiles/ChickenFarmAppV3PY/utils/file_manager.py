"""
Data persistence
@author Andres Pachacama, Byte Work, @ESPE
"""
import os
from datetime import date

from model.chicken import Chicken
from model.chicken_coop import ChickenCoop

SEPARATOR = ";"
HEADER = "id;name;color;bornOnDate;molting"


class FileManager:

    @staticmethod
    def save(coop, file_name):
        """writes all the chickens of the coop in the file (replaces what was there)"""
        with open(file_name, "w", encoding="utf-8") as file:
            file.write(HEADER + "\n")

            coop.reset_iteration()
            chicken = coop.next()
            while chicken is not None:
                file.write(str(chicken.id)
                           + SEPARATOR + FileManager._clean(chicken.name)
                           + SEPARATOR + FileManager._clean(chicken.color)
                           + SEPARATOR + chicken.born_on_date.isoformat()    
                           + SEPARATOR + str(chicken.molting) + "\n")
                chicken = coop.next()

    @staticmethod
    def load(file_name, chicken_coop_id):
        """reads the file and returns a coop with the saved chickens
        (if the file does not exist yet, the coop comes empty)"""
        coop = ChickenCoop(chicken_coop_id)

        if not FileManager.exists(file_name):
            return coop

        with open(file_name, "r", encoding="utf-8") as file:
            lines = file.read().splitlines()

        for line in lines[1:]:          
            if coop.is_full():
                break
            chicken = FileManager._to_chicken(line)
            if chicken is not None:    
                coop.add(chicken)

        return coop

    @staticmethod
    def exists(file_name):
        return os.path.exists(file_name)

    @staticmethod
    def full_path(file_name):
        return os.path.abspath(file_name)

    @staticmethod
    def _to_chicken(line):
        """converts one line of the file into a Chicken ("5;Mateo;green;1997-03-03;False")"""
        parts = line.split(SEPARATOR)
        if len(parts) != 5:
            return None

        try:
            id = int(parts[0].strip())
            name = parts[1].strip()
            color = parts[2].strip()
            born_on_date = date.fromisoformat(parts[3].strip())
            molting = parts[4].strip().lower() == "true"

            chicken = Chicken(id, name, color, born_on_date, 0, molting)
            chicken.set_age_in_years()   
            return chicken
        except ValueError:
            return None

    @staticmethod
    def _clean(text):
        """a ";" inside a name would break the line, so we change it to ","."""
        return text.replace(SEPARATOR, ",")
