"""
@author Ostin Torres, CodeLex, @ESPE
"""
from datetime import date

from ec.edu.espe.animalfarmapp.model.farm_animal import FarmAnimal


class Chicken(FarmAnimal):

    def __init__(self, is_molting: bool, number_of_laid_eggs_per_week: int,
                 id: int, breed: str, born_on_date: date):
        super().__init__(id, breed, born_on_date, is_molting)
        self.__is_molting = is_molting
        self.__number_of_laid_eggs_per_week = number_of_laid_eggs_per_week

    def __str__(self) -> str:
        return ("Chicken{" + super().__str__() + "isMolting=" + str(self.is_molting).lower()
                + ", numberOfLaidEggsPerWeek=" + str(self.number_of_laid_eggs_per_week) + "}")

    @property
    def is_molting(self) -> bool:
        """the isMolting"""
        return self.__is_molting

    @is_molting.setter
    def is_molting(self, is_molting: bool) -> None:
        self.__is_molting = is_molting

    @property
    def number_of_laid_eggs_per_week(self) -> int:
        """the numberOfLaidEggsPerWeek"""
        return self.__number_of_laid_eggs_per_week

    @number_of_laid_eggs_per_week.setter
    def number_of_laid_eggs_per_week(self, number_of_laid_eggs_per_week: int) -> None:
        self.__number_of_laid_eggs_per_week = number_of_laid_eggs_per_week
