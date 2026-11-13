"""
@author Ostin Torres, CodeLex, @ESPE
"""
from datetime import date

from ec.edu.espe.animalfarmapp.model.farm_animal import FarmAnimal


class Cow(FarmAnimal):

    def __init__(self, is_producing_milk: bool, liters_of_milk_per_week: float,
                 id: int, breed: str, born_on_date: date, is_meat: bool):
        super().__init__(id, breed, born_on_date, is_meat)
        self.__is_producing_milk = is_producing_milk
        self.__liters_of_milk_per_week = liters_of_milk_per_week

    def __str__(self) -> str:
        return ("Cow{" + super().__str__() + "isProducingMilk=" + str(self.is_producing_milk).lower()
                + ", litersOfMilkPerWeek=" + str(self.liters_of_milk_per_week) + "}")

    @property
    def is_producing_milk(self) -> bool:
        """the isProducingMilk"""
        return self.__is_producing_milk

    @is_producing_milk.setter
    def is_producing_milk(self, is_producing_milk: bool) -> None:
        self.__is_producing_milk = is_producing_milk

    @property
    def liters_of_milk_per_week(self) -> float:
        """the litersOfMilkPerWeek"""
        return self.__liters_of_milk_per_week

    @liters_of_milk_per_week.setter
    def liters_of_milk_per_week(self, liters_of_milk_per_week: float) -> None:
        self.__liters_of_milk_per_week = liters_of_milk_per_week
