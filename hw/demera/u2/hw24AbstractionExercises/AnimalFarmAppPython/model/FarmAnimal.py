from abc import ABC, abstractmethod
from datetime import date
class FarmAnimal(ABC):
    def __init__(self, id : int, breed : str, bornOnDate : date, isMeat : bool):
        self.id = id
        self.breed = breed
        self.bornOnDate = bornOnDate
        self.isMeat = isMeat
        
    def getAgeInYears(bornOnDate):
        #TODO compute Age
        return 0
    
    def __str__(self):
        return f"{self.__class__.__name__}: {vars(self)}"
    