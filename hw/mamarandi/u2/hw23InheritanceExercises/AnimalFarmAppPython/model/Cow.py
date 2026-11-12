from datetime import datetime
from model.FarmAnimal import FarmAnimal
from model.JavaDate import java_bool


class Cow(FarmAnimal):
    def __init__(self, isProducingMilk: bool, litersOfMilkPerWeek: float, id: int, breed: str, bornOnDate: datetime, isMeat: bool):
        super().__init__(id, breed, bornOnDate, isMeat)
        self.isProducingMilk = isProducingMilk
        self.litersOfMilkPerWeek = litersOfMilkPerWeek

    def isIsProducingMilk(self) -> bool:
        return self.isProducingMilk

    def setIsProducingMilk(self, value: bool) -> None:
        self.isProducingMilk = value

    def getLitersOfMilkPerWeek(self) -> float:
        return self.litersOfMilkPerWeek

    def setLitersOfMilkPerWeek(self, value: float) -> None:
        self.litersOfMilkPerWeek = value

    def __str__(self) -> str:
        return ("Cow{" + super().__str__() + f"isProducingMilk={java_bool(self.isProducingMilk)}, litersOfMilkPerWeek={self.litersOfMilkPerWeek}" + "}")
