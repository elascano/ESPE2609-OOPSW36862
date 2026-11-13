from datetime import date

from ..util import bool_str
from .farm_animal import FarmAnimal


class Cow(FarmAnimal):
    def __init__(
        self,
        is_producing_milk: bool,
        liters_of_milk_per_week: float,
        animal_id: int,
        breed: str,
        born_on_date: date,
        is_meat: bool,
    ) -> None:
        super().__init__(animal_id, breed, born_on_date, is_meat)
        self._is_producing_milk = is_producing_milk
        self._liters_of_milk_per_week = liters_of_milk_per_week

    def milk(self) -> None:
        print("cow is producing milk")

    def __str__(self) -> str:
        return (
            f"Cow{{{super().__str__()}isProducingMilk={bool_str(self.is_producing_milk)}, "
            f"litersOfMilkPerWeek={self.liters_of_milk_per_week}}}"
        )

    @property
    def is_producing_milk(self) -> bool:
        return self._is_producing_milk

    @is_producing_milk.setter
    def is_producing_milk(self, value: bool) -> None:
        self._is_producing_milk = value

    @property
    def liters_of_milk_per_week(self) -> float:
        return self._liters_of_milk_per_week

    @liters_of_milk_per_week.setter
    def liters_of_milk_per_week(self, value: float) -> None:
        self._liters_of_milk_per_week = value
