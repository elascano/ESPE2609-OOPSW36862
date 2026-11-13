from model.farm_animal import FarmAnimal
from datetime import date

class Sheep(FarmAnimal):
    def __init__(self, last_sheering: date, is_producing_wool: bool, is_meat: bool, id: int, breed: str, born_on_date: date):
        super().__init__(id, breed, born_on_date)
        self.last_sheering = last_sheering
        self.is_producing_wool = is_producing_wool
        self.is_meat = is_meat

    def __str__(self) -> str:
        last_sheering_str = self.last_sheering.strftime('%d/%m/%Y') if isinstance(self.last_sheering, date) else str(self.last_sheering)
        return f"Sheep{{{super().__str__()},lastSheering={last_sheering_str}, isProducingWool={self.is_producing_wool}, isMeat={self.is_meat}}}"

    def feed(self):
        print(f"Sheep (ID: {self.id}) is grazing.")