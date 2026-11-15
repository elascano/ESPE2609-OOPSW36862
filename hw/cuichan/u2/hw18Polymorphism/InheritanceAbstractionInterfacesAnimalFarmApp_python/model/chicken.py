from datetime import date
from model.farm_animal import FarmAnimal
from model.food import Food

class Chicken(FarmAnimal):
    def __init__(self, is_molting: bool, number_of_laid_eggs_in_a_month: int, is_meat: bool, id: int, breed: str, born_on_date: date):
        super().__init__(id, breed, born_on_date)
        self.is_molting = is_molting
        self.number_of_laid_eggs_in_a_month = number_of_laid_eggs_in_a_month
        self.is_meat = is_meat

    def __str__(self):
        return (f"Chicken{{{super().__str__()}"
                f"isMolting={self.is_molting}, "
                f"numberOfLaidEggsInAMonth={self.number_of_laid_eggs_in_a_month}, "
                f"isMeat={self.is_meat}}}")

    @property
    def is_molting_status(self):
        return self.is_molting

    @is_molting_status.setter
    def is_molting_status(self, is_molting: bool):
        self.is_molting = is_molting

    @property
    def number_of_laid_eggs(self):
        return self.number_of_laid_eggs_in_a_month

    @number_of_laid_eggs.setter
    def number_of_laid_eggs(self, number_of_laid_eggs_in_a_month: int):
        self.number_of_laid_eggs_in_a_month = number_of_laid_eggs_in_a_month

    @property
    def is_meat_status(self):
        return self.is_meat

    @is_meat_status.setter
    def is_meat_status(self, is_meat: bool):
        self.is_meat = is_meat

    def lay_in_egg(self):
        print("Chicken is laying egg")

    def feed(self, food: Food, amount_of_food: int):
        print(f"Feeding a Chicken with {amount_of_food} pound of {food}")