from datetime import date

from egg import Egg
from poop import Poop


class Chicken:
    def __init__(self, id: int, name: str, color: str, born_on_date: date, molting: bool = False):
        self.id = id
        self.name = name
        self.color = color
        self.born_on_date = born_on_date
        self.age_in_years = 0
        self.molting = molting

    def do_stuff(self) -> None:
        self._cluck()
        self._wander()
        self._drink(1.3)
        print("poop-->", self._poop())
        print("egg-->", self._lay_an_egg())

    def _cluck(self) -> None:
        print(f"The chicken {self.name} is clucking ...")

    def _wander(self) -> None:
        print(f"The chicken {self.name} is wandering ...")

    def _eat(self) -> None:
        print(f"The chicken {self.name} is eating ...")

    def _drink(self, amount_of_water: float) -> None:
        print(f"The chicken {self.name} is drinking --> {amount_of_water}ml of water")

    def _poop(self) -> Poop:
        return Poop(2, "green", "M")

    def _lay_an_egg(self) -> Egg:
        return Egg(2, "S")

    def compute_age_in_years(self) -> int:
        today = date.today()
        age = today.year - self.born_on_date.year
        if (today.month, today.day) < (self.born_on_date.month, self.born_on_date.day):
            age -= 1
        return age

    def set_age_in_years(self) -> None:
        self.age_in_years = self.compute_age_in_years()

    def get_age_in_years(self) -> int:
        return self.age_in_years

    def __str__(self) -> str:
        return (
            f"Chicken{{id={self.id}, name={self.name}, color={self.color}, "
            f"bornOnDate={self.born_on_date}, age={self.age_in_years}, molting={self.molting}}}"
        )

    __repr__ = __str__
