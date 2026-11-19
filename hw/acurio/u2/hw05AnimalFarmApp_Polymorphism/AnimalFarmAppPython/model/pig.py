from model.farm_animal import FarmAnimal
from model.sell_animal import SellAnimal


class Pig(FarmAnimal, SellAnimal):
    def __init__(self, isWallowing, weightGainPerWeek, id, breed, bornOnDate, isMeat):
        super().__init__(id, breed, bornOnDate, isMeat)
        self._isWallowing = isWallowing
        self._weightGainPerWeek = weightGainPerWeek

    def showPigMeatCuts(self):
        meatCuts = ["head", "cheek", "picnic", "clear plate", "boston shoulder",
                    "back fat", "loin", "ribs", "bacon", "leg", "ham", "hock"]
        print("Avalaible cuts for the pig[" + ", ".join(meatCuts) + "]")

    def feed(self, food, amount):
        print(f"feeding the pig with {amount} kg of {food}")

    def sellAnimal(self, price):
        print(f"sold the pig in {price} dollars.")

    def __str__(self):
        return (f"Pig{{{super().__str__()}"
                f"isWallowing={str(self._isWallowing).lower()}, "
                f"weightGainPerWeek={self._weightGainPerWeek}}}")

    def isIsWallowing(self):
        return self._isWallowing

    def setIsWallowing(self, isWallowing):
        self._isWallowing = isWallowing

    def getWeightGainPerWeek(self, weightGain):
        print(f"the pig has gained {weightGain} kg of weight")

    def setWeightGainPerWeek(self, weightGainPerWeek):
        self._weightGainPerWeek = weightGainPerWeek
