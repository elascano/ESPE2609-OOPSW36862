from datetime import date
from .farm_animal import FarmAnimal

class Sheep(FarmAnimal):
    def __init__(self, last_sheering: date, wool_weight_in_kg: float, id: int, breed: str, born_on_date: date):
        super().__init__(id, breed, born_on_date)
        self._last_sheering = last_sheering
        self._wool_weight_in_kg = wool_weight_in_kg

    def shear(self):
        print("shearing the sheep()")

    @property
    def last_sheering(self) -> date: return self._last_sheering
    @last_sheering.setter
    def last_sheering(self, value: date): self._last_sheering = value

    @property
    def wool_weight_in_kg(self) -> float: return self._wool_weight_in_kg
    @wool_weight_in_kg.setter
    def wool_weight_in_kg(self, value: float): self._wool_weight_in_kg = value

    def __str__(self) -> str:
        return f"Sheep{{{super().__str__()}lastSheering={self._last_sheering.day}/{self._last_sheering.month}/{self._last_sheering.year}, woolWeightInKg={self._wool_weight_in_kg}}}"