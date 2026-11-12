from datetime import datetime
from model.FarmAnimal import FarmAnimal
from model.JavaDate import java_bool


class Chicken(FarmAnimal):
    def __init__(self, isMolting: bool, numberOfLaidEggsPerWeek: int, id: int, breed: str, bornOnDate: datetime):
        super().__init__(id, breed, bornOnDate, isMolting)
        self.isMolting = isMolting
        self.numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek

    def isIsMolting(self) -> bool:
        return self.isMolting

    def setIsMolting(self, value: bool) -> None:
        self.isMolting = value

    def getNumberOfLaidEggsPerWeek(self) -> int:
        return self.numberOfLaidEggsPerWeek

    def setNumberOfLaidEggsPerWeek(self, value: int) -> None:
        self.numberOfLaidEggsPerWeek = value

    def __str__(self) -> str:
        return ("Chicken{" + super().__str__() + f"isMolting={java_bool(self.isMolting)}, numberOfLaidEggsPerWeek={self.numberOfLaidEggsPerWeek}" + "}")
