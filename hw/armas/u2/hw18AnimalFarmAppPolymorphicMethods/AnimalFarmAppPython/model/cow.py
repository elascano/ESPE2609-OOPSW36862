from datetime import date

from model.farm_animal import FarmAnimal
from model.food import Food
from model.graze_animal import GrazeAnimal
from model.location import Location


class Cow(FarmAnimal, GrazeAnimal):
    def __init__(
        self,
        animal_id: int,
        breed: str,
        born_on_date: date | None,
        is_producing_milk: bool,
        liters_of_milk_per_week: float,
    ):
        super().__init__(animal_id, breed, born_on_date)
        self.is_producing_milk = is_producing_milk
        self.liters_of_milk_per_week = liters_of_milk_per_week

    def feed(self, food: Food, amount: int) -> str:
        return f"Feeding the cow {amount} lb of {food}."

    def graze(self, location: Location) -> str:
        return f"The cow is grazing in {location}."

    def milk(self, amount_of_liters: float | None = None) -> str:
        if amount_of_liters is None:
            return "The cow is being milked."

        self.liters_of_milk_per_week += amount_of_liters
        return f"Milking the cow: {amount_of_liters} liters."

    def __str__(self) -> str:
        return (
            f"{super().__str__()}, Producing milk: {self.is_producing_milk}, "
            f"Liters of milk per week: {self.liters_of_milk_per_week}"
        )
