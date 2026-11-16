from datetime import date
from .farm_animal import FarmAnimal

class Sheep(FarmAnimal):
    def __init__(self, last_sheering: date, id: int, breed: str, born_on_date: date):
        super().__init__(id, breed, born_on_date)
        self._last_sheering = last_sheering

    @property
    def last_sheering(self) -> date:
        return self._last_sheering

    @last_sheering.setter
    def last_sheering(self, value: date):
        self._last_sheering = value

    def __str__(self) -> str:
        return f"Sheep{{lastSheering={self._last_sheering}, {super().__str__()}}}"