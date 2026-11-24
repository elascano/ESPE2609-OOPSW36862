"""Module defining the concrete Pig subclass."""

from datetime import date
from typing import Optional
from .farm_animal import FarmAnimal


class Pig(FarmAnimal):
    """Concrete subclass representing a pig on the farm."""

    def __init__(
        self,
        animal_id: int,
        breed: str,
        born_on: Optional[date] = None,
    ) -> None:
        if born_on is None:
            super().__init__(animal_id, breed)
        else:
            super().__init__(animal_id, breed, born_on)

    def compute_food_cost(self) -> float:
        return 45.00 + (self.get_age_in_months() * 0.50)

    def produce(self) -> str:
        return "Growing for farm meat production."

    def __str__(self) -> str:
        return f"Pig{{{super().__str__()}}}"
