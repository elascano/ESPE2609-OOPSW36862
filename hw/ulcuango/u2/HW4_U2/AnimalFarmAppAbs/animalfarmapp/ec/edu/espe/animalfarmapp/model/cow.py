from datetime import date
from ec.edu.espe.animalfarmapp.model.farm_animal import FarmAnimal

class Cow(FarmAnimal):
    def __init__(self, id: int, bred: str, born_on_date: date, is_producing_milk: bool):
        super().__init__(id, bred, born_on_date)
        self._is_producing_milk = is_producing_milk

    def milk(self):
        print("Cow is milking")

    def __str__(self) -> str:
        super_str = super().__str__().replace("}", "")
        return f"Cow{{{super_str}, isProducingMilk={self._is_producing_milk}}}"

    @property
    def is_is_producing_milk(self) -> bool:
        return self._is_producing_milk

    @is_is_producing_milk.setter
    def is_is_producing_milk(self, is_producing_milk: bool):
        self._is_producing_milk = is_producing_milk