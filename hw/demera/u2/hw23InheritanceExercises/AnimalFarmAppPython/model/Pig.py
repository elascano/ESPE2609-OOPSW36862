from model.FarmAnimal import FarmAnimal
from datetime import date
class Pig(FarmAnimal):
    def __init__(self,id : int, breed : str, bornOnDate : date, isMeat : bool, isWallowing : bool, weightGainPerWeek : float):
        super().__init__(id, breed, bornOnDate, isMeat)
        self.isWallowing = isWallowing
        self.weightGainPerWeek = weightGainPerWeek
    def __str__(self):
        return f"{self.__class__.__name__}: {vars(self)}"    
        
