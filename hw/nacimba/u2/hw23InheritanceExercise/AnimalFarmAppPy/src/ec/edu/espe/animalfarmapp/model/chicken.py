"""
@author Mateo, Group5, @ESPE
"""
from datetime import datetime

from ec.edu.espe.animalfarmapp.model.farm_animal import FarmAnimal


class Chicken(FarmAnimal):
    def __init__(self, is_molting: bool, number_of_laid_eggs_in_a_month: int,
                 is_meat: bool, id: int, breed: str, born_on_date: datetime) -> None:
        super().__init__(id, breed, born_on_date)
        self._is_molting = is_molting
        self._number_of_laid_eggs_in_a_month = number_of_laid_eggs_in_a_month
        self._is_meat = is_meat

    def __str__(self) -> str:
        return ('Chicken{' + super().__str__() +
                f'isMolting={str(self._is_molting).lower()}, '
                f'numberOfLaidEggsInAMonth={self._number_of_laid_eggs_in_a_month}, '
                f'isMeat={str(self._is_meat).lower()}' + '}')

    @property
    def is_molting(self) -> bool:
        return self._is_molting

    @is_molting.setter
    def is_molting(self, value: bool) -> None:
        self._is_molting = value

    @property
    def number_of_laid_eggs_in_a_month(self) -> int:
        return self._number_of_laid_eggs_in_a_month

    @number_of_laid_eggs_in_a_month.setter
    def number_of_laid_eggs_in_a_month(self, value: int) -> None:
        self._number_of_laid_eggs_in_a_month = value

    @property
    def is_meat(self) -> bool:
        return self._is_meat

    @is_meat.setter
    def is_meat(self, value: bool) -> None:
        self._is_meat = value
