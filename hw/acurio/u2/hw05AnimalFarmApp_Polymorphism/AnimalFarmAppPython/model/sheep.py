from model.farm_animal import FarmAnimal
from model.graze_animal_i import GrazeAnimalI


class Sheep(FarmAnimal, GrazeAnimalI):
    def __init__(self, isSheared, lastSheering, id, breed, bornOnDate, isMeat):
        super().__init__(id, breed, bornOnDate, isMeat)
        self._isSheared = isSheared
        self._lastSheering = lastSheering

    def shearSheep(self):
        print("Sheep is sheared")

    def feed(self, food, amount):
        print(f"feeding the sheep with {amount} kg of {food}")

    def sendToGraze(self, location, date):
        print(f"send the sheep to {location} at {date.hour} : {date.minute}")

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
