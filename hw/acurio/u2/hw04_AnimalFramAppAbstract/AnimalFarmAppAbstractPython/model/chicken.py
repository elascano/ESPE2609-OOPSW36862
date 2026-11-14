from model.farm_animal import FarmAnimal


class Chicken(FarmAnimal):
    def __init__(self, isMolting, numberOfLaidEggsPerWeek, id, breed, bornOnDate):
        super().__init__(id, breed, bornOnDate, isMolting)
        self._isMolting = isMolting
        self._numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek

    def layAnEgg(self):
        print("chicken is laying an egg")

    def __str__(self):
        return (f"Chicken{{{super().__str__()}"
                f"isMolting={str(self._isMolting).lower()}, "
                f"numberOfLaidEggsPerWeek={self._numberOfLaidEggsPerWeek}}}")

    def isIsMolting(self):
        return self._isMolting

    def setIsMolting(self, isMolting):
        self._isMolting = isMolting

    def getNumberOfLaidEggsPerWeek(self):
        return self._numberOfLaidEggsPerWeek

    def setNumberOfLaidEggsPerWeek(self, numberOfLaidEggsPerWeek):
        self._numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek
