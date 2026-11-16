from datetime import date
from .farm_animal import FarmAnimal

class Chicken(FarmAnimal):
    def __init__(self, is_molting: bool, id: int, breed: str, born_on_date: date):
        super().__init__(id, breed, born_on_date)
        self._is_molting = is_molting

    @property
    def is_molting(self) -> bool:
        return self._is_molting

    @is_molting.setter
    def is_molting(self, value: bool):
        self._is_molting = value

    def __str__(self) -> str:
        return f"Chicken{{isMolting={self._is_molting}, {super().__str__()}}}"