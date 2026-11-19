from datetime import date

from model.farm_animal import FarmAnimal


class Chicken(FarmAnimal):
    """
    @author Dennis, Byte & Fabric Devs, @ESPE
    """

    def __init__(self, isMolting: bool, numberOfLaidEggsPerWeek: int, id: int, breed: str, bornOnDate: date):
        super().__init__(id, breed, bornOnDate, isMolting)
        self.__isMolting = isMolting
        self.__numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek

    def layAnEgg(self) -> None:
        print("chicken is laying an egg")

    def __str__(self) -> str:
        return ("Chicken{" + super().__str__() + "isMolting=" + str(self.__isMolting).lower()
                + ", numberOfLaidEggsPerWeek=" + str(self.__numberOfLaidEggsPerWeek) + "}")

    def isIsMolting(self) -> bool:
        """@return the isMolting"""
        return self.__isMolting

    def setIsMolting(self, isMolting: bool) -> None:
        """@param isMolting the isMolting to set"""
        self.__isMolting = isMolting

    def getNumberOfLaidEggsPerWeek(self) -> int:
        """@return the numberOfLaidEggsPerWeek"""
        return self.__numberOfLaidEggsPerWeek

    def setNumberOfLaidEggsPerWeek(self, numberOfLaidEggsPerWeek: int) -> None:
        """@param numberOfLaidEggsPerWeek the numberOfLaidEggsPerWeek to set"""
        self.__numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek
