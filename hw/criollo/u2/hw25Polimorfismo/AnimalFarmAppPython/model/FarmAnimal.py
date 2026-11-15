from abc import ABC, abstractmethod
from datetime import date


class FarmAnimal(ABC):
    def __init__(self, animal_id: int, breed: str, born_on_date: date, is_meat: bool):
        self.id = animal_id
        self.breed = breed
        self.bornOnDate = born_on_date
        self.isMeat = is_meat

    @abstractmethod
    def feed(self, food, amount: int):
        raise NotImplementedError

    def getAgeInMonths(self) -> int:
        today = date.today()
        return (today.year - self.bornOnDate.year) * 12 + today.month - self.bornOnDate.month

    def __str__(self) -> str:
        born_date = self.bornOnDate
        return (f"FarmAnimal{{id={self.id}, breed={self.breed}, is meat={str(self.isMeat).lower()}, "
                f"bornOnDate={born_date.day} / {born_date.month} / {born_date.year}}}, ")
