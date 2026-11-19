from datetime import date
from .farm_animal import FarmAnimal

class Chicken(FarmAnimal):
    def __init__(self, is_molting: bool, number_of_laid_egg_in_month: int, is_meat: bool, id: int, breed: str, born_on_date: date):
        super().__init__(id, breed, born_on_date)
        self._is_molting = is_molting
        self._number_of_laid_egg_in_month = number_of_laid_egg_in_month
        self._is_meat = is_meat

    def lay_an_egg(self):
        print("chicken is laying an egg()")

    @property
    def is_molting(self) -> bool: return self._is_molting
    @is_molting.setter
    def is_molting(self, value: bool): self._is_molting = value

    @property
    def number_of_laid_egg_in_month(self) -> int: return self._number_of_laid_egg_in_month
    @number_of_laid_egg_in_month.setter
    def number_of_laid_egg_in_month(self, value: int): self._number_of_laid_egg_in_month = value

    @property
    def is_meat(self) -> bool: return self._is_meat
    @is_meat.setter
    def is_meat(self, value: bool): self._is_meat = value

    def __str__(self) -> str:
        return f"Chicken{{{super().__str__()}isMolting={self._is_molting}, numberOfLaidEggInMonth={self._number_of_laid_egg_in_month}, isMeat={self._is_meat}}}"