"""Module defining the concrete Cow subclass."""

from datetime import date
from .farm_animal import FarmAnimal


class Cow(FarmAnimal):
    """Concrete subclass representing a cow on the farm."""

    def __init__(
        self, animal_id: int, breed: str, born_on: date, is_producing_milk: bool
    ) -> None:
        super().__init__(animal_id, breed, born_on)
        self.__is_producing_milk: bool = is_producing_milk

    def feed(self) -> str:
        return (
            f"Cow #{self.get_id()} ({self.get_breed()}) "
            "is grazing on fresh grass and hay."
        )

    def make_sound(self) -> str:
        return "Moo!"

    def produce(self) -> str:
        if self.__is_producing_milk:
            return "Producing fresh milk."
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
