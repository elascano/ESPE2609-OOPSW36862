from datetime import date
from Poop import Poop
from Egg import Egg

class Chicken:
    def __init__(self, id, name, color, born_on_date, age, molting):
        self.id = id
        self.name = name
        self.color = color
        self.born_on_date = born_on_date
        self.age_in_years = self.compute_age_in_years()
        self.molting = molting

    def do_stuff(self):
        self.cluck()
        self.wander()
        self.drink(1.3)
        print(f"poop --> {self.poop()}")
        print(f"egg --> {self.lay_an_egg()}")

    def cluck(self):
        print(f"The chicken {self.name} is clucking...")

    def wander(self):
        print(f"The chicken {self.name} is wandering...")

    def eat(self):
        print(f"The chicken {self.name} is eating...")

    def drink(self, amount_of_water):
        print(f"The chicken {self.name} is drinking --> {amount_of_water} ml of water")

    def poop(self):
        return Poop(2, "green", 'M')

    def lay_an_egg(self):
        return Egg(2, '5')

    def compute_age_in_years(self):
        if self.born_on_date is None:
            return 0

        today = date.today()
        if isinstance(self.born_on_date, date):
            age = today.year - self.born_on_date.year
            if (today.month, today.day) < (self.born_on_date.month, self.born_on_date.day):
                age -= 1
            return age + 1900
        return 0

    def get_id(self):
        return self.id

    def set_id(self, id):
        self.id = id

    def get_name(self):
        return self.name

    def set_name(self, name):
        self.name = name

    def get_color(self):
        return self.color

    def set_color(self, color):
        self.color = color

    def get_born_on_date(self):
        return self.born_on_date

    def set_born_on_date(self, born_on_date):
        self.born_on_date = born_on_date
        self.age_in_years = self.compute_age_in_years()

    def get_age_in_years(self):
        return self.age_in_years

    def set_age_in_years(self):
        self.age_in_years = self.compute_age_in_years()

    def is_molting(self):
        return self.molting

    def set_molting(self, molting):
        self.molting = molting

    def __str__(self):
        return f"Chicken{{id={self.id}, name={self.name}, color={self.color}, bornOnDate={self.born_on_date}, age={self.age_in_years}, molting={self.molting}}}"