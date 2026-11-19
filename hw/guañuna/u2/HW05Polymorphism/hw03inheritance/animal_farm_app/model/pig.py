from datetime import date
from .farm_animal import FarmAnimal
from .graze_animal_i import GrazeAnimalI
from .food import Food
from .location import Location

class Pig(FarmAnimal, GrazeAnimalI):
    def __init__(self, weight_in_kg: float, is_hungry: bool, id: int, breed: str, born_on_date: date):
        super().__init__(id, breed, born_on_date)
        self._weight_in_kg = weight_in_kg
        self._is_hungry = is_hungry

    def feed(self, food: Food, amount: int):
        print(f"Feeding a pig with ---> {amount} of {food}")

    def sen_to_graze(self, location: Location, date: date):
        print(f"Sending the pig to ---> {location} at {date}")

    def eat(self, kg_of_food: float = 0.0):
        if kg_of_food == 0.0:
            print("Pig is eating slop. Just maintaining weight.")
        else:
            self._weight_in_kg += (kg_of_food * 0.8)
            self._is_hungry = False
            print(f"Pig ate {kg_of_food}kg of food. New weight: {self._weight_in_kg} kg. Hungry? {self._is_hungry}")

    def __str__(self) -> str:
        return f"Pig{{{super().__str__()}weightInKg={self._weight_in_kg}, isHungry={self._is_hungry}}}"