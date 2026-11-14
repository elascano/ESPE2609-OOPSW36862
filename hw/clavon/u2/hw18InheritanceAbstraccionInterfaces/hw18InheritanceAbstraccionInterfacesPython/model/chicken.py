# @author David Clavón, Infinite loops, @ESPE

from datetime import date
from model.farm_animal import FarmAnimal
from model.food import Food


class Chicken(FarmAnimal):
    def __init__(self, is_molting: bool, number_of_laid_eggs_in_a_month: int, is_meat: bool, id: int, breed: str, born_on_date: date):
        super().__init__(id, breed, born_on_date)
        self.__is_molting = is_molting
        self.__number_of_laid_eggs_in_a_month = number_of_laid_eggs_in_a_month
        self.__is_meat = is_meat

    def is_is_molting(self) -> bool:
        return self.__is_molting

    def set_is_molting(self, is_molting: bool) -> None:
        self.__is_molting = is_molting

    def get_number_of_laid_eggs_in_a_month(self) -> int:
        return self.__number_of_laid_eggs_in_a_month

    def set_number_of_laid_eggs_in_a_month(self, number_of_laid_eggs_in_a_month: int) -> None:
        self.__number_of_laid_eggs_in_a_month = number_of_laid_eggs_in_a_month

    def is_is_meat(self) -> bool:
        return self.__is_meat

    def set_is_meat(self, is_meat: bool) -> None:
        self.__is_meat = is_meat

    def lay_in_egg(self) -> None:
        print("Chicken is laying egg")

    def feed(self, food: Food, amount_of_food: int) -> None:
        print(f"Feeding a Chicken with ---> {amount_of_food} amound of {food}")

    def __str__(self) -> str:
        return (
            f"Chicken{{{super().__str__()}isMolting={self.__is_molting}, "
            f"numberOfLaidEggsInAMonth={self.__number_of_laid_eggs_in_a_month}, "
            f"isMeat={self.__is_meat}}}"
        )