from model.FarmAnimal import FarmAnimal
from datetime import date
class Cow(FarmAnimal):
    def __init__(self, id : int, breed : str, bornOnDate : date, isMeat : bool, isProducingMilk : bool, litersOfMilkPerWeek : float):
        super().__init__(id, breed, bornOnDate, isMeat)
        self.isProducingMilk = isProducingMilk
        self.litersOfMilkPerWeek = litersOfMilkPerWeek
    def __str__(self):
            return f"{self.__class__.__name__}: {vars(self)}"
