from datetime import date

from model.farm_animal import FarmAnimal


class Pig(FarmAnimal):
    """
    @author Dennis, Bytes & Fabric Devs, @ESPE
    """

    def __init__(self, isWallowing: bool, weightGainPerWeek: float, id: int, breed: str,
                 bornOnDate: date, isMeat: bool):
        super().__init__(id, breed, bornOnDate, isMeat)
        self.__isWallowing = isWallowing
        self.__weightGainPerWeek = weightGainPerWeek

    def __str__(self) -> str:
        return ("Pig{" + super().__str__() + "isWallowing=" + str(self.__isWallowing).lower()
                + ", weightGainPerWeek=" + str(self.__weightGainPerWeek) + "}")

    def isIsWallowing(self) -> bool:
        """@return the isWallowing"""
        return self.__isWallowing

    def setIsWallowing(self, isWallowing: bool) -> None:
        """@param isWallowing the isWallowing to set"""
        self.__isWallowing = isWallowing

    def getWeightGainPerWeek(self) -> float:
        """@return the weightGainPerWeek"""
        return self.__weightGainPerWeek

    def setWeightGainPerWeek(self, weightGainPerWeek: float) -> None:
        """@param weightGainPerWeek the weightGainPerWeek to set"""
        self.__weightGainPerWeek = weightGainPerWeek
