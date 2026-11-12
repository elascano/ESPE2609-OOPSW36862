from datetime import date
from model.farm_animal import FarmAnimal

# @author David Clavón, Infinite Loops, @ESPE
class Pig(FarmAnimal):
    def __init__(self, weight: float, is_for_meat: bool, id: int, breed: str, born_on_date: date):
        super().__init__(id, breed, born_on_date)
        self.__weight = weight
        self.__is_for_meat = is_for_meat

    def __str__(self) -> str:
        return f"Pig{{{super().__str__()}weight={self.__weight}, isForMeat={self.__is_for_meat}}}"

    @property
    def weight(self) -> float:
        return self.__weight

    @weight.setter
    def weight(self, weight: float):
        self.__weight = weight

    @property
    def is_for_meat(self) -> bool:
        return self.__is_for_meat

    @is_for_meat.setter
    def is_for_meat(self, is_for_meat: bool):
        self.__is_for_meat = is_for_meat