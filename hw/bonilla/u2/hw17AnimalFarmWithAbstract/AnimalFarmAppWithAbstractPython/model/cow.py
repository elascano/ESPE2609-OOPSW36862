from datetime import datetime
from model.farm_animal import FarmAnimal

class Cow(FarmAnimal):
    def __init__(
        self,
        is_producing_milk: bool,
        liters_of_milk_per_week: float,
        id: int,
        breed: str,
        born_on_date: datetime,
    ):
        super().__init__(id, breed, born_on_date)
        self._is_producing_milk = is_producing_milk
        self._liters_of_milk_per_week = liters_of_milk_per_week

    def milk(self):
        print("milk in the cow ")

    def __str__(self) -> str:
        return (
            f"cow{{{super().__str__()}"
            f"isProducingMilk={str(self._is_producing_milk).lower()}, "
            f"litersOfMilkPerWeek={self._liters_of_milk_per_week}}}"
        )

    def is_is_producing_milk(self) -> bool:
        return self._is_producing_milk

    def set_is_producing_milk(self, is_producing_milk: bool):
        self._is_producing_milk = is_producing_milk

    def get_liters_of_milk_per_week(self) -> float:
        return self._liters_of_milk_per_week

    def set_liters_of_milk_per_week(self, liters_of_milk_per_week: float):
        self._liters_of_milk_per_week = liters_of_milk_per_week