from abc import ABC, abstractmethod
from datetime import date

class FarmAnimal(ABC):
    def __init__(self, id: int, breed: str, born_on_date: date):
        self.id = id
        self.breed = breed
        self.born_on_date = born_on_date

    def __str__(self) -> str:
        date_str = (
            self.born_on_date.strftime('%d/%m/%Y')
            if isinstance(self.born_on_date, date)
            else str(self.born_on_date)
        )
        return f"id={self.id}, breed={self.breed}, bornOnDate={date_str}"

    @abstractmethod
    def feed(self):
        pass