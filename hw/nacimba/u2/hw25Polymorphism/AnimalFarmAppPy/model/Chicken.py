"""Author: Mateo Nacimba, @ESPE"""
from datetime import datetime

from .FarmAnimal import FarmAnimal
from .Food import Food
from .SellAnimal import SellAnimal


class Chicken(FarmAnimal, SellAnimal):
    def __init__(self, is_molting: bool, number_of_laid_eggs_per_week: int,
                 id: int, breed: str, born_on_date: datetime):
        super().__init__(id, breed, born_on_date, is_molting)
        self.is_molting = is_molting
        self.number_of_laid_eggs_per_week = number_of_laid_eggs_per_week

    def feed(self, food: Food, amount: int) -> None:
        print(f"giving the chicken {amount} pounds of {food} to eat")

    def sell_animal(self, price: float) -> None:
        print(f"the chicken was sold for {price} dollars.")

    def lay_an_egg(self) -> None:
        self.number_of_laid_eggs_per_week += 1
        print("the chicken just laid an egg")

    def __str__(self) -> str:
        return (f"Chicken{{{super().__str__()}isMolting={self.is_molting}, "
                f"numberOfLaidEggsPerWeek={self.number_of_laid_eggs_per_week}}}")
