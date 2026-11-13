from model.farm_animal import FarmAnimal
from datetime import date

class Chicken(FarmAnimal):
    def __init__(self, is_molting: bool, number_of_laid_eggs_in_month: int, is_meat: bool, id: int, breed: str, born_on_date: date):
        super().__init__(id, breed, born_on_date)
        self.is_molting = is_molting
        self.number_of_laid_eggs_in_month = number_of_laid_eggs_in_month
        self.is_meat = is_meat

    def __str__(self) -> str:
        return f"Chicken{{{super().__str__()},isMolting={self.is_molting}, numberOfLaidEggsInMonth={self.number_of_laid_eggs_in_month}, isMeat={self.is_meat}}}"

    def feed(self):
        print(f"Chicken (ID: {self.id}) is eating grains.")

    def layAnEgg(self):
        print("The chicken is laying an Egg")