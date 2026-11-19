from .FarmAnimal import FarmAnimal
from .GrazeAnimalI import GrazeAnimalI


class Cow(FarmAnimal, GrazeAnimalI):
    def __init__(self, is_producing_milk: bool, liters_of_milk_per_week: float,
                 animal_id: int, breed: str, born_on_date, is_meat: bool):
        super().__init__(animal_id, breed, born_on_date, is_meat)
        self.isProducingMilk = is_producing_milk
        self.litersOfMilkPerWeek = liters_of_milk_per_week

    def feed(self, food, amount: int):
        print(f"feeding the cow with {amount} of {food}")

    def sendToGraze(self, location, date_time):
        print(f"send the cow to {location} at {date_time.hour} : {date_time.minute}")

    def milk(self, liters=None):
        if liters is None:
            print(f"this cow has produced --->{self.litersOfMilkPerWeek}liters per week")
        else:
            self.litersOfMilkPerWeek += liters
            print(f"milking the cow... we got --->{liters:g} liters today")
            print(f"this cow has produced --->{self.litersOfMilkPerWeek:g}liters per week")

    def getLitersOfMilkPerWeek(self):
        return self.litersOfMilkPerWeek

    def __str__(self):
        return (f"Cow{{{super().__str__()}isProducingMilk={str(self.isProducingMilk).lower()}, "
                f"litersOfMilkPerWeek={self.litersOfMilkPerWeek:g}}}")
