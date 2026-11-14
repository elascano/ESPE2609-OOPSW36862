from datetime import date
from math import isfinite

from model.farm_animal import FarmAnimal
from model.food import Food


class Sheep(FarmAnimal):
    def __init__(
        self,
        last_shearing: date,
        is_producing_wool: bool,
        kg_of_wool_for_month: float,
        animal_id: int,
        breed: str,
        born_on_date: date | None,
    ):
        super().__init__(animal_id, breed, born_on_date)
        self.last_shearing = last_shearing
        self.is_producing_wool = is_producing_wool
        self.kg_of_wool_for_month = kg_of_wool_for_month

    def feed(self, food: Food, amount: int) -> str:
        return f"Feeding the sheep {amount} lb of {food}."

    def shear(self, kilograms: float | None = None) -> str:
        if kilograms is None:
            return "The sheep is being sheared."
        if not isfinite(kilograms) or kilograms <= 0:
            raise ValueError("The wool weight must be positive and finite.")
        self.kg_of_wool_for_month += kilograms
        self.last_shearing = date.today()
        return f"Collected {kilograms:g} kg of wool."

    def __str__(self) -> str:
        return (
            f"{super().__str__()}, Last shearing: {self.last_shearing:%d/%m/%Y}, "
            f"Producing wool: {self.is_producing_wool}, "
            f"Kilograms of wool this month: {self.kg_of_wool_for_month}"
        )
