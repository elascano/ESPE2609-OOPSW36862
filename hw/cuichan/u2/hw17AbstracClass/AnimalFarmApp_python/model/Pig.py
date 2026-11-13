from model.farm_animal import FarmAnimal
from datetime import date

class Pig(FarmAnimal):
    def __init__(self, id: int, breed: str, born_on: date, weight: float, is_meat: bool):
        super().__init__(id, breed, born_on)
        self.weight = weight
        self.is_meat = is_meat

    def __str__(self) -> str:
        return f"Pig{{{super().__str__()}, weight={self.weight}, isMeat={self.is_meat}}}"

    def feed(self):
        print(f"Pig (ID: {self.id}) is eating feed.")