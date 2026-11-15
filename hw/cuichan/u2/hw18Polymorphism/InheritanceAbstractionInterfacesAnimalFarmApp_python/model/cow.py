from datetime import date
from model.farm_animal import FarmAnimal
from model.graze_animal import GrazeAnimal
from model.food import Food
from model.location import Location

class Cow(FarmAnimal, GrazeAnimal):
    def __init__(self, is_producing_milk: bool, liters_of_milk_per_week: float, id: int, breed: str, born_on_date: date):
        super().__init__(id, breed, born_on_date)
        self.is_producing_milk = is_producing_milk
        self.liters_of_milk_per_week = liters_of_milk_per_week
        self.vacinnes = []

    def sendto_graze(self, location: Location):
        print("Sending the caw to eat grass at ")

    def feed(self, food: Food, amount_of_food: int):
        print(f"feeding the cow with{amount_of_food}of{food}")

    def __str__(self):
        return f"Cow{{{super().__str__()}isProducingMilk={self.is_producing_milk}, litersOfMilkPerWeek={self.liters_of_milk_per_week}}}"

    @property
    def is_producing_milk_status(self):
        return self.is_producing_milk

    @is_producing_milk_status.setter
    def is_producing_milk_status(self, is_producing_milk: bool):
        self.is_producing_milk = is_producing_milk

    @property
    def liters_of_milk(self):
        return self.liters_of_milk_per_week

    @liters_of_milk.setter
    def liters_of_milk(self, liters_of_milk_per_week: float):
        self.liters_of_milk_per_week = liters_of_milk_per_week

    def milk(self, amount_of_liters=None, hour=None):
        if amount_of_liters is None and hour is None:
            print("Milking the Cow I need")
        elif hour is None:
            self.liters_of_milk_per_week += float(amount_of_liters)
            print(f"Milking the Cow obtend  {amount_of_liters}liters today")
        else:
            self.liters_of_milk_per_week += float(amount_of_liters)
            hours = hour.hour if hasattr(hour, 'hour') else 0
            minutes = hour.minute if hasattr(hour, 'minute') else 0
            print(f"milking the cow for {amount_of_liters}liters, at {hours}:{minutes}")