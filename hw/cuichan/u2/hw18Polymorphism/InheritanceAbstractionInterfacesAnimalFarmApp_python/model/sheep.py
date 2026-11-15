from datetime import date
from model.farm_animal import FarmAnimal
from model.food import Food

class Sheep(FarmAnimal):
    def __init__(self, last_sheering: date, is_producing_wool: bool, is_meat: bool, id: int, breed: str, born_on_date: date):
        super().__init__(id, breed, born_on_date)
        self.last_sheering = last_sheering
        self.is_producing_wool = is_producing_wool
        self.is_meat = is_meat

    def feed(self, food: Food, amount_of_food: int):
        print(f"Sheep {self.id} is eating {amount_of_food} units of {str(food)}")

    def __str__(self):
        return f"Sheep{{{super().__str__()} lastSheering={self.last_sheering}, isProducingWool={self.is_producing_wool}, isMeat={self.is_meat}}}"

    @property
    def last_sheering_date(self):
        return self.last_sheering

    @last_sheering_date.setter
    def last_sheering_date(self, last_sheering: date):
        self.last_sheering = last_sheering

    @property
    def is_producing_wool_status(self):
        return self.is_producing_wool

    @is_producing_wool_status.setter
    def is_producing_wool_status(self, is_producing_wool: bool):
        self.is_producing_wool = is_producing_wool

    @property
    def is_meat_status(self):
        return self.is_meat

    @is_meat_status.setter
    def is_meat_status(self, is_meat: bool):
        self.is_meat = is_meat