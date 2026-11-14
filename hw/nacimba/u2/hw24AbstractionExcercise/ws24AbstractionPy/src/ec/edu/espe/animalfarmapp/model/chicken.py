"""
@author Mateo, Group5, @ESPE
"""
from datetime import datetime

from ec.edu.espe.animalfarmapp.model.farm_animal import FarmAnimal


class Chicken(FarmAnimal):
    def __init__(self, is_molting: bool, number_of_laid_eggs_per_week: int, id: int,
                 breed: str, born_on_date: datetime) -> None:
        super().__init__(id, breed, born_on_date, is_molting)
        self._is_molting = is_molting
        self._number_of_laid_eggs_per_week = number_of_laid_eggs_per_week

    def __str__(self) -> str:
        return ('Chicken{' + super().__str__() +
                f'isMolting={str(self._is_molting).lower()}, '
                f'numberOfLaidEggsPerWeek={self._number_of_laid_eggs_per_week}' + '}')

    def lay_an_egg(self) -> None:
        print('chicken is laying an egg')

    @property
    def is_molting(self) -> bool:
        return self._is_molting

    @is_molting.setter
    def is_molting(self, value: bool) -> None:
        self._is_molting = value

    @property
    def number_of_laid_eggs_per_week(self) -> int:
        return self._number_of_laid_eggs_per_week

    @number_of_laid_eggs_per_week.setter
    def number_of_laid_eggs_per_week(self, value: int) -> None:
        self._number_of_laid_eggs_per_week = value
