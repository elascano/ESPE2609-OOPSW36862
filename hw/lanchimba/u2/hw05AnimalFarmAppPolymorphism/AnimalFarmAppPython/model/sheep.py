"""Module defining the concrete Sheep subclass."""

from datetime import date
from typing import Optional
from .farm_animal import FarmAnimal


class Sheep(FarmAnimal):
    """Concrete subclass representing a sheep on the farm."""

    def __init__(
        self,
        animal_id: int,
        breed: str,
        born_on: Optional[date] = None,
        last_sheering: Optional[date] = None,
    ) -> None:
        if born_on is None:
            super().__init__(animal_id, breed)
        else:
            super().__init__(animal_id, breed, born_on)
        self.__last_sheering: date = (
            last_sheering if last_sheering is not None else date.today()
        )

    def compute_food_cost(self) -> float:
        return 30.00 + (self.get_age_in_months() * 0.25)

    def produce(self) -> str:
        return f"Producing wool (last sheared on {self.__last_sheering.isoformat()})."

    def get_last_sheering(self) -> date:
        return self.__last_sheering

    def set_last_sheering(self, last_sheering: date) -> None:
        self.__last_sheering = last_sheering

    def __str__(self) -> str:
        return (
            f"Sheep{{{super().__str__()}, "
            f"lastSheering={self.__last_sheering.isoformat()}}}"
        )
