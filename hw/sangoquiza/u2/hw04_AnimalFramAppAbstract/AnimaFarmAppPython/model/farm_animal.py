from abc import ABC, abstractmethod
from datetime import datetime, date
from model.food import Food

class FarmAnimal(ABC):
    """
    Clase abstracta FarmAnimal traducida de ec.edu.espe.model.FarmAnimal
    @author Daniel Sangoquiza, SaborCode
    """
    def __init__(self, id: int, breed: str, born_on_date: date | datetime):
        self._id = id
        self._breed = breed
        self._born_on_date = born_on_date

    @abstractmethod
    def feed(self, food: Food, amount_of_food: int) -> None:
        """Método abstracto que deben implementar las clases hijas."""
        pass

    def get_age_in_months(self) -> int:
        return 0

    @property
    def id(self) -> int:
        return self._id

    @id.setter
    def id(self, value: int):
        self._id = value

    @property
    def breed(self) -> str:
        return self._breed

    @breed.setter
    def breed(self, value: str):
        self._breed = value

    @property
    def born_on_date(self) -> date | datetime:
        return self._born_on_date

    @born_on_date.setter
    def born_on_date(self, value: date | datetime):
        self._born_on_date = value

    def __str__(self) -> str:
        # Equivalente fiel a: (bornOnDate.getDate()+1) + "/" + bornOnDate.getMonth() + "/" + (bornOnDate.getYear()+1900)
        day = self.born_on_date.day + 1
        month = self.born_on_date.month - 1
        year = self.born_on_date.year
        return f"id = {self.id}, breed = {self.breed}, bornOnDate = {day}/{month}/{year}, "