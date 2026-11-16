from datetime import date
from .farm_animal import FarmAnimal
from .graze_animal_i import GrazeAnimalI
from .food import Food
from .location import Location

class Chicken(FarmAnimal, GrazeAnimalI):
    def __init__(self, is_molting: bool, number_of_laid_eggs_in_a_month: int, is_meat: bool, id: int, breed: str, born_on_date: date):
        super().__init__(id, breed, born_on_date)
        self._is_molting = is_molting
        self._number_of_laid_eggs_in_a_month = number_of_laid_eggs_in_a_month
        self._is_meat = is_meat

    def feed(self, food: Food, amount: int):
        print(f"Feeding a chicken with ---> {amount} pounds of {food}")

    def sen_to_graze(self, location: Location, date: date):
        print(f"Sending the chicken to ---> {location} at {date}")

    def lay_an_egg(self, amount: int = 1):
        self._number_of_laid_eggs_in_a_month += amount
        print(f"Chicken is laying {amount} eggs. Total this month: {self._number_of_laid_eggs_in_a_month}")

    def __str__(self) -> str:
        return f"Chicken{{{super().__str__()}isMolting={self._is_molting}, numberOfLaidEggsInAMonth={self._number_of_laid_eggs_in_a_month}, isMeat={self._is_meat}}}"