from .FarmAnimal import FarmAnimal
from .SellAnimal import SellAnimal


class Chicken(FarmAnimal, SellAnimal):
    def __init__(self, is_molting: bool, number_of_laid_eggs_per_week: int,
                 animal_id: int, breed: str, born_on_date):
        super().__init__(animal_id, breed, born_on_date, is_molting)
        self.isMolting = is_molting
        self.numberOfLaidEggsPerWeek = number_of_laid_eggs_per_week

    def feed(self, food, amount: int):
        print(f"feeding a chicken with {amount} pound of {food}")

    def sellAnimal(self, price: float):
        print(f"sold the chicken in {price:g} dollars.")

    def layAnEgg(self):
        self.numberOfLaidEggsPerWeek += 1
        print("chicken is laying an egg")

    def __str__(self):
        return (f"Chicken{{{super().__str__()}isMolting={str(self.isMolting).lower()}, "
                f"numberOfLaidEggsPerWeek={self.numberOfLaidEggsPerWeek}}}")
