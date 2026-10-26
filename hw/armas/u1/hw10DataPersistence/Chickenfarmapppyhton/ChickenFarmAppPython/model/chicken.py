from datetime import date
from model.egg import Egg
from model.poop import Poop


class Chicken:

    def __init__(
        self, id_chicken: int, name: str, color: str, born_on_date: date
    ):
        self._id = id_chicken
        self._name = name
        self._color = color
        self._born_on_date = born_on_date
        self._molting = False
        self._age_in_years = self.compute_age_in_years()

    def get_id(self) -> int:
        return self._id

    def set_id(self, id_chicken: int):
        self._id = id_chicken

    def get_name(self) -> str:
        return self._name

    def set_name(self, name: str):
        self._name = name

    def get_color(self) -> str:
        return self._color

    def set_color(self, color: str):
        self._color = color

    def get_born_on_date(self) -> date:
        return self._born_on_date

    def set_born_on_date(self, born_on_date: date):
        self._born_on_date = born_on_date

    def get_age_in_years(self) -> int:
        return self._age_in_years

    def set_age_in_years(self, age_in_years: int):
        self._age_in_years = age_in_years

    def is_molting(self) -> bool:
        return self._molting

    def set_molting(self, molting: bool):
        self._molting = molting

    def cluck(self):
        print(f"The chicken {self._name} is clucking ...")

    def wander(self):
        print(f"The chicken {self._name} is wandering ...")

    def eat(self):
        print(f"The chicken {self._name} is eating ...")

    def drink(self, amount_of_water: float):
        print(
            f"The chicken {self._name} is drinking --> {amount_of_water} ml of water"
        )

    @staticmethod
    def poop() -> Poop:
        return Poop(1, "green", "S")

    @staticmethod
    def lay_an_egg() -> Egg:
        return Egg(1, "M")

    def compute_age_in_years(self) -> int:
        today = date.today()
        age = today.year - self._born_on_date.year
        if (today.month, today.day) < (
            self._born_on_date.month,
            self._born_on_date.day,
        ):
            age -= 1
        return age

    def do_stuff(self, amount_of_water: float):
        self.cluck()

    def __str__(self):
        return (
            f"Chicken{{id={self._id}, name={self._name}, color={self._color}, "
            f"bornOnDate={self._born_on_date}, molting={self._molting}, "
            f"ageInYears={self._age_in_years}}}"
        )

    __repr__ = __str__