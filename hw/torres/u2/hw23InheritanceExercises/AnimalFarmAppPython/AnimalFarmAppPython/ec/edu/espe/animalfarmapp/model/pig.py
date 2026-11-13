"""
@author Ostin Torres, CodeLex, @ESPE
"""
from datetime import date

from ec.edu.espe.animalfarmapp.model.farm_animal import FarmAnimal


class Pig(FarmAnimal):

    def __init__(self, is_wallowing: bool, weight_gain_per_week: float,
                 id: int, breed: str, born_on_date: date, is_meat: bool):
        super().__init__(id, breed, born_on_date, is_meat)
        self.__is_wallowing = is_wallowing
        self.__weight_gain_per_week = weight_gain_per_week

    def __str__(self) -> str:
        return ("Pig{" + super().__str__() + "isWallowing=" + str(self.is_wallowing).lower()
                + ", weightGainPerWeek=" + str(self.weight_gain_per_week) + "}")

    @property
    def is_wallowing(self) -> bool:
        """the isWallowing"""
        return self.__is_wallowing

    @is_wallowing.setter
    def is_wallowing(self, is_wallowing: bool) -> None:
        self.__is_wallowing = is_wallowing

    @property
    def weight_gain_per_week(self) -> float:
        """the weightGainPerWeek"""
        return self.__weight_gain_per_week

    @weight_gain_per_week.setter
    def weight_gain_per_week(self, weight_gain_per_week: float) -> None:
        self.__weight_gain_per_week = weight_gain_per_week
