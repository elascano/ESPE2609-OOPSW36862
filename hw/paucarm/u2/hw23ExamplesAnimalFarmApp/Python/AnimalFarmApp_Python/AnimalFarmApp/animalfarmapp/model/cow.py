from datetime import date
from animalfarmapp.model.farm_animal import FarmAnimal

# @author Matias Paucar, ByteWorks, @ESPE


class Cow(FarmAnimal):
    def __init__(self, id: int, breed: str, born_on_date: date,
                 is_producing_milk: bool = False,
                 liters_of_milk_per_week: float = 0.0,
                 is_meat: bool = False):
        super().__init__(id, breed, born_on_date)
        self._is_producing_milk = is_producing_milk
        self._liters_of_milk_per_week = liters_of_milk_per_week
        self._is_meat = is_meat

    def __str__(self) -> str:
        return ("Cow{" + super().__str__()
                + f"isProducingMilk={self._is_producing_milk}, "
                + f"litersOfMilkPerWeek={self._liters_of_milk_per_week}" + "}")

    def milk(self):
        print("Milking the cow ")

    @property
    def is_producing_milk(self) -> bool:
        return self._is_producing_milk

    @is_producing_milk.setter
    def is_producing_milk(self, value: bool):
        self._is_producing_milk = value

    @property
    def liters_of_milk_per_week(self) -> float:
        return self._liters_of_milk_per_week

    @liters_of_milk_per_week.setter
    def liters_of_milk_per_week(self, value: float):
        self._liters_of_milk_per_week = value

    @property
    def is_meat(self) -> bool:
        return self._is_meat
