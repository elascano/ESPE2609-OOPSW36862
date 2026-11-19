from datetime import date

from .farm_animal import FarmAnimal


class Cow(FarmAnimal):
    def __init__(
        self,
        is_producing_milk: bool,
        liters_of_milk_for_week: float,
        id: int,
        breed: str,
        born_on_date: date,
    ):
        super().__init__(id, breed, born_on_date)
        self._is_producing_milk = is_producing_milk
        self._liters_of_milk_for_week = liters_of_milk_for_week

    def milk(self) -> str:
        return "milking the cow"

    def __str__(self) -> str:
        return (
            f"Cow{{{super().__str__()}isProducingMilk={str(self._is_producing_milk).lower()}, "
            f"litersOfMilkForWeek={self._liters_of_milk_for_week}}}"
        )

    @property
    def is_producing_milk(self) -> bool:
        return self._is_producing_milk

    @is_producing_milk.setter
    def is_producing_milk(self, is_producing_milk: bool) -> None:
        self._is_producing_milk = is_producing_milk

    @property
    def liters_of_milk_for_week(self) -> float:
        return self._liters_of_milk_for_week

    @liters_of_milk_for_week.setter
    def liters_of_milk_for_week(self, liters_of_milk_for_week: float) -> None:
        self._liters_of_milk_for_week = liters_of_milk_for_week
