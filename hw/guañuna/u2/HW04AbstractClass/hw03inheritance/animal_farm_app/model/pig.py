from datetime import date
from .farm_animal import FarmAnimal

class Pig(FarmAnimal):
    def __init__(self, weight_in_kg: float, is_hungry: bool, id: int, breed: str, born_on_date: date):
        super().__init__(id, breed, born_on_date)
        self._weight_in_kg = weight_in_kg
        self._is_hungry = is_hungry

    def eat(self):
        print("pig is eating slop()")

    @property
    def weight_in_kg(self) -> float: return self._weight_in_kg
    @weight_in_kg.setter
    def weight_in_kg(self, value: float): self._weight_in_kg = value

    @property
    def is_hungry(self) -> bool: return self._is_hungry
    @is_hungry.setter
    def is_hungry(self, value: bool): self._is_hungry = value

    def __str__(self) -> str:
        return f"Pig{{{super().__str__()}weightInKg={self._weight_in_kg}, isHungry={self._is_hungry}}}"