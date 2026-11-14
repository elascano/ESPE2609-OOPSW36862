from datetime import date
from ec.edu.espe.animalfarmapp.model.farm_animal import FarmAnimal

class Sheep(FarmAnimal):
    def __init__(self, id: int, bred: str, born_on_date: date, last_sheering: date):
        super().__init__(id, bred, born_on_date)
        self._last_sheering = last_sheering

    def __str__(self) -> str:
        super_str = super().__str__().replace("}", "")
        return f"Sheep{{{super_str}, lastSheering={self._last_sheering}}}"

    @property
    def last_sheering(self) -> date:
        return self._last_sheering

    @last_sheering.setter
    def last_sheering(self, last_sheering: date):
        self._last_sheering = last_sheering