"""Author: Mateo Nacimba, @ESPE"""
from datetime import datetime

from .FarmAnimal import FarmAnimal
from .Food import Food
from .GrazeAnimal import GrazeAnimal
from .Location import Location


class Sheep(FarmAnimal, GrazeAnimal):
    def __init__(self, is_sheared: bool, last_shearing: datetime,
                 id: int, breed: str, born_on_date: datetime, is_meat: bool):
        super().__init__(id, breed, born_on_date, is_meat)
        self.is_sheared = is_sheared
        self.last_shearing = last_shearing

    def shear_sheep(self) -> None:
        print("the sheep has just been sheared")

    def feed(self, food: Food, amount: int) -> None:
        print(f"giving the sheep {amount} kg of {food} to eat")

    def send_to_graze(self, location: Location, date: datetime) -> None:
        print(f"taking the sheep out to {location} at {date.hour} : {date.minute}")

    def __str__(self) -> str:
        return (f"Sheep{{{super().__str__()}isSheared={self.is_sheared}, "
                f"lastShearing={self.last_shearing}}}")
