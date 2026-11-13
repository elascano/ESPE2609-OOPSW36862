from datetime import date
from model.FarmAnimal import FarmAnimal
class Sheep(FarmAnimal):
    def __init__(self, id : int, breed : str, bornOnDate : date, isMeat : bool, isSheared : bool, lastSheering : date):
        super().__init__(id, breed, bornOnDate, isMeat)
        self.isSheared = isSheared
        self.lastSheering = lastSheering        
    def __str__(self):
        return f"{self.__class__.__name__}: {vars(self)}"
    
    def sheerSheep(self):
        print("Sheep is sheared")