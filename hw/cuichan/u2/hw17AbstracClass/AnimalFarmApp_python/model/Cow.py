from model.farm_animal import FarmAnimal
from datetime import date

class Cow(FarmAnimal):
    def __init__(self, is_producing_milk: bool, liters_of_milk_per_week: float, id: int, breed: str, born_on_date: date):
        super().__init__(id, breed, born_on_date)
        self.is_producing_milk = is_producing_milk
        self.liters_of_milk_per_week = liters_of_milk_per_week

    def __str__(self) -> str:
        return f"Cow{{{super().__str__()}, isProducingMilk={self.is_producing_milk}, litersOfMilkPerWeek={self.liters_of_milk_per_week}}}"

    def feed(self):
        print(f"Cow (ID: {self.id}) is eating grass.")

    def milk(self):
        print("cow is producing milk ")