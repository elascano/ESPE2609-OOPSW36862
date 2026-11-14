"""
@author Mateo, Group5, @ESPE
"""
from datetime import datetime

from ec.edu.espe.animalfarmapp.model.farm_animal import FarmAnimal


class Pig(FarmAnimal):
    def __init__(self, is_wallowing: bool, weight_gain_per_week: float, id: int,
                 breed: str, born_on_date: datetime) -> None:
        super().__init__(id, breed, born_on_date)
        self._is_wallowing = is_wallowing
        self._weight_gain_per_week = weight_gain_per_week

    def __str__(self) -> str:
        return ('Pig{' + super().__str__() +
                f'isWallowing={str(self._is_wallowing).lower()}, '
                f'weightGainPerWeek={self._weight_gain_per_week}' + '}')

    @property
    def is_wallowing(self) -> bool:
        return self._is_wallowing

    @is_wallowing.setter
    def is_wallowing(self, value: bool) -> None:
        self._is_wallowing = value

    @property
    def weight_gain_per_week(self) -> float:
        return self._weight_gain_per_week

    @weight_gain_per_week.setter
    def weight_gain_per_week(self, value: float) -> None:
        self._weight_gain_per_week = value
