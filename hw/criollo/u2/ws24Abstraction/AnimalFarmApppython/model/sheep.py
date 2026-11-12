from datetime import date

from model.farm_animal import FarmAnimal


class Sheep(FarmAnimal):
    """
    @author Dennis, Bytes & Fabric Devs, @ESPE
    """

    def __init__(self, isSheared: bool, lastSheering: date, id: int, breed: str,
                 bornOnDate: date, isMeat: bool):
        super().__init__(id, breed, bornOnDate, isMeat)
        self.__isSheared = isSheared
        self.__lastSheering = lastSheering

    def __str__(self) -> str:
        return ("Sheep{" + super().__str__() + "isSheared=" + str(self.__isSheared).lower()
                + ", lastSheering=" + str(self.__lastSheering) + "}")

    def isIsSheared(self) -> bool:
        """@return the isSheared"""
        return self.__isSheared

    def setIsSheared(self, isSheared: bool) -> None:
        """@param isSheared the isSheared to set"""
        self.__isSheared = isSheared

    def getLastSheering(self) -> date:
        """@return the lastSheering"""
        return self.__lastSheering

    def setLastSheering(self, lastSheering: date) -> None:
        """@param lastSheering the lastSheering to set"""
        self.__lastSheering = lastSheering
