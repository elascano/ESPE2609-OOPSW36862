from model.farm_animal import FarmAnimal


class Pig(FarmAnimal):
    def __init__(self, isWallowing, weightGainPerWeek, id, breed, bornOnDate, isMeat):
        super().__init__(id, breed, bornOnDate, isMeat)
        self._isWallowing = isWallowing
        self._weightGainPerWeek = weightGainPerWeek

    def showPigMeatCuts(self):
        meatCuts = ["head", "cheek", "picnic", "clear plate", "boston shoulder",
                    "back fat", "loin", "ribs", "bacon", "leg", "ham", "hock"]
        print("Avalaible cuts for the pig[" + ", ".join(meatCuts) + "]")

    def __str__(self):
        return (f"Pig{{{super().__str__()}"
                f"isWallowing={str(self._isWallowing).lower()}, "
                f"weightGainPerWeek={self._weightGainPerWeek}}}")

    def isIsWallowing(self):
        return self._isWallowing

    def setIsWallowing(self, isWallowing):
        self._isWallowing = isWallowing

    def getWeightGainPerWeek(self):
        return self._weightGainPerWeek

    def setWeightGainPerWeek(self, weightGainPerWeek):
        self._weightGainPerWeek = weightGainPerWeek
