from datetime import date
from model.farm_animal import FarmAnimal

class Pig(FarmAnimal):
    def __init__(self, weight, isMeat, id, breed, bornOnDate):
        super().__init__(id, breed, bornOnDate)
        self.weight = weight
        self.isMeat = isMeat

    def __str__(self):
        return f"Pig{{{super().__str__()}, weight={self.weight}, isMeat={self.isMeat}}}"