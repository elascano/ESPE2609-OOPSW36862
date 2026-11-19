"""Author: Mateo Nacimba, @ESPE"""
from datetime import datetime

from .FarmAnimal import FarmAnimal
from .Food import Food
from .SellAnimal import SellAnimal


class Pig(FarmAnimal, SellAnimal):
    def __init__(self, is_wallowing: bool, weight_gain_per_week: float,
                 id: int, breed: str, born_on_date: datetime, is_meat: bool):
        super().__init__(id, breed, born_on_date, is_meat)
        self.is_wallowing = is_wallowing
        self.weight_gain_per_week = weight_gain_per_week

    def show_pig_meat_cuts(self) -> None:
        meat_cuts = ["head", "cheek", "picnic", "clear plate", "boston shoulder",
                     "back fat", "loin", "ribs", "bacon", "leg", "ham", "hock"]
        print("Meat cuts available from the pig: " + str(meat_cuts))

    def feed(self, food: Food, amount: int) -> None:
        print(f"giving the pig {amount} kg of {food} to eat")

    def sell_animal(self, price: float) -> None:
        print(f"the pig was sold for {price} dollars.")

    def report_weight_gain(self, weight_gain: int) -> None:
        print(f"this week the pig put on {weight_gain} kg")

    def __str__(self) -> str:
        return (f"Pig{{{super().__str__()}isWallowing={self.is_wallowing}, "
                f"weightGainPerWeek={self.weight_gain_per_week}}}")
