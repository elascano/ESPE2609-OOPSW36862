from datetime import datetime
from model.JavaDate import java_bool, java_date_text


class FarmAnimal:
    def __init__(self, id: int, breed: str, bornOnDate: datetime, isMeat: bool):
        self.id = id
        self.breed = breed
        self.bornOnDate = bornOnDate
        self.isMeat = isMeat

    def getId(self) -> int:
        return self.id

    def setId(self, id: int) -> None:
        self.id = id

    def getBreed(self) -> str:
        return self.breed

    def setBreed(self, breed: str) -> None:
        self.breed = breed

    def getBornOnDate(self) -> datetime:
        return self.bornOnDate

    def setBornOnDate(self, bornOnDate: datetime) -> None:
        self.bornOnDate = bornOnDate

    def isIsMeat(self) -> bool:
        return self.isMeat

    def setIsMeat(self, isMeat: bool) -> None:
        self.isMeat = isMeat

    def __str__(self) -> str:
        return (f"FarmAnimal{{id={self.id}, breed={self.breed}, "
                f"is meat={java_bool(self.isMeat)}, bornOnDate={java_date_text(self.bornOnDate)}" 
                "}, ")
