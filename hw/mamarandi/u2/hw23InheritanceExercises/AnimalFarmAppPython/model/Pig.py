from datetime import datetime
from model.FarmAnimal import FarmAnimal
from model.JavaDate import java_bool


class Pig(FarmAnimal):
    def __init__(self, isWallowing: bool, weightGainPerWeek: float, id: int, breed: str, bornOnDate: datetime, isMeat: bool):
        super().__init__(id, breed, bornOnDate, isMeat)
        self.isWallowing = isWallowing
        self.weightGainPerWeek = weightGainPerWeek

    def isIsWallowing(self) -> bool:
        return self.isWallowing

    def setIsWallowing(self, value: bool) -> None:
        self.isWallowing = value

    def getWeightGainPerWeek(self) -> float:
        return self.weightGainPerWeek

    def setWeightGainPerWeek(self, value: float) -> None:
        self.weightGainPerWeek = value

    def __str__(self) -> str:
        return ("Pig{" + super().__str__() + f"isWallowing={java_bool(self.isWallowing)}, weightGainPerWeek={self.weightGainPerWeek}" + "}")
