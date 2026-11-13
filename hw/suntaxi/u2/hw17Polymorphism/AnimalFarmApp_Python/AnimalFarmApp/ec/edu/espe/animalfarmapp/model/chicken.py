from ec.edu.espe.animalfarmapp.model.farm_animal import FarmAnimal

class Chicken(FarmAnimal):
    def __init__(self, name: str, age: int, breed: str, is_molting: bool = False):
        super().__init__(name, age, breed)
        self.is_molting = is_molting

    def make_sound(self) -> str:
        return "Cluck, cluck"

    def lay_egg(self) -> str:
        return f"{self.name} ha puesto un huevo fresco."
