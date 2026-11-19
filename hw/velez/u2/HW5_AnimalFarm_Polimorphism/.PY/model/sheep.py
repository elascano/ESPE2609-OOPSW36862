from model.farm_animal import FarmAnimal
from model.graze_animal import GrazeAnimal

class Sheep(FarmAnimal, GrazeAnimal):
    def __init__(self, is_sheared, last_sheering, animal_id, breed, born_on_date, is_meat):
        super().__init__(animal_id, breed, born_on_date, is_meat)
        self._is_sheared = is_sheared
        self._last_sheering = last_sheering

    def shear_sheep(self):
        print("Sheep is sheared")

    def feed(self, food, amount):
        print(f"feeding the sheep with {amount} kg of {food}")

    def send_to_graze(self, location, date):
        print(f"send the sheep to {location} at {date.hour} : {date.minute}")

    def __str__(self):
        return f"Sheep{{{super().__str__()}, isSheared={self._is_sheared}, lastSheering={self._last_sheering}}}"