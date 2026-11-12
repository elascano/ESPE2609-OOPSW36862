from datetime import datetime, date
from model.farm_animal import FarmAnimal

class Cow(FarmAnimal):
    """
    Clase Cow traducida de ec.edu.espe.animalfarmapp.model.Cow
    """
    def __init__(self, is_producing_milk: bool, liters_of_milk_per_week: float, id: int, breed: str, born_on_date: date | datetime, is_meat: bool):
        super().__init__(id, breed, born_on_date, is_meat)
        self._is_producing_milk = is_producing_milk
        self._liters_of_milk_per_week = liters_of_milk_per_week

    @property
    def is_producing_milk(self) -> bool:
        return self._is_producing_milk

    @is_producing_milk.setter
    def is_producing_milk(self, value: bool):
        self._is_producing_milk = value

    def is_is_producing_milk(self) -> bool:
        return self.is_producing_milk

    @property
    def liters_of_milk_per_week(self) -> float:
        return self._liters_of_milk_per_week

    @liters_of_milk_per_week.setter
    def liters_of_milk_per_week(self, value: float):
        self._liters_of_milk_per_week = value

    def get_liters_of_milk_per_week(self) -> float:
        return self.liters_of_milk_per_week

    def __str__(self) -> str:
        return f"Cow{{{super().__str__()}isProducingMilk={self.is_producing_milk}, litersOfMilkPerWeek={self.liters_of_milk_per_week}}}"