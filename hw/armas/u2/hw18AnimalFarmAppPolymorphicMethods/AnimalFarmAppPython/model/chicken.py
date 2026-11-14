from datetime import date

from model.farm_animal import FarmAnimal
from model.food import Food


class Chicken(FarmAnimal):
    def __init__(
        self,
        animal_id: int,
        breed: str,
        born_on_date: date | None,
        is_molting: bool,
        eggs_laid_this_month: int,
        is_raised_for_meat: bool,
    ):
        super().__init__(animal_id, breed, born_on_date)
        self.is_molting = is_molting
        self.eggs_laid_this_month = eggs_laid_this_month
        self.is_raised_for_meat = is_raised_for_meat

    def feed(self, food: Food, amount: int) -> str:
        return f"Feeding the chicken {amount} lb of {food}."

    def lay_an_egg(self) -> str:
        self.lay_eggs(1)
        return "The chicken is laying an egg."

    def lay_eggs(self, amount: int) -> str:
        if type(amount) is not int or amount <= 0:
            raise ValueError("The egg count must be a positive integer.")
        self.eggs_laid_this_month += amount
        return f"The chicken laid {amount} eggs."

    def __str__(self) -> str:
        return (
            f"{super().__str__()}, Molting: {self.is_molting}, "
            f"Eggs laid this month: {self.eggs_laid_this_month}, "
            f"Raised for meat: {self.is_raised_for_meat}"
        )
