from datetime import date
from model.farm_animal import FarmAnimal
from model.food import Food

class Pig(FarmAnimal):
    def __init__(self, id: int, breed: str, born_on: date, weight: float, is_meat: bool):
        super().__init__(id, breed, born_on)
        self.weight = weight
        self.is_meat = is_meat

    def feed(self, food: Food, amount_of_food: int):
        print(f"Pig {self.id} is eating {amount_of_food} units of {str(food)}")

    def __str__(self):
        return f"Pig{{{super().__str__()} weight={self.weight}, isMeat={self.is_meat}}}"

    @property
    def pig_weight(self):
        return self.weight

    @pig_weight.setter
    def pig_weight(self, weight: float):
        self.weight = weight

    @property
    def is_meat_status(self):
        return self.is_meat

    @is_meat_status.setter
    def is_meat_status(self, is_meat: bool):
        self.is_meat = is_meat