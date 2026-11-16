from model.farm_animal import FarmAnimal
from model.graze_animal import GrazeAnimal

class Cow(FarmAnimal, GrazeAnimal):
    def __init__(self, is_producing_milk, liters_per_week, animal_id, breed, born_on_date, is_meat):
        super().__init__(animal_id, breed, born_on_date, is_meat)
        self._is_producing_milk = is_producing_milk
        self._liters_per_week = liters_per_week

    def milk(self, liters=None):
        if liters is None:
            print(f"this cow has produced --->{self._liters_per_week}liters per week")
        else:
            self._liters_per_week += liters
            print(f"milking the cow... we got --->{liters} liters today")
            print(f"this cow has produced --->{self._liters_per_week}liters per week")

    def feed(self, food, amount):
        print(f"feeding the cow with {amount} of {food}")

    def send_to_graze(self, location, date):
        print(f"send the cow to {location} at {date.hour} : {date.minute}")

    def __str__(self):
        return f"Cow{{{super().__str__()}, isProducingMilk={self._is_producing_milk}, litersPerWeek={self._liters_per_week}}}"