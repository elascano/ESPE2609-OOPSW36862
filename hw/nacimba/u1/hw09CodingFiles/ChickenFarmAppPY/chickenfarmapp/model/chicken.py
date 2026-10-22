"""Chicken model."""
from datetime import date

from chickenfarmapp.model.egg import Egg
from chickenfarmapp.model.poop import Poop



class Chicken:
    def __init__(self, id, name, color, born_on_date, molting, poop=None):
        self.id = id
        self.name = name
        self.color = color
        self.born_on_date = born_on_date
        self.age_in_years = self.compute_age_in_years()
        self.molting = molting
        self.poop = poop

    def do_stuff(self):
        self._cluck()
        self._wander()
        self._eat()
        self._drink(1.3)
        print("poop --> " + str(self._poop()))
        print("egg --> " + str(self._lay_an_egg()))

    def _cluck(self):
        print(f"The chicken {self.name} is clucking ...")

    def _wander(self):
        print(f"The chicken {self.name} is wandering ...")

    def _eat(self):
        print(f"The chicken {self.name} is eating ...")

    def _drink(self, amount_of_water):
        print(f"The chicken {self.name} is drinking {amount_of_water} ml of water ...")

    def _poop(self):
        if self.poop is None:
            self.poop = Poop(self.id, "green", "M")
        return self.poop

    def _lay_an_egg(self):
        return Egg(self.id, "S")

    def compute_age_in_years(self):
        today = date.today()
        birth = self.born_on_date
        age = today.year - birth.year
        if (today.month, today.day) < (birth.month, birth.day):
            age -= 1
        return age

    def update_age_in_years(self):
        """Recalculates the age using the current date and the birth date."""
        self.age_in_years = self.compute_age_in_years()

    def set_born_on_date(self, born_on_date):
        self.born_on_date = born_on_date
        self.age_in_years = self.compute_age_in_years()

    def __str__(self):
        return (f"Chicken{{id={self.id}, name={self.name}, color={self.color}, "
                f"bornOnDate={self.born_on_date}, age={self.age_in_years}, "
                f"molting={str(self.molting).lower()}, poop={self.poop}}}")
