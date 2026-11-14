# @author David Clavón, Infinite loops, @ESPE

from datetime import date, datetime
from typing import Optional, Union
from model.farm_animal import FarmAnimal
from model.graze_animal import GrazeAnimal
from model.food import Food
from model.location import Location


class Cow(FarmAnimal, GrazeAnimal):
    def __init__(self, is_producing_milk: bool, liters_of_milk_per_week: float, id: int, breed: str, born_on_date: date):
        super().__init__(id, breed, born_on_date)
        self.__is_producing_milk = is_producing_milk
        self.__liters_of_milk_per_week = liters_of_milk_per_week
        self.__vaccines = []

    def graze(self, location: Location) -> None:
        print(f"Sending the cow to {location}")

    def feed(self, food: Food, amount_of_food: int) -> None:
        print(f"Feeding a cow in the Stable with ---> {amount_of_food}of{food}")

    def is_is_producing_milk(self) -> bool:
        return self.__is_producing_milk

    def set_is_producing_milk(self, is_producing_milk: bool) -> None:
        self.__is_producing_milk = is_producing_milk

    def get_liters_of_milk_per_week(self) -> float:
        return self.__liters_of_milk_per_week

    def set_liters_of_milk_per_week(self, liters_of_milk_per_week: float) -> None:
        self.__liters_of_milk_per_week = liters_of_milk_per_week

    def milk(self, amount: Optional[Union[int, float]] = None, hour: Optional[datetime] = None) -> None:
        if amount is None and hour is None:
            print("Milking the Cow I need")
        elif amount is not None and hour is not None:
            self.__liters_of_milk_per_week += amount
            print(f"milking the cow for {amount}liters, at {hour.hour}:{hour.minute}")
        elif isinstance(amount, float):
            self.__liters_of_milk_per_week += amount
            print(f"miking the cow... we got {amount} today")
            print(f"this cow has produce:  {amount}")
        elif isinstance(amount, int):
            self.__liters_of_milk_per_week += amount
            print(f"Milking the Cow obtend  {amount}")

    def __str__(self) -> str:
        return (
            f"Cow{{{super().__str__()}isProducingMilk={self.__is_producing_milk}, "
            f"litersOfMilkPerWeek={self.__liters_of_milk_per_week}}}"
        )