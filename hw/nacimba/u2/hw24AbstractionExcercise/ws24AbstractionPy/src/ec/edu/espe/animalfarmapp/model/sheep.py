"""
@author Mateo, Group5, @ESPE
"""
from datetime import datetime

from ec.edu.espe.animalfarmapp.model.farm_animal import FarmAnimal


class Sheep(FarmAnimal):
    def __init__(self, is_sheared: bool, last_sheering: datetime, id: int, breed: str,
                 born_on_date: datetime, is_meat: bool) -> None:
        super().__init__(id, breed, born_on_date, is_meat)
        self._is_sheared = is_sheared
        self._last_sheering = last_sheering

    def __str__(self) -> str:
        return ('Sheep{' + super().__str__() +
                f'isSheared={str(self._is_sheared).lower()}, '
                f'lastSheering={self._last_sheering.day}/{self._last_sheering.month}/{self._last_sheering.year}' + '}')

    @property
    def is_sheared(self) -> bool:
        return self._is_sheared

    @is_sheared.setter
    def is_sheared(self, value: bool) -> None:
        self._is_sheared = value

    @property
    def last_sheering(self) -> datetime:
        return self._last_sheering

    @last_sheering.setter
    def last_sheering(self, value: datetime) -> None:
        self._last_sheering = value
