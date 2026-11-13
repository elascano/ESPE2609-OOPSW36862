from model.farm_animal import FarmAnimal


class Sheep(FarmAnimal):
    def __init__(self, isSheared, lastSheering, id, breed, bornOnDate, isMeat):
        super().__init__(id, breed, bornOnDate, isMeat)
        self._isSheared = isSheared
        self._lastSheering = lastSheering

    def shearSheep(self):
        print("Sheep is sheared")

    def __str__(self):
        return (f"Sheep{{{super().__str__()}"
                f"isSheared={str(self._isSheared).lower()}, "
                f"lastSheering={self._lastSheering}}}")

    def isIsSheared(self):
        return self._isSheared

    def setIsSheared(self, isSheared):
        self._isSheared = isSheared

    def getLastSheering(self):
        return self._lastSheering

    def setLastSheering(self, lastSheering):
        self._lastSheering = lastSheering
