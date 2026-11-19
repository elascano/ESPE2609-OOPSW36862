from datetime import date
from .farm_animal import FarmAnimal
from .graze_animal_i import GrazeAnimalI
from .food import Food
from .location import Location

class Cow(FarmAnimal, GrazeAnimalI):
    def __init__(self, is_producing_milk: bool, liters_of_milk_per_week: float, id: int, breed: str, born_on_date: date):
        super().__init__(id, breed, born_on_date)
        self._is_producing_milk = is_producing_milk
        self._liters_of_milk_per_week = liters_of_milk_per_week

    def feed(self, food: Food, amount: int):
        print(f"Feeding a cow with ---> {amount} of {food}")

    def sen_to_graze(self, location: Location, date: date):
        print(f"Sending the cow to ---> {location} at {date}")

    # Sobrecarga simulada con argumentos opcionales
    def milk(self, amount_of_liters=None, hour=None):
        if amount_of_liters is None:
            print("Milking the cow as I need")
        elif hour is not None:
            self._liters_of_milk_per_week += amount_of_liters
            print(f"Milking the cow for {amount_of_liters} liters, at {hour.strftime('%H:%M')}")
        else:
            self._liters_of_milk_per_week += amount_of_liters
            print(f"Milking the cow: {amount_of_liters} liters")

    def __str__(self) -> str:
        return f"Cow{{{super().__str__()}isProducingMilk={self._is_producing_milk}, litersOfMilkPerWeek={self._liters_of_milk_per_week}}}"