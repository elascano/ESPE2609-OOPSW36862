from datetime import date
from model.farm_animal import FarmAnimal

class Sheep(FarmAnimal):
    def __init__(self, lastSheering, isProducingWool, isMeat, id, breed, bornOnDate):
        super().__init__(id, breed, bornOnDate)
        self.lastSheering = lastSheering
        self.isProducingWool = isProducingWool
        self.isMeat = isMeat

    def shear(self):
        print("shearing the sheep")

    def __str__(self):
        date_str = self.lastSheering.strftime("%d/%m/%Y") if isinstance(self.lastSheering, date) else str(self.lastSheering)
        return f"Sheep{{{super().__str__()}lastSheering={date_str}, isProducingWool={self.isProducingWool}, isMeat={self.isMeat}}}"