from datetime import date

from ..util import bool_str
from .farm_animal import FarmAnimal


class Pig(FarmAnimal):
    MEAT_CUTS = [
        "head", "cheek", "picnic", "clear plate", "boston shoulder", "back fat",
        "loin", "ribs", "bacon", "leg", "ham", "hock",
    ]

    def __init__(
        self,
        is_wallowing: bool,
        weight_gain_per_week: float,
        animal_id: int,
        breed: str,
        born_on_date: date,
        is_meat: bool,
    ) -> None:
        super().__init__(animal_id, breed, born_on_date, is_meat)
        self._is_wallowing = is_wallowing
        self._weight_gain_per_week = weight_gain_per_week

    def show_pig_meat_cuts(self) -> None:
        print("Avalaible cuts for the pig[" + ", ".join(self.MEAT_CUTS) + "]")

    def __str__(self) -> str:
        return (
            f"Pig{{{super().__str__()}isWallowing={bool_str(self.is_wallowing)}, "
            f"weightGainPerWeek={self.weight_gain_per_week}}}"
        )

    @property
    def is_wallowing(self) -> bool:
        return self._is_wallowing

    @is_wallowing.setter
    def is_wallowing(self, value: bool) -> None:
        self._is_wallowing = value

    @property
    def weight_gain_per_week(self) -> float:
        return self._weight_gain_per_week

    @weight_gain_per_week.setter
    def weight_gain_per_week(self, value: float) -> None:
        self._weight_gain_per_week = value
