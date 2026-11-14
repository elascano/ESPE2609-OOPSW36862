"""
@author Mateo, Group5, @ESPE
"""
from abc import ABC
from datetime import datetime


class FarmAnimal(ABC):  # ABC = Abstract Base Class (clase abstracta)
    def __new__(cls, *args, **kwargs):
        # Igual que "abstract" en Java: no se puede hacer FarmAnimal(...)
        if cls is FarmAnimal:
            raise TypeError('FarmAnimal es abstracta: no se puede instanciar')
        return super().__new__(cls)

    def __init__(self, id: int, breed: str, born_on_date: datetime,
                 is_meat: bool) -> None:
        self._id = id
        self._breed = breed
        self._born_on_date = born_on_date
        self._is_meat = is_meat

    def __str__(self) -> str:
        d = self._born_on_date
        return (f'FarmAnimal{{id={self._id}, breed={self._breed}, '
                f'is meat={str(self._is_meat).lower()}, '
                f'bornOnDate={d.day} / {d.month} / {d.year}}}, ')

    @property
    def id(self) -> int:
        return self._id

    @id.setter
    def id(self, value: int) -> None:
        self._id = value

    @property
    def breed(self) -> str:
        return self._breed

    @breed.setter
    def breed(self, value: str) -> None:
        self._breed = value

    @property
    def born_on_date(self) -> datetime:
        return self._born_on_date

    @born_on_date.setter
    def born_on_date(self, value: datetime) -> None:
        self._born_on_date = value

    @property
    def is_meat(self) -> bool:
        return self._is_meat

    @is_meat.setter
    def is_meat(self, value: bool) -> None:
        self._is_meat = value
