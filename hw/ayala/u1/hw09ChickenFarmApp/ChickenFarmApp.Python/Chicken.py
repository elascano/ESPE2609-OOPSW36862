"""
@author Enrique Ayala, LogicLords,@ESPE
"""

from datetime import date
import Egg
import Poop

class Chicken:
    def __init__(self, chicken_id: int, name: str, color: str, born_on_date: date, molting: bool):
        self._id = chicken_id
        self._name = name
        self._color = color
        self._born_on_date = born_on_date
        self._molting = molting
        self._age_in_years = self.compute_age_in_years()
        self._compute_age_in_years_attr = self._age_in_years

    def do_stuff(self):
        self._cluck()
        self._eat()
        self._wander()
        self._drink(1.3)
        p = self._poop()
        print(f"poop -->{p}")
        print(f"egg{self._lay_an_egg()}")

    def _cluck(self):
        print(f"the chicken is clicking{self._name}is clucking...")

    def _wander(self):
        print(f"The chicken{self._name}is wandering...")

    def _eat(self):
        print(f"The chicken{self._name}is eating...")

    def _drink(self, amount_of_water: float):
        print(f"The chicken{self._name}is wandering-->{amount_of_water}ml of water")

    def _poop(self) -> Poop:
        return Poop(2, "green", 'M')

    def _lay_an_egg(self) -> Egg:
        return Egg(2, 'L')

    def compute_age_in_years(self) -> int:
        today = date.today()
        age = today.year - self._born_on_date.year
        if (today.month, today.day) < (self._born_on_date.month, self._born_on_date.day):
            age -= 1
        return age

    # Getters y Setters
    @property
    def id(self) -> int:
        return self._id

    @id.setter
    def id(self, chicken_id: int):
        self._id = chicken_id

    @property
    def name(self) -> str:
        return self._name

    @name.setter
    def name(self, name: str):
        self._name = name

    @property
    def color(self) -> str:
        return self._color

    @color.setter
    def color(self, color: str):
        self._color = color

    @property
    def born_on_date(self) -> date:
        return self._born_on_date

    @born_on_date.setter
    def born_on_date(self, born_on_date: date):
        self._born_on_date = born_on_date

    @property
    def age_in_years(self) -> int:
        return self._age_in_years

    def set_age_in_years(self):
        self._age_in_years = self.compute_age_in_years()

    @property
    def molting(self) -> bool:
        return self._molting

    @molting.setter
    def molting(self, molting: bool):
        self._molting = molting

    @property
    def compute_age_in_years_attr(self) -> int:
        return self._compute_age_in_years_attr

    @compute_age_in_years_attr.setter
    def compute_age_in_years_attr(self, value: int):
        self._compute_age_in_years_attr = value

    def __str__(self) -> str:
        return (f"Chicken{{id={self._id}, name='{self._name}', color='{self._color}', "
                f"bornOnDate={self._born_on_date}, age={self._age_in_years}, molting={self._molting}}}")