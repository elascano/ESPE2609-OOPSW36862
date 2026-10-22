"""
Persistence: saves the chickens of the coop in a text file and reads them again.
Every line of the file is one chicken, with its data separated by ";".

@author Paucar Matias, ByteWorks, @ESPE
"""
import os
from datetime import date

from model.chicken import Chicken
from model.chicken_coop import ChickenCoop

FILE_NAME = "chickens.csv"
SEPARATOR = ";"
HEADER = "id;name;color;yearOfBirth;monthOfBirth;dayOfBirth;molting"


def save(coop: ChickenCoop):
    """
    Writes ALL the chickens of the coop in the file.
    The file is created again every time, so it always has the current chickens:
    the ones that were already there, the new ones, and without the removed ones.
    """
    try:
        with open(FILE_NAME, "w", encoding="utf-8") as file:
            file.write(HEADER + "\n")

            coop.reset_iteration()
            while coop.has_next():
                chicken = coop.next()
                born_on_date = chicken.get_born_on_date()
                file.write(str(chicken.get_id()) + SEPARATOR
                           + _clean(chicken.get_name()) + SEPARATOR
                           + _clean(chicken.get_color()) + SEPARATOR
                           + str(born_on_date.year) + SEPARATOR
                           + str(born_on_date.month) + SEPARATOR
                           + str(born_on_date.day) + SEPARATOR
                           + str(chicken.is_molting()).lower() + "\n")
    except OSError as error:
        print("Error: the file " + FILE_NAME + " could not be saved: " + str(error))


def load(chicken_coop_id: int) -> ChickenCoop:
    """
    Reads the file and returns a coop with the chickens that are saved there.
    If the file does not exist yet, it returns an empty coop.
    """
    coop = ChickenCoop(chicken_coop_id)

    if not os.path.exists(FILE_NAME):
        return coop

    try:
        with open(FILE_NAME, "r", encoding="utf-8") as file:
            for line_number, line in enumerate(file, start=1):
                line = line.strip()

                # we skip the header and the empty lines
                if line_number == 1 or line == "":
                    continue

                chicken = _to_chicken(line)
                if chicken is None:
                    print("Warning: line " + str(line_number) + " of " + FILE_NAME
                          + " is not valid and was skipped")
                elif not coop.add(chicken):
                    print("Warning: the coop is full, the other chickens of the file were not loaded")
                    break
    except OSError as error:
        print("Error: the file " + FILE_NAME + " could not be read: " + str(error))

    return coop


def _to_chicken(line: str):
    """
    Converts one line of the file in a Chicken. Returns None if the line has an error.
    """
    data = line.split(SEPARATOR)
    if len(data) != 7:
        return None
    try:
        chicken_id = int(data[0])
        name = data[1]
        color = data[2]
        born_on_date = date(int(data[3]), int(data[4]), int(data[5]))
        molting = data[6].strip().lower() == "true"
        return Chicken(chicken_id, name, color, born_on_date, molting)
    except ValueError:
        return None


def _clean(text: str) -> str:
    """
    The ";" separates the data in the file, so it can not be inside a name or a color.
    """
    return text.replace(SEPARATOR, ",")
