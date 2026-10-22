import csv
import os
from datetime import datetime

from chickenfarmapp.model.chicken import Chicken
from chickenfarmapp.model.poop import Poop

HEADER = ["id", "name", "color", "bornOnDate", "ageInYears",
          "molting", "poopColor", "poopConsistency"]
DATE_FORMAT = "%d/%m/%Y"


class ChickenFiles:
    def __init__(self, file_path):
        self.file_path = file_path

    def add(self, chicken):
        try:
            is_new = (not os.path.exists(self.file_path)
                      or os.path.getsize(self.file_path) == 0)
            with open(self.file_path, "a", newline="", encoding="utf-8") as file:
                writer = csv.writer(file)
                if is_new:
                    writer.writerow(HEADER)
                writer.writerow(self._to_row(chicken))
            print(f"Chicken saved in file: {os.path.abspath(self.file_path)}")
            return True
        except OSError as error:
            print(f"Error saving chicken in file: {error}")
            return False

    def delete(self, chicken_id):
        if not os.path.exists(self.file_path):
            print("The file does not exist yet. Nothing to delete.")
            return False
        try:
            with open(self.file_path, "r", newline="", encoding="utf-8") as file:
                rows = list(csv.reader(file))

            remaining = []
            found = False
            for index, row in enumerate(rows):
                if index == 0 or not row:
                    remaining.append(row)
                elif row[0] == str(chicken_id):
                    found = True
                else:
                    remaining.append(row)

            if found:
                with open(self.file_path, "w", newline="", encoding="utf-8") as file:
                    csv.writer(file).writerows(remaining)
                print(f"Chicken with ID {chicken_id} deleted from file.")
            else:
                print(f"Chicken with ID {chicken_id} not found in file.")
            return found
        except OSError as error:
            print(f"Error deleting chicken from file: {error}")
            return False

    def load_all(self):
        chickens = []
        if not os.path.exists(self.file_path):
            return chickens
        try:
            with open(self.file_path, "r", newline="", encoding="utf-8") as file:
                rows = list(csv.reader(file))
        except OSError as error:
            print(f"Error reading file: {error}")
            return chickens

        for line_number, row in enumerate(rows[1:], start=2):
            if not row:
                continue
            try:
                chickens.append(self._from_row(row))
            except (ValueError, IndexError):
                print(f"Skipping invalid line {line_number} in file.")
        return chickens

    @staticmethod
    def _to_row(chicken):
        poop = chicken.poop
        return [
            chicken.id,
            chicken.name,
            chicken.color,
            chicken.born_on_date.strftime(DATE_FORMAT),
            chicken.age_in_years,
            str(chicken.molting).lower(),
            poop.color if poop else "",
            poop.consistency if poop else "",
        ]

    @staticmethod
    def _from_row(row):
        chicken_id = int(row[0])
        born_on_date = datetime.strptime(row[3], DATE_FORMAT).date()
        molting = row[5].strip().lower() == "true"

        poop = None
        if len(row) > 7 and row[6] and row[7]:
            poop = Poop(chicken_id, row[6], row[7][0])
        return Chicken(chicken_id, row[1], row[2], born_on_date, molting, poop)