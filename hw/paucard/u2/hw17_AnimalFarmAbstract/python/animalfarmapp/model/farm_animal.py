from abc import ABC
from datetime import date

from ..util import bool_str, format_date


class FarmAnimal(ABC):
    """Clase abstracta base para todos los animales de la granja."""

    def __init__(self, animal_id: int, breed: str, born_on_date: date, is_meat: bool) -> None:
        # ABC sin métodos abstractos sigue siendo instanciable, así que se
        # bloquea explícitamente para reproducir el `abstract class` de Java.
        if type(self) is FarmAnimal:
            raise TypeError("FarmAnimal es abstracta y no puede instanciarse directamente")
        self._id = animal_id
        self._breed = breed
        self._born_on_date = born_on_date
        self._is_meat = is_meat

    def __str__(self) -> str:
        return (
            f"FarmAnimal{{id={self.id}, breed={self.breed}, is meat={bool_str(self.is_meat)}, "
            f"bornOnDate={format_date(self.born_on_date)}}}, "
        )

    # --- getters / setters -------------------------------------------------
    @property
    def id(self) -> int:
        return self._id

    @id.setter
    def id(self, value: int) -> None:
        self._id = value

    @property
    def breed(self) -> str:
        return self._breed

    @breed.setter
    def breed(self, value: str) -> None:
        self._breed = value

    @property
    def born_on_date(self) -> date:
        return self._born_on_date

    @born_on_date.setter
    def born_on_date(self, value: date) -> None:
        self._born_on_date = value

    @property
    def is_meat(self) -> bool:
        return self._is_meat

    @is_meat.setter
    def is_meat(self, value: bool) -> None:
        self._is_meat = value
