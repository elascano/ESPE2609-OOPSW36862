"""Module defining the concrete Sheep subclass."""

from datetime import date
from .farm_animal import FarmAnimal


class Sheep(FarmAnimal):
    """Concrete subclass representing a sheep on the farm."""

    def __init__(
        self, animal_id: int, breed: str, born_on: date, last_sheering: date
    ) -> None:
        super().__init__(animal_id, breed, born_on)
        self.__last_sheering: date = last_sheering

    def feed(self) -> str:
        return (
            f"Sheep #{self.get_id()} ({self.get_breed()}) "
            "is grazing on pasture grass and clover."
        )

    def make_sound(self) -> str:
        return "Baa, baa!"

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
