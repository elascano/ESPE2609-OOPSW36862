from datetime import date
from model.farm_animal import FarmAnimal

# Demian Armas, Logical Lords, @ESPE
class Chicken(FarmAnimal):
    def __init__(self, is_molting: bool, number_of_laid_egg_in_month: int, is_meat: bool, id: int, breed: str, born_on_date: date):
        super().__init__(id, breed, born_on_date)
        self.__is_molting = is_molting
        self.__number_of_laid_egg_in_month = number_of_laid_egg_in_month
        self.__is_meat = is_meat

    def __str__(self) -> str:
        return f"Chicken{{{super().__str__()}is Molting={self.__is_molting}, number Of Laid Egg In Month={self.__number_of_laid_egg_in_month}, is Meat={self.__is_meat}}}"

    @property
    def is_molting(self) -> bool:
        return self.__is_molting

    @is_molting.setter
    def is_molting(self, is_molting: bool):
        self.__is_molting = is_molting

    @property
    def number_of_laid_egg_in_month(self) -> int:
        return self.__number_of_laid_egg_in_month

    @number_of_laid_egg_in_month.setter
    def number_of_laid_egg_in_month(self, number_of_laid_egg_in_month: int):
        self.__number_of_laid_egg_in_month = number_of_laid_egg_in_month

    @property
    def is_meat(self) -> bool:
        return self.__is_meat

    @is_meat.setter
    def is_meat(self, is_meat: bool):
        self.__is_meat = is_meat