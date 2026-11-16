from model.farm_animal import FarmAnimal
from model.graze_animal_i import GrazeAnimalI


class Cow(FarmAnimal, GrazeAnimalI):
    def __init__(self, isProducingMilk, litersOfMilkPerWeek, id, breed, bornOnDate, isMeat):
        super().__init__(id, breed, bornOnDate, isMeat)
        self._isProducingMilk = isProducingMilk
        self._litersOfMilkPerWeek = litersOfMilkPerWeek

    def milk(self, liters=None):
        if liters is not None:
            self._litersOfMilkPerWeek += liters
            print(f"milking the cow... we got --->{liters} liters today")
        print(f"this cow has produced --->{self._litersOfMilkPerWeek}liters per week")

    def feed(self, food, amount):
        print(f"feeding the cow with {amount} of {food}")

    def sendToGraze(self, location, date):
        print(f"send the cow to {location} at {date.hour} : {date.minute}")

    def __str__(self):
        return (f"Cow{{{super().__str__()}"
                f"isProducingMilk={str(self._isProducingMilk).lower()}, "
                f"litersOfMilkPerWeek={self._litersOfMilkPerWeek}}}")

    def isIsProducingMilk(self):
        return self._isProducingMilk

    def setIsProducingMilk(self, isProducingMilk):
        self._isProducingMilk = isProducingMilk

    def getLitersOfMilkPerWeek(self):
        return self._litersOfMilkPerWeek

    def setLitersOfMilkPerWeek(self, litersOfMilkPerWeek):
        self._litersOfMilkPerWeek = litersOfMilkPerWeek
