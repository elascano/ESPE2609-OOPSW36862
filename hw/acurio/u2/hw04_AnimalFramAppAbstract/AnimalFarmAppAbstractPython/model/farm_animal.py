from abc import ABC


class FarmAnimal(ABC):
    def __init__(self, id, breed, bornOnDate, isMeat):
        if type(self) is FarmAnimal:
            raise TypeError("FarmAnimal es una clase abstracta")
        self._id = id
        self._breed = breed
        self._bornOnDate = bornOnDate
        self._isMeat = isMeat

    def __str__(self):
        fecha = self.getBornOnDate()
        return (f"FarmAnimal{{id={self.getId()}, breed={self.getBreed()}, "
                f"is meat={str(self.isIsMeat()).lower()}, "
                f"bornOnDate={fecha.day} / {fecha.month} / {fecha.year}}}, ")

    def getId(self):
        return self._id

    def setId(self, id):
        self._id = id

    def getBreed(self):
        return self._breed

    def setBreed(self, breed):
        self._breed = breed

    def getBornOnDate(self):
        return self._bornOnDate

    def setBornOnDate(self, bornOnDate):
        self._bornOnDate = bornOnDate

    def isIsMeat(self):
        return self._isMeat

    def setIsMeat(self, isMeat):
        self._isMeat = isMeat
