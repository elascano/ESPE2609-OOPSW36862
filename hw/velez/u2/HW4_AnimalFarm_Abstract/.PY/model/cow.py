from model.farm_animal import FarmAnimal

class Cow(FarmAnimal):
    def __init__(self, is_producing_milk, liters_per_week, animal_id, breed, born_on_date, is_meat):
        super().__init__(animal_id, breed, born_on_date, is_meat)
        self._is_producing_milk = is_producing_milk
        self._liters_per_week = liters_per_week

    def milk(self):
        print("cow is producing milk")

    def __str__(self):
        return f"Cow{{{super().__str__()}, isProducingMilk={self._is_producing_milk}, litersPerWeek={self._liters_per_week}}}"