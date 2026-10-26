from datetime import date
from .egg import Egg
from .poop import Poop


class Chicken:
    def __init__(
        self,
        chicken_id: int,
        name: str,
        color: str,
        born_on_date: date,
        age_in_years: int,
        molting: bool
    ):
        self.id = chicken_id
        self.name = name
        self.color = color
        self.born_on_date = born_on_date
        self.age_in_years = age_in_years
        self.molting = molting

    def do_stuff(self) -> None:
        self._cluck()
        self._wander()
        self._drink(1.3)
        print("poop--> " + str(self._poop()))
        print("egg--> " + str(self._lay_an_egg()))

    def _cluck(self) -> None:
        print(f"The chicken {self.name} is clucking")

    def _wander(self) -> None:
        print(f"The chicken {self.name} is wandering")

    def _eat(self) -> None:
        print(f"The chicken {self.name} is eating")

    def _drink(self, amount_of_water: float) -> None:
        print(
            f"The chicken {self.name} is drinking --> "
            f"{amount_of_water} ml of water"
        )

    def _poop(self) -> Poop:
        return Poop(2, "green", "M")

    def _lay_an_egg(self) -> Egg:
        return Egg(2, "S")

    def compute_age_in_years(self) -> int:
        return max(0, (date.today() - self.born_on_date).days // 365)

    def set_age_in_years(self) -> None:
        self.age_in_years = self.compute_age_in_years()

    def __str__(self) -> str:
        return (
            f"Chicken{{id={self.id}, name={self.name}, color={self.color}, "
            f"bornOnDate={self.born_on_date}, age={self.age_in_years}, "
            f"molting={str(self.molting).lower()}}}"
        )

    def get_id(self) -> int:
        return self.id

    def set_id(self, chicken_id: int) -> None:
        self.id = chicken_id

    def get_name(self) -> str:
        return self.name

    def set_name(self, name: str) -> None:
        self.name = name

    def get_color(self) -> str:
        return self.color

    def set_color(self, color: str) -> None:
        self.color = color

    def get_born_on_date(self) -> date:
        return self.born_on_date

    def set_born_on_date(self, born_on_date: date) -> None:
        self.born_on_date = born_on_date

    def get_age_in_years(self) -> int:
        return self.age_in_years

    def is_molting(self) -> bool:
        return self.molting

    def set_molting(self, molting: bool) -> None:
        self.molting = molting
