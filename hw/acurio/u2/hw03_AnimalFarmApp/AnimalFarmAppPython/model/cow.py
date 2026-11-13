from model.farm_animal import FarmAnimal

class Cow(FarmAnimal):
    def __init__(self, is_producing_milk, liters_of_milk_per_week,
                 id, breed, born_on_date, is_meat):
        super().__init__(id, breed, born_on_date, is_meat)
        self._is_producing_milk = is_producing_milk
        self._liters_of_milk_per_week = liters_of_milk_per_week

    def __str__(self):
        return (
            f"Cow{{{super().__str__()}"
            f"isProducingMilk={self._is_producing_milk}, "
            f"litersOfMilkPerWeek={self._liters_of_milk_per_week}}}"
        )

    def get_is_producing_milk(self):
        return self._is_producing_milk

    def set_is_producing_milk(self, is_producing_milk):
        self._is_producing_milk = is_producing_milk

    def get_liters_of_milk_per_week(self):
        return self._liters_of_milk_per_week

    def set_liters_of_milk_per_week(self, liters):
        self._liters_of_milk_per_week = liters
