from abc import ABC
from datetime import date


class FarmAnimal(ABC):
    """
    @author Dennis, Byte & Fabric Devs, @ESPE
    """

    def __init__(self, id: int, breed: str, bornOnDate: date, isMeat: bool):
        # Igual que una clase abstracta en Java: no se puede instanciar directamente
        if type(self) is FarmAnimal:
            raise TypeError("Can't instantiate abstract class FarmAnimal")
        self.__id = id
        self.__breed = breed
        self.__bornOnDate = bornOnDate
        self.__isMeat = isMeat

    def __str__(self) -> str:
        return ("FarmAnimal{" + "id=" + str(self.getId()) + ", breed=" + self.getBreed()
                + ", is meat=" + str(self.isIsMeat()).lower()
                + ", bornOnDate=" + str(self.getBornOnDate().day) + " / "
                + str(self.getBornOnDate().month) + " / " + str(self.getBornOnDate().year)
                + "}, ")

    def getId(self) -> int:
        """@return the id"""
        return self.__id

    def setId(self, id: int) -> None:
        """@param id the id to set"""
        self.__id = id

    def getBreed(self) -> str:
        """@return the breed"""
        return self.__breed

    def setBreed(self, breed: str) -> None:
        """@param breed the breed to set"""
        self.__breed = breed

    def getBornOnDate(self) -> date:
        """@return the bornOnDate"""
        return self.__bornOnDate

    def setBornOnDate(self, bornOnDate: date) -> None:
        """@param bornOnDate the bornOnDate to set"""
        self.__bornOnDate = bornOnDate

    def isIsMeat(self) -> bool:
        """@return the isMeat"""
        return self.__isMeat

    def setIsMeat(self, isMeat: bool) -> None:
        """@param isMeat the isMeat to set"""
        self.__isMeat = isMeat
