from abc import ABC, abstractmethod
from datetime import date

from model.food import Food


class FarmAnimal(ABC):
    def __init__(self, animal_id: int, breed: str, born_on_date: date | None):
        self.animal_id = animal_id
        self.breed = breed
        self.born_on_date = born_on_date

    @abstractmethod
    def feed(self, food: Food, amount: int) -> str:
        pass

    def get_age_in_months(self) -> int:
        today = date.today()
        birth_date = self.born_on_date
        if birth_date is None or birth_date > today:
            return 0

        months = (today.year - birth_date.year) * 12 + today.month - birth_date.month
        if today.day < birth_date.day:
            months -= 1
        return months

    def __str__(self) -> str:
        birth_date = self.born_on_date
        formatted_date = birth_date.strftime("%d/%m/%Y") if birth_date else "Unknown"
        return f"ID: {self.animal_id}, Breed: {self.breed}, Birth date: {formatted_date}"
