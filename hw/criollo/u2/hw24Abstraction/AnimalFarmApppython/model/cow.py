from datetime import date

from model.farm_animal import FarmAnimal


class Cow(FarmAnimal):
    """
    @author Dennis, Byte & Fabric Devs, @ESPE
    """

    def __init__(self, isProducingMilk: bool, litersOfMilkPerWeek: float, id: int, breed: str,
                 bornOnDate: date, isMeat: bool):
        super().__init__(id, breed, bornOnDate, isMeat)
        self.__isProducingMilk = isProducingMilk
        self.__litersOfMilkPerWeek = litersOfMilkPerWeek

    def milk(self) -> None:
        print("cow is producing milk")

    def __str__(self) -> str:
        return ("Cow{" + super().__str__() + "isProducingMilk=" + str(self.__isProducingMilk).lower()
                + ", litersOfMilkPerWeek=" + str(self.__litersOfMilkPerWeek) + "}")

    def isIsProducingMilk(self) -> bool:
        """@return the isProducingMilk"""
        return self.__isProducingMilk

    def setIsProducingMilk(self, isProducingMilk: bool) -> None:
        """@param isProducingMilk the isProducingMilk to set"""
        self.__isProducingMilk = isProducingMilk

    def getLitersOfMilkPerWeek(self) -> float:
        """@return the litersOfMilkPerWeek"""
        return self.__litersOfMilkPerWeek

    def setLitersOfMilkPerWeek(self, litersOfMilkPerWeek: float) -> None:
        """@param litersOfMilkPerWeek the litersOfMilkPerWeek to set"""
        self.__litersOfMilkPerWeek = litersOfMilkPerWeek
