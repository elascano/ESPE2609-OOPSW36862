"""Module defining the concrete Pig subclass."""

from datetime import date
from .farm_animal import FarmAnimal


class Pig(FarmAnimal):
    """Concrete subclass representing a pig on the farm."""

    def __init__(self, animal_id: int, breed: str, born_on: date) -> None:
        super().__init__(animal_id, breed, born_on)

    def feed(self) -> str:
        return (
            f"Pig #{self.get_id()} ({self.get_breed()}) "
            "is eating corn, vegetables, and farm feed."
        )

    def make_sound(self) -> str:
        return "Oink, oink!"

    def produce(self) -> str:
        return "Growing for farm meat production."

    def __str__(self) -> str:
        return f"Pig{{{super().__str__()}}}"
