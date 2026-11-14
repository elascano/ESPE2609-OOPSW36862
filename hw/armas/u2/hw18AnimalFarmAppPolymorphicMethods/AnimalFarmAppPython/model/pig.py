from datetime import date
from math import isfinite

from model.farm_animal import FarmAnimal
from model.food import Food


class Pig(FarmAnimal):
    def __init__(
        self,
        weight: float,
        is_for_meat: bool,
        animal_id: int,
        breed: str,
        born_on_date: date | None,
    ):
        super().__init__(animal_id, breed, born_on_date)
        self.weight = weight
        self.is_for_meat = is_for_meat
        self.total_cut_weight = 0.0

    def feed(self, food: Food, amount: int) -> str:
        return f"Feeding the pig {amount} lb of {food}."

    def record_cut(self, cut_name: str, kilograms: float) -> str:
        if not cut_name.strip() or not isfinite(kilograms) or kilograms <= 0:
            raise ValueError("A cut needs a name and a positive, finite weight.")
        self.total_cut_weight += kilograms
        return f"Recorded {kilograms:g} kg of {cut_name.strip()}."

    def __str__(self) -> str:
        return (
            f"{super().__str__()}, Weight: {self.weight} kg, "
            f"Raised for meat: {self.is_for_meat}"
        )
