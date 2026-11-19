"""Author: Mateo Nacimba, @ESPE"""
from abc import ABC, abstractmethod
from datetime import datetime

from .Food import Food


class FarmAnimal(ABC):
    def __init__(self, id: int, breed: str, born_on_date: datetime, is_meat: bool):
        self.id = id
        self.breed = breed
        self.born_on_date = born_on_date
        self.is_meat = is_meat

    @abstractmethod
    def feed(self, food: Food, amount: int) -> None:
        """Feed the animal with an amount of food."""

    def get_age_in_months(self) -> int:
        return 0

    def __str__(self) -> str:
        d = self.born_on_date
        return (f"FarmAnimal{{id={self.id}, breed={self.breed}, is meat={self.is_meat}, "
                f"bornOnDate={d.day} / {d.month} / {d.year}}}, ")
