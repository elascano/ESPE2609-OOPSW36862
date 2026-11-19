from datetime import date

from .farm_animal import FarmAnimal


class Chicken(FarmAnimal):
    def __init__(
        self,
        is_molting: bool,
        number_of_laid_egg_in_month: int,
        id: int,
        breed: str,
        born_on_date: date,
        is_meat: bool = False,
    ):
        super().__init__(id, breed, born_on_date)
        self._is_molting = is_molting
        self._number_of_laid_egg_in_month = number_of_laid_egg_in_month
        self._is_meat = is_meat

    def lay_an_egg(self) -> str:
        return "chicken is laying an egg()"

    def __str__(self) -> str:
        return (
            f"Chicken{{{super().__str__()}isMolting={str(self._is_molting).lower()}, "
            f"numberOfLaidEggInMonth={self._number_of_laid_egg_in_month}, "
            f"isMeat={str(self._is_meat).lower()}}}"
        )

    @property
    def is_molting(self) -> bool:
        return self._is_molting

    @is_molting.setter
    def is_molting(self, is_molting: bool) -> None:
        self._is_molting = is_molting

    @property
    def number_of_laid_egg_in_month(self) -> int:
        return self._number_of_laid_egg_in_month

    @number_of_laid_egg_in_month.setter
    def number_of_laid_egg_in_month(self, number_of_laid_egg_in_month: int) -> None:
        self._number_of_laid_egg_in_month = number_of_laid_egg_in_month

    @property
    def is_meat(self) -> bool:
        return self._is_meat

    @is_meat.setter
    def is_meat(self, is_meat: bool) -> None:
        self._is_meat = is_meat
