from datetime import date
from model.farm_animal import FarmAnimal

# @author David Clavón, Infinite Loops, @ESPE
class Cow(FarmAnimal):
    def __init__(self, is_producing_milk: bool, liters_of_milk_for_week: float, id: int, breed: str, born_on_date: date):
        super().__init__(id, breed, born_on_date)
        self.__is_producing_milk = is_producing_milk
        self.__liters_of_milk_for_week = liters_of_milk_for_week

    def milk(self):
        print("milking the cow")

    def __str__(self) -> str:
        return f"Cow{{{super().__str__()}isProducingMilk={self.__is_producing_milk}, litersOfMilkForWeek={self.__liters_of_milk_for_week}}}"

    @property
    def is_producing_milk(self) -> bool:
        return self.__is_producing_milk

    @is_producing_milk.setter
    def is_producing_milk(self, is_producing_milk: bool):
        self.__is_producing_milk = is_producing_milk

    @property
    def liters_of_milk_for_week(self) -> float:
        return self.__liters_of_milk_for_week

    @liters_of_milk_for_week.setter
    def liters_of_milk_for_week(self, liters_of_milk_for_week: float):
        self.__liters_of_milk_for_week = liters_of_milk_for_week