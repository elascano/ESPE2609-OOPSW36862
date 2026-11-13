from datetime import date
from model.farm_animal import FarmAnimal

# @author David Clavón, Infinite Loops, @ESPE
class Sheep(FarmAnimal):
    def __init__(self, last_sheering: date, is_producing_wool: bool, kg_of_wool_for_month: float, id: int, breed: str, born_on_date: date):
        super().__init__(id, breed, born_on_date)
        self.__last_sheering = last_sheering
        self.__is_producing_wool = is_producing_wool
        self.__kg_of_wool_for_month = kg_of_wool_for_month

    def shear(self):
        print("shearing the sheep")

    def __str__(self) -> str:
        formatted_sheering = f"{self.__last_sheering.day}/{self.__last_sheering.month}/{self.__last_sheering.year}"
        return f"Sheep{{{super().__str__()}lastSheering={formatted_sheering}, isProducingWool={self.__is_producing_wool}, kgOfWoolForMonth={self.__kg_of_wool_for_month}}}"

    @property
    def last_sheering(self) -> date:
        return self.__last_sheering

    @last_sheering.setter
    def last_sheering(self, last_sheering: date):
        self.__last_sheering = last_sheering

    @property
    def is_producing_wool(self) -> bool:
        return self.__is_producing_wool

    @is_producing_wool.setter
    def is_producing_wool(self, is_producing_wool: bool):
        self.__is_producing_wool = is_producing_wool

    @property
    def kg_of_wool_for_month(self) -> float:
        return self.__kg_of_wool_for_month

    @kg_of_wool_for_month.setter
    def kg_of_wool_for_month(self, kg_of_wool_for_month: float):
        self.__kg_of_wool_for_month = kg_of_wool_for_month