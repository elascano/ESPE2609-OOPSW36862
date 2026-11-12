from datetime import date
from model.FarmAnimal import FarmAnimal
class Chicken(FarmAnimal):
    def __init__(self, id : int, breed : str, bornOnDate : date, isMeat : bool, isMolting : bool, numberOfLaidEggsPerWeek : int):
        super().__init__(id, breed, bornOnDate, isMeat)
        self.isMolting = isMolting
        self.numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek
    def __str__(self):
        return f"{self.__class__.__name__}: {vars(self)}"
