"""
@author Josselyn Ulcuango, Codelex, @ESPE
"""

from datetime import date
from model.egg import Egg
from model.poop import Poop

class Chicken:
    def __init__(self, chicken_id: int, name: str, color: str, born_on_date: date, age: int, molting: bool):
        self.id = chicken_id
        self.name = name
        self.color = color
        self.born_on_date = born_on_date
        self.age_in_years = age
        self.molting = molting

    def do_stuff(self):
        self._cluck()
        self._wander()
        self._drink(1.3)
        print(f"poop --> {self._poop()}")
        print(f"egg --> {self._lay_an_egg()}")

    def _cluck(self):
        print(f"The chicken {self.name} is clucking")

    def _wander(self):
        print(f"The chicken {self.name} is wandering")

    def _eat(self):
        print(f"The chicken {self.name} is eating")

    def _drink(self, amount_of_water: float):
        print(f"The chicken {self.name} is drinking --> {amount_of_water} ml of water")

    def _poop(self) -> Poop:
        return Poop(2, "green", "M")

    def _lay_an_egg(self) -> Egg:
        return Egg(2, "S")

    def compute_age_in_years(self) -> int:
        today = date.today()
        birth = self.born_on_date
        age = today.year - birth.year
        if (today.month, today.day) < (birth.month, birth.day):
            age -= 1
        return age

    def __str__(self):
        molting_str = "true" if self.molting else "false"
        formatted_date = self.born_on_date.strftime("%a %b %d 00:00:00 COT %Y")
        return (f"Chicken{{id={self.id}, name={self.name}, color={self.color}, "
                f"bornOnDate={formatted_date}, age={self.age_in_years}, molting={molting_str}}}")

    # Getters y Setters
    def get_id(self): return self.id
    def set_id(self, chicken_id): self.id = chicken_id
    def get_name(self): return self.name
    def set_name(self, name): self.name = name
    def get_color(self): return self.color
    def set_color(self, color): self.color = color
    def get_born_on_date(self): return self.born_on_date
    def set_born_on_date(self, born_on_date): self.born_on_date = born_on_date
    def get_age_in_years(self): return self.age_in_years
    def set_age_in_years(self, age): self.age_in_years = age
    def is_molting(self): return self.molting
    def set_molting(self, molting): self.molting = molting