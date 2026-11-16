from datetime import date
from .farm_animal import FarmAnimal
from .graze_animal_i import GrazeAnimalI
from .food import Food
from .location import Location

class Sheep(FarmAnimal, GrazeAnimalI):
    def __init__(self, last_sheering: date, wool_weight_in_kg: float, id: int, breed: str, born_on_date: date):
        super().__init__(id, breed, born_on_date)
        self._last_sheering = last_sheering
        self._wool_weight_in_kg = wool_weight_in_kg

    def feed(self, food: Food, amount: int):
        print(f"Feeding a sheep with ---> {amount} of {food}")

    def sen_to_graze(self, location: Location, date: date):
        print(f"Sending the sheep to ---> {location} at {date}")

    def shear(self, kg_of_wool: float = 0.0):
        if kg_of_wool == 0.0:
            print(f"Shearing the sheep casually. Wool remains at: {self._wool_weight_in_kg} kg.")
        else:
            self._wool_weight_in_kg += kg_of_wool
            print(f"Shearing the sheep for {kg_of_wool} kg of wool. Total wool collected: {self._wool_weight_in_kg} kg.")

    def __str__(self) -> str:
        return f"Sheep{{{super().__str__()}lastSheering={self._last_sheering.day}/{self._last_sheering.month}/{self._last_sheering.year}, woolWeightInKg={self._wool_weight_in_kg}}}"