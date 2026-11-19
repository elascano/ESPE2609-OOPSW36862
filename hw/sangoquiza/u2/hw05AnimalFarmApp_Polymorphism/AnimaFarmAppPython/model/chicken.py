from datetime import datetime, date
from model.farm_animal import FarmAnimal
from model.food import Food

class Chicken(FarmAnimal):
    """
    Clase Chicken traducida de ec.edu.espe.model.Chicken
    @author Daniel Sangoquiza, SaborCode
    """
    def __init__(self, is_molting: bool, number_of_laid_eggs_in_a_month: int, is_meat: bool, id: int, breed: str, born_on_date: date | datetime):
        super().__init__(id, breed, born_on_date)
        self._is_molting = is_molting
        self._number_of_laid_eggs_in_a_month = number_of_laid_eggs_in_a_month
        self._is_meat = is_meat

    def feed(self, food: Food, amount_of_food: int) -> None:
        print(f"feeding a chicken with --->{amount_of_food}of {food}")

    def lay_an_egg(self) -> None:
        print("Chicken is laying an egg () ")

    @property
    def is_molting(self) -> bool:
        return self._is_molting

    @is_molting.setter
    def is_molting(self, value: bool):
        self._is_molting = value

    def is_is_molting(self) -> bool:
        return self.is_molting

    @property
    def number_of_laid_eggs_in_a_month(self) -> int:
        return self._number_of_laid_eggs_in_a_month

    @number_of_laid_eggs_in_a_month.setter
    def number_of_laid_eggs_in_a_month(self, value: int):
        self._number_of_laid_eggs_in_a_month = value

    def get_number_of_laid_eggs_in_a_month(self) -> int:
        return self.number_of_laid_eggs_in_a_month

    @property
    def is_meat(self) -> bool:
        return self._is_meat

    @is_meat.setter
    def is_meat(self, value: bool):
        self._is_meat = value

    def is_is_meat(self) -> bool:
        return self.is_meat

    def __str__(self) -> str:
        return f"Chicken{{{super().__str__()}isMolting={self.is_molting}, numberOfLaidEggsInAMonth={self.number_of_laid_eggs_in_a_month}, isMeat={self.is_meat}}}"