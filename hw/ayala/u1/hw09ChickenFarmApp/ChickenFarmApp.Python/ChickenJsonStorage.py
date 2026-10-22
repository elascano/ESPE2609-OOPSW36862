"""
@author Enrique Ayala, LogicLords,@ESPE
"""

import json
import os
from datetime import datetime
import Chicken
import ChickenCoop

class ChickenJsonStorage:
    def __init__(self, file_path: str):
        self._file_path = file_path

    def save(self, coop: ChickenCoop.ChickenCoop):
        try:
            data = []
            for chicken in coop.to_array():
                data.append({
                    "id": chicken.id,
                    "name": chicken.name,
                    "color": chicken.color,
                    "bornOnDate": chicken.born_on_date.strftime("%Y-%m-%d"),
                    "ageInYears": chicken.age_in_years,
                    "molting": chicken.molting
                })
            
            with open(self._file_path, "w", encoding="utf-8") as f:
                json.dump(data, f, indent=2)
        except Exception as e:
            print(f"Could not save the file: {e}")

    def load(self, coop: ChickenCoop.ChickenCoop):
        if not os.path.exists(self._file_path):
            return  # First run, nothing to load yet

        try:
            with open(self._file_path, "r", encoding="utf-8") as f:
                data = json.load(f)
                if data:
                    for item in data:
                        born_date = datetime.strptime(item["bornOnDate"], "%Y-%m-%d").date()
                        chicken = Chicken.Chicken(
                            chicken_id=item["id"],
                            name=item["name"],
                            color=item["color"],
                            born_on_date=born_date,
                            molting=item["molting"]
                        )
                        chicken.set_age_in_years()  # Update saved age if outdated
                        coop.add(chicken)
        except Exception as e:
            print(f"Could not read the file: {e}")