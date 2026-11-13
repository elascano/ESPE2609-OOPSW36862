from model.farm_animal import FarmAnimal


class Cow(FarmAnimal):
    def __init__(self, isProducingMilk, litersOfMilkPerWeek, id, breed, bornOnDate, isMeat):
        super().__init__(id, breed, bornOnDate, isMeat)
        self._isProducingMilk = isProducingMilk
        self._litersOfMilkPerWeek = litersOfMilkPerWeek

    def milk(self):
        print("cow is producing milk")

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
