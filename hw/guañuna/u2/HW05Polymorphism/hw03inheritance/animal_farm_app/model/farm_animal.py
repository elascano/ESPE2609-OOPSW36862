from abc import ABC, abstractmethod
from datetime import date
from .food import Food

class FarmAnimal(ABC):
    def __init__(self, id: int, breed: str, born_on_date: date):
        self._id = id
        self._breed = breed
        self._born_on_date = born_on_date

    @abstractmethod
    def feed(self, food: Food, amount: int):
        pass

    def get_age_in_months(self) -> int:
        if not self._born_on_date:
            return 0
        today = date.today()
        months = (today.year - self._born_on_date.year) * 12
        months += today.month - self._born_on_date.month
        return months

    @property
    def id(self) -> int: return self._id
    @id.setter
    def id(self, value: int): self._id = value

    @property
    def breed(self) -> str: return self._breed
    @breed.setter
    def breed(self, value: str): self._breed = value

    @property
    def born_on_date(self) -> date: return self._born_on_date
    @born_on_date.setter
    def born_on_date(self, value: date): self._born_on_date = value

    def __str__(self) -> str:
        return f"id = {self._id}, breed = {self._breed}, bornOnDate = {self._born_on_date.day}/{self._born_on_date.month}/{self._born_on_date.year}, "