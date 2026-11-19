from datetime import datetime
from ec.edu.espe.model.FarmAnimal import FarmAnimal
from ec.edu.espe.model.GrazeAnimalI import GrazeAnimalI
from ec.edu.espe.model.Food import Food
from ec.edu.espe.model.Location import Location

class Cow(FarmAnimal, GrazeAnimalI):
    def __init__(self, is_producing_milk: bool, liters_of_milk_per_week: float, id: int, breed: str, born_on_date: datetime):
        super().__init__(id, breed, born_on_date)
        self.is_producing_milk = is_producing_milk
        self.liters_of_milk_per_week = liters_of_milk_per_week

    def send_to_graze(self, location: Location, date: datetime):
        print(f"Sending the cow to ---> {location} at {date}")

    def feed(self, food: Food, amount: int):
        print(f"Feeding a cow with ---> {amount} of {food}")

    def milk(self, amount_of_liters: float = None, hour: datetime = None):
        if amount_of_liters is not None and hour is not None:
            self.liters_of_milk_per_week += amount_of_liters
            print(f"Milking the cow for {amount_of_liters} liters, at {hour.strftime('%H:%M')}")
        elif amount_of_liters is not None:
            self.liters_of_milk_per_week += amount_of_liters
            print(f"Milking the cow: {amount_of_liters} liters")
        else:
            print("Milking the cow as I need")

    def __str__(self):
        return f"Cow{{{super().__str__()}, isProducingMilk={self.is_producing_milk}, litersOfMilkPerWeek={self.liters_of_milk_per_week}}}"