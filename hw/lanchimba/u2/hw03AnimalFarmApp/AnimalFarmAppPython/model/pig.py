"""Module defining the Pig subclass."""

from datetime import date
from .farm_animal import FarmAnimal


class Pig(FarmAnimal):
    """Subclass representing a pig on the farm."""

    def __init__(self, animal_id: int, breed: str, born_on: date) -> None:
        super().__init__(animal_id, breed, born_on)

    def __str__(self) -> str:
        return f"Pig{{{super().__str__()}}}"
