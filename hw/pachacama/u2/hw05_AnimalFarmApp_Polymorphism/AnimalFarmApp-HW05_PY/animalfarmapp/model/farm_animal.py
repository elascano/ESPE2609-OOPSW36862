# @author Andrés Pachacama, ByteWorks, @ESPE
from abc import ABC, abstractmethod
from datetime import date

from animalfarmapp.model.food import Food


class FarmAnimal(ABC):

    def __init__(self, id: int, breed: str, born_on_date: date, is_meat: bool):
        self._id = id
        self._breed = breed
        self._born_on_date = born_on_date
        self._is_meat = is_meat

    @abstractmethod
    def feed(self, food: Food, amount: int):
        pass

    def get_age_in_months(self) -> int:
        return 0

    def __str__(self) -> str:
        born = self.born_on_date
        return ("FarmAnimal{" + "id=" + str(self.id) + ", breed=" + self.breed
                + ", is meat=" + str(self.is_meat).lower()
                + ", bornOnDate=" + f"{born.day} / {born.month} / {born.year}"
                + "}, ")

    @property
    def id(self) -> int:
        return self._id

    @id.setter
    def id(self, id: int):
        self._id = id

    @property
    def breed(self) -> str:
        return self._breed

    @breed.setter
    def breed(self, breed: str):
        self._breed = breed

    @property
    def born_on_date(self) -> date:
        return self._born_on_date

    @born_on_date.setter
    def born_on_date(self, born_on_date: date):
        self._born_on_date = born_on_date

    @property
    def is_meat(self) -> bool:
        return self._is_meat

    @is_meat.setter
    def is_meat(self, is_meat: bool):
        self._is_meat = is_meat
