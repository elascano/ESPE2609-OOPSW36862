from datetime import date

from ..util import bool_str, format_date
from .farm_animal import FarmAnimal


class Sheep(FarmAnimal):
    def __init__(
        self,
        is_sheared: bool,
        last_sheering: date,
        animal_id: int,
        breed: str,
        born_on_date: date,
        is_meat: bool,
    ) -> None:
        super().__init__(animal_id, breed, born_on_date, is_meat)
        self._is_sheared = is_sheared
        self._last_sheering = last_sheering

    def shear_sheep(self) -> None:
        print("Sheep is sheared")

    def __str__(self) -> str:
        return (
            f"Sheep{{{super().__str__()}isSheared={bool_str(self.is_sheared)}, "
            f"lastSheering={format_date(self.last_sheering)}}}"
        )

    @property
    def is_sheared(self) -> bool:
        return self._is_sheared

    @is_sheared.setter
    def is_sheared(self, value: bool) -> None:
        self._is_sheared = value

    @property
    def last_sheering(self) -> date:
        return self._last_sheering

    @last_sheering.setter
    def last_sheering(self, value: date) -> None:
        self._last_sheering = value
