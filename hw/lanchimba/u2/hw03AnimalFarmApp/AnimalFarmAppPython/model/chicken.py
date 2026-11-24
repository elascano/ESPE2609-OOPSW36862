"""Module defining the Chicken subclass."""

from datetime import date
from .farm_animal import FarmAnimal


class Chicken(FarmAnimal):
    """Subclass representing a chicken on the farm."""

    def __init__(
        self, animal_id: int, breed: str, born_on: date, is_molting: bool
    ) -> None:
        super().__init__(animal_id, breed, born_on)
        self.__is_molting: bool = is_molting

    def get_is_molting(self) -> bool:
        return self.__is_molting

    def set_is_molting(self, is_molting: bool) -> None:
        self.__is_molting = is_molting

    def __str__(self) -> str:
        return f"Chicken{{{super().__str__()}, isMolting={self.__is_molting}}}"
