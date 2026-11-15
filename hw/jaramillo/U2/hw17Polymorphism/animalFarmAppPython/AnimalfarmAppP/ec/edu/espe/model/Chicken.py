from datetime import datetime
from ec.edu.espe.model.FarmAnimal import FarmAnimal
from ec.edu.espe.model.Food import Food

class Chicken(FarmAnimal):
    def __init__(self, is_molting: bool, number_of_laid_eggs_in_a_month: int, is_meat: bool, id: int, breed: str, born_on_date: datetime):
        super().__init__(id, breed, born_on_date)
        self.is_molting = is_molting
        self.number_of_laid_eggs_in_a_month = number_of_laid_eggs_in_a_month
        self.is_meat = is_meat

    def feed(self, food: Food, amount: int):
        print(f"feeding a chicken with --->{amount} of {food}")

    def lay_an_egg(self):
        print("Chicken is laying an egg () ")

    def __str__(self):
        return f"Chicken{{{super().__str__()}isMolting={self.is_molting}, numberOfLaidEggsInAMonth={self.number_of_laid_eggs_in_a_month}, isMeat={self.is_meat}}}"