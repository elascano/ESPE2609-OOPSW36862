from datetime import date

from ..util import bool_str
from .farm_animal import FarmAnimal


class Chicken(FarmAnimal):
    def __init__(
        self,
        is_molting: bool,
        number_of_laid_eggs_per_week: int,
        animal_id: int,
        breed: str,
        born_on_date: date,
    ) -> None:
        # Se mantiene el comportamiento del original en Java: `is_molting`
        # también se envía como `is_meat` a la clase base.
        super().__init__(animal_id, breed, born_on_date, is_molting)
        self._is_molting = is_molting
        self._number_of_laid_eggs_per_week = number_of_laid_eggs_per_week

    def lay_an_egg(self) -> None:
        print("chicken is laying an egg")

    def __str__(self) -> str:
        return (
            f"Chicken{{{super().__str__()}isMolting={bool_str(self.is_molting)}, "
            f"numberOfLaidEggsPerWeek={self.number_of_laid_eggs_per_week}}}"
        )

    @property
    def is_molting(self) -> bool:
        return self._is_molting

    @is_molting.setter
    def is_molting(self, value: bool) -> None:
        self._is_molting = value

    @property
    def number_of_laid_eggs_per_week(self) -> int:
        return self._number_of_laid_eggs_per_week

    @number_of_laid_eggs_per_week.setter
    def number_of_laid_eggs_per_week(self, value: int) -> None:
        self._number_of_laid_eggs_per_week = value
