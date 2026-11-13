from datetime import date
from ec.edu.espe.animalfarmapp.model.farm_animal import FarmAnimal

class Pig(FarmAnimal):
    def __init__(self, id: int, bred: str, born_on_date: date):
        super().__init__(id, bred, born_on_date)

    def __str__(self) -> str:
        super_str = super().__str__().replace("}", "")
        return f"Pig{{{super_str}}}"