from datetime import date
from .farm_animal import FarmAnimal

class Pig(FarmAnimal):
    def __init__(self, id: int, breed: str, born_on_date: date):
        super().__init__(id, breed, born_on_date)

    def __str__(self) -> str:
        return f"Pig{{{super().__str__()}}}"