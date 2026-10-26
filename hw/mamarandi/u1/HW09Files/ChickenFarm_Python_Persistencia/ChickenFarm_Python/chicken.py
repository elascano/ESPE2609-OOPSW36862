from datetime import date
from egg import Egg
from poop import Poop


class Chicken:
    def __init__(self, id: int, name: str, color: str, bornOnDate: date, molting: bool):
        self.__id = id
        self.__name = name
        self.__color = color
        self.__bornOnDate = bornOnDate
        self.__ageInYears = self.computeAgeInYears()
        self.__molting = molting

    def doStuff(self):
        self.__cluck()
        self.__wander()
        self.__drink(1.3)
        print("poop -->", self.__poop())
        print("egg -->", self.__layAnEgg())

    def __cluck(self):
        print(f"the chicken {self.__name} is clucking ...")

    def __wander(self):
        print(f"the chicken {self.__name} is wandering ...")

    def __eat(self):
        print(f"the chicken {self.__name} is eating ...")

    def __drink(self, amountOfWater: float):
        print(f"the chicken {self.__name} is drinking --> {amountOfWater} ml of water")

    def __poop(self):
        return Poop(2, "green", "M")

    def __layAnEgg(self):
        return Egg(2, "S")

    def computeAgeInYears(self):
        today = date.today()
        return today.year - self.__bornOnDate.year - (
            (today.month, today.day) < (self.__bornOnDate.month, self.__bornOnDate.day)
        )

    def getId(self):
        return self.__id

    def setId(self, id):
        self.__id = id

    def getName(self):
        return self.__name

    def setName(self, name):
        self.__name = name

    def getColor(self):
        return self.__color

    def setColor(self, color):
        self.__color = color

    def getBorn0nDate(self):
        return self.__bornOnDate

    def setBorn0nDate(self, bornOnDate):
        self.__bornOnDate = bornOnDate
        self.setAgeInYears()

    def getAgeInYears(self):
        return self.__ageInYears

    def setAgeInYears(self, ageInYears=None):
        self.__ageInYears = self.computeAgeInYears() if ageInYears is None else ageInYears

    def isMolting(self):
        return self.__molting

    def setMolting(self, molting):
        self.__molting = molting

    def __str__(self):
        molting = str(self.__molting).lower()
        return (f"Chicken{{id={self.__id}, name={self.__name}, color={self.__color}, "
                f"born0nDate={self.__bornOnDate}, age={self.__ageInYears}, molting={molting}}}")
