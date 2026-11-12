from datetime import datetime

from model.farm_animal import FarmAnimal


class Chicken(FarmAnimal):
    def __init__(
        self,
        is_molting: bool,
        number_of_last_eggs_per_week: int,
        is_meat: bool,
        id: int,
        breed: str,
        born_on_date: datetime,
    ):
        super().__init__(id, breed, born_on_date)
        self._is_molting = is_molting
        self._number_of_last_eggs_per_week = number_of_last_eggs_per_week
        self._is_meat = is_meat

    def __str__(self) -> str:
        return (
            "Chicken{" + super().__str__()
            + f"isMolting={str(self._is_molting).lower()}, "
            + f"numberOfLastEggsPerWeek={self._number_of_last_eggs_per_week}, "
            + f"isMeat={str(self._is_meat).lower()}" + "}"
        )

    def is_molting(self) -> bool:
        return self._is_molting

    def set_is_molting(self, is_molting: bool) -> None:
        self._is_molting = is_molting

    def get_number_of_last_eggs_per_week(self) -> int:
        return self._number_of_last_eggs_per_week

    def set_number_of_last_eggs_per_week(self, number_of_last_eggs_per_week: int) -> None:
        self._number_of_last_eggs_per_week = number_of_last_eggs_per_week

    def is_meat(self) -> bool:
        return self._is_meat

    def set_is_meat(self, is_meat: bool) -> None:
        self._is_meat = is_meat
