"""Module defining the concrete Cow subclass."""

from datetime import date
from typing import Optional
from .farm_animal import FarmAnimal


class Cow(FarmAnimal):
    """Concrete subclass representing a cow on the farm."""

    def __init__(
        self,
        animal_id: int,
        breed: str,
        born_on: Optional[date] = None,
        is_producing_milk: bool = False,
    ) -> None:
        if born_on is None:
            super().__init__(animal_id, breed)
        else:
            super().__init__(animal_id, breed, born_on)
        self.__is_producing_milk: bool = is_producing_milk

    def compute_food_cost(self) -> float:
        return 120.00 if self.__is_producing_milk else 85.00

    def produce(self) -> str:
        if self.__is_producing_milk:
            return "Producing fresh dairy milk."
        return "Not producing milk at the moment."

    def get_is_producing_milk(self) -> bool:
        return self.__is_producing_milk

    def set_is_producing_milk(self, is_producing_milk: bool) -> None:
        self.__is_producing_milk = is_producing_milk

    def __str__(self) -> str:
        return (
            f"Cow{{{super().__str__()}, "
            f"isProducingMilk={self.__is_producing_milk}}}"
        )
