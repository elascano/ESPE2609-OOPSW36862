from datetime import date
from model.farm_animal import FarmAnimal

class Cow(FarmAnimal):
    def __init__(self, is_producing_milk: bool, liters_of_milk_per_week: float, id_animal: int, breed: str, born_on_date: date):
        super().__init__(id_animal, breed, born_on_date)
        self.is_producing_milk = is_producing_milk
        self.liters_of_milk_per_week = liters_of_milk_per_week

    def milk(self):
        print("milk in the cow")

    def __str__(self):
      return f"Cow{{{super().__str__()}, isProducingMilk={self.is_producing_milk}, litersOfMilkPerWeek={self.liters_of_milk_per_week}}}"