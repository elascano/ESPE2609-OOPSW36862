# @author Andrés Pachacama, ByteWorks, @ESPE
from datetime import date, datetime
from typing import overload

from animalfarmapp.model.farm_animal import FarmAnimal
from animalfarmapp.model.food import Food
from animalfarmapp.model.graze_animal_i import GrazeAnimalI
from animalfarmapp.model.location import Location


class Cow(FarmAnimal, GrazeAnimalI):

    @overload
    def milk(self) -> None: ...

    @overload
    def milk(self, liters: float) -> None: ...

    @overload
    def milk(self, liters: int) -> None: ...

    def milk(self, liters=None):
        if liters is None:
            print("this cow has produced --->" + str(self._liters_of_milk_per_week) + "liters per week")
            return
        self._liters_of_milk_per_week += liters
        print("milking the cow... we got --->" + str(liters) + " liters today")
        print("this cow has produced --->" + str(self._liters_of_milk_per_week) + "liters per week")

    def feed(self, food: Food, amount: int):
        print("feeding the cow with " + str(amount) + " of " + str(food))

    def __init__(self, is_producing_milk: bool, liters_of_milk_per_week: float,
                 id: int, breed: str, born_on_date: date, is_meat: bool):
        super().__init__(id, breed, born_on_date, is_meat)
        self._is_producing_milk = is_producing_milk
        self._liters_of_milk_per_week = liters_of_milk_per_week

    def send_to_graze(self, location: Location, date: datetime):
        print("send the cow to " + str(location) + " at " + str(date.hour) + " : " + str(date.minute))

    def __str__(self) -> str:
        return ("Cow{" + super().__str__()
                + "isProducingMilk=" + str(self._is_producing_milk).lower()
                + ", litersOfMilkPerWeek=" + str(self._liters_of_milk_per_week) + "}")

    @property
    def is_producing_milk(self) -> bool:
        return self._is_producing_milk

    @is_producing_milk.setter
    def is_producing_milk(self, is_producing_milk: bool):
        self._is_producing_milk = is_producing_milk

    @property
    def liters_of_milk_per_week(self) -> float:
        return self._liters_of_milk_per_week

    @liters_of_milk_per_week.setter
    def liters_of_milk_per_week(self, liters_of_milk_per_week: float):
        self._liters_of_milk_per_week = liters_of_milk_per_week
