"""Author: Mateo Nacimba, @ESPE"""
from datetime import datetime
from typing import Optional

from .FarmAnimal import FarmAnimal
from .Food import Food
from .GrazeAnimal import GrazeAnimal
from .Location import Location


class Cow(FarmAnimal, GrazeAnimal):
    def __init__(self, is_producing_milk: bool, liters_of_milk_per_week: float,
                 id: int, breed: str, born_on_date: datetime, is_meat: bool):
        super().__init__(id, breed, born_on_date, is_meat)
        self.is_producing_milk = is_producing_milk
        self.liters_of_milk_per_week = liters_of_milk_per_week

    def milk(self, liters: Optional[float] = None) -> None:
        """Without arguments just reports the total; with liters adds today's yield."""
        if liters is not None:
            self.liters_of_milk_per_week += liters
            print(f"milking the cow... today's yield ---> {liters} liters")
        print(f"this cow has given a total of ---> {self.liters_of_milk_per_week} liters this week")

    def feed(self, food: Food, amount: int) -> None:
        print(f"giving the cow {amount} units of {food} to eat")

    def send_to_graze(self, location: Location, date: datetime) -> None:
        print(f"taking the cow out to {location} at {date.hour} : {date.minute}")

    def __str__(self) -> str:
        return (f"Cow{{{super().__str__()}isProducingMilk={self.is_producing_milk}, "
                f"litersOfMilkPerWeek={self.liters_of_milk_per_week}}}")
