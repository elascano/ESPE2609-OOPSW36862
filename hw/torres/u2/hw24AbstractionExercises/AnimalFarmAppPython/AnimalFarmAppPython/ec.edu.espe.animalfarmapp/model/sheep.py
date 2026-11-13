"""
@author Ostin Torres, CodeLex, @ESPE
"""
from datetime import date

from model.farm_animal import FarmAnimal


class Sheep(FarmAnimal):

    def shear_sheep(self) -> None:
        print("Sheep is sheared")

    def __str__(self) -> str:
        return ("Sheep{" + super().__str__() + "isSheared=" + str(self.is_sheared).lower()
                + ", lastSheering=" + str(self.last_sheering) + "}")

    def __init__(self, is_sheared: bool, last_sheering: date,
                 id: int, breed: str, born_on_date: date, is_meat: bool):
        super().__init__(id, breed, born_on_date, is_meat)
        self.__is_sheared = is_sheared
        self.__last_sheering = last_sheering

    @property
    def is_sheared(self) -> bool:
        """the isSheared"""
        return self.__is_sheared

    @is_sheared.setter
    def is_sheared(self, is_sheared: bool) -> None:
        self.__is_sheared = is_sheared

    @property
    def last_sheering(self) -> date:
        """the lastSheering"""
        return self.__last_sheering

    @last_sheering.setter
    def last_sheering(self, last_sheering: date) -> None:
        self.__last_sheering = last_sheering
