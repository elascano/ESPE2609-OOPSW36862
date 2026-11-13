from ec.edu.espe.animalfarmapp.model.farm_animal import FarmAnimal

class Cow(FarmAnimal):
    def __init__(self, name: str, age: int, breed: str, milk_production_liters: float):
        super().__init__(name, age, breed)
        self.milk_production_liters = milk_production_liters

    def make_sound(self) -> str:
        return "Muuu"

    def produce_milk(self) -> str:
        return f"{self.name} ha producido {self.milk_production_liters} litros de leche."
