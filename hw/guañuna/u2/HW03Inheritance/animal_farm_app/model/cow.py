from datetime import date
from .farm_animal import FarmAnimal

class Cow(FarmAnimal):
    def __init__(self, is_producing_milk: bool, id: int, breed: str, born_on_date: date):
        super().__init__(id, breed, born_on_date)
        self._is_producing_milk = is_producing_milk

    @property
    def is_producing_milk(self) -> bool:
        return self._is_producing_milk

    @is_producing_milk.setter
    def is_producing_milk(self, value: bool):
        self._is_producing_milk = value

    def __str__(self) -> str:
        return f"Cow{{isProducingMilk={self._is_producing_milk}, {super().__str__()}}}"