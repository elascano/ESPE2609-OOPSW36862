from datetime import datetime
from model.FarmAnimal import FarmAnimal
from model.JavaDate import java_bool, java_date_to_string


class Sheep(FarmAnimal):
    def __init__(self, isSheared: bool, lastSheering: datetime, id: int, breed: str, bornOnDate: datetime, isMeat: bool):
        super().__init__(id, breed, bornOnDate, isMeat)
        self.isSheared = isSheared
        self.lastSheering = lastSheering

    def isIsSheared(self) -> bool:
        return self.isSheared

    def setIsSheared(self, value: bool) -> None:
        self.isSheared = value

    def getLastSheering(self) -> datetime:
        return self.lastSheering

    def setLastSheering(self, value: datetime) -> None:
        self.lastSheering = value

    def __str__(self) -> str:
        return ("Sheep{" + super().__str__() + f"isSheared={java_bool(self.isSheared)}, lastSheering={java_date_to_string(self.lastSheering)}" + "}")
