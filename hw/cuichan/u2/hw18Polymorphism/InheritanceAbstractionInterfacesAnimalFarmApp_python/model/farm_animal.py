from abc import ABC, abstractmethod
from datetime import date
from model.food import Food

class FarmAnimal(ABC):
    def __init__(self, id: int, breed: str, born_on_date: date):
        self.id = id
        self.breed = breed
        self.born_on_date = born_on_date

    @abstractmethod
    def feed(self, food: Food, amount_of_food: int):
        """Feed the animals with an amount of food"""
        pass

    @property
    def id_animal(self):
        return self.id

    @id_animal.setter
    def id_animal(self, id: int):
        self.id = id

    @property
    def breed_animal(self):
        return self.breed

    @breed_animal.setter
    def breed_animal(self, breed: str):
        self.breed = breed

    @property
    def born_on_date(self):
        return self._born_on_date

    @born_on_date.setter
    def born_on_date(self, born_on_date: date):
        self._born_on_date = born_on_date

    def __str__(self):
        day = self.born_on_date.day if hasattr(self.born_on_date, 'day') else 1
        month = self.born_on_date.month if hasattr(self.born_on_date, 'month') else 1
        year = self.born_on_date.year if hasattr(self.born_on_date, 'year') else 1900
        
        return f"id={self.id}, breed={self.breed}, bornOnDate={day}/{month}/{year},"

    def get_age_in_months(self):
        # Complete
        return 0