"""
@author Andres Pachacama, Byte Work, @ESPE
"""
from datetime import date

from model.egg import Egg
from model.poop import Poop


class Chicken:

    def __init__(self, id, name, color, born_on_date, age_in_years, molting):
        self.id = id
        self.name = name
        self.color = color
        self.born_on_date = born_on_date     
        self.age_in_years = age_in_years
        self.molting = molting

    def do_stuff(self):
        self._cluck()
        self._wander()
        self._drink(1.3)
        print("poop ---> " + str(self._poop()))
        print("egg ---> " + str(self._lay_an_egg()))

    def _cluck(self):
        print("The chicken " + self.name + " is clucking ...")

    def _wander(self):
        print("The chicken " + self.name + " is wandering ...")

    def _eat(self):
        print("The chicken " + self.name + " is eating ...")

    def _drink(self, amount_of_water):
        print("The chicken " + self.name + " is drinking --> " + str(amount_of_water) + "ml of water")

    def _poop(self):
        return Poop(2, "green", "M")

    def _lay_an_egg(self):
        return Egg(2, "S")

    def compute_age_in_years(self):
        today = date.today()
        age = today.year - self.born_on_date.year

        if (today.month, today.day) < (self.born_on_date.month, self.born_on_date.day):
            age = age - 1

        return age

    def set_age_in_years(self):
        """computes the age from born_on_date and saves it in age_in_years"""
        self.age_in_years = self.compute_age_in_years()

    def __str__(self):
        return ("\n Chicken{id=" + str(self.id) + ", name=" + self.name + ", color=" + self.color
                + ", bornOnDate=" + str(self.born_on_date) + ", age=" + str(self.age_in_years)
                + ", molting=" + str(self.molting) + "}")
