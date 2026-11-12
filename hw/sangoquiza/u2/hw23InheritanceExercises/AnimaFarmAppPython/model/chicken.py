from datetime import datetime, date
from model.farm_animal import FarmAnimal

class Chicken(FarmAnimal):
    """
    Clase Chicken traducida de ec.edu.espe.animalfarmapp.model.Chicken
    """
    def __init__(self, is_molting: bool, number_of_laid_eggs_per_week: int, id: int, breed: str, born_on_date: date | datetime):
        super().__init__(id, breed, born_on_date, is_molting)
        self._is_molting = is_molting
        self._number_of_laid_eggs_per_week = number_of_laid_eggs_per_week

    @property
    def is_molting(self) -> bool:
        return self._is_molting

    @is_molting.setter
    def is_molting(self, value: bool):
        self._is_molting = value

    def is_is_molting(self) -> bool:
        return self.is_molting

    @property
    def number_of_laid_eggs_per_week(self) -> int:
        return self._number_of_laid_eggs_per_week

    @number_of_laid_eggs_per_week.setter
    def number_of_laid_eggs_per_week(self, value: int):
        self._number_of_laid_eggs_per_week = value

    def get_number_of_laid_eggs_per_week(self) -> int:
        return self.number_of_laid_eggs_per_week

    def __str__(self) -> str:
        return f"Chicken{{{super().__str__()}isMolting={self.is_molting}, numberOfLaidEggsPerWeek={self.number_of_laid_eggs_per_week}}}"