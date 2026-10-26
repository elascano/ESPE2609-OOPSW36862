"""
@author Paucar Matias, ByteWorks, @ESPE
"""
from datetime import date


class Chicken:

    def __init__(self, id: int, name: str, color: str, born_on_date: date, molting: bool):
        self.__id = id
        self.__name = name
        self.__color = color
        self.__born_on_date = born_on_date
        self.__molting = molting
        self.__age_in_years = self.compute_age_in_years()

    # the age is based only on years
    def compute_age_in_years(self) -> int:
        today = date.today()
        age = today.year - self.__born_on_date.year
        self.__age_in_years = age
        return age

    def do_stuff(self):
        self.__cluck()
        self.__wander()
        self.__eat()
        self.__drink(1.3)

    # private methods (in Python they start with two underscores)
    def __cluck(self):
        print("The chicken " + self.__name + " is clucking ...")

    def __wander(self):
        print("The chicken " + self.__name + " is wandering ...")

    def __eat(self):
        print("The chicken " + self.__name + " is eating ...")

    def __drink(self, amount_of_water: float):
        print("The chicken " + self.__name + " is drinking ..." + str(amount_of_water) + "ml of water")

    def __str__(self):
        return ("Chicken{id=" + str(self.__id) + ", name=" + self.__name + ", color=" + self.__color
                + ", bornOnDate=" + str(self.__born_on_date) + ", age=" + str(self.__age_in_years)
                + ", molting=" + str(self.__molting).lower() + "}")

    # getters and setters
    def get_id(self) -> int:
        return self.__id

    def set_id(self, id: int):
        self.__id = id

    def get_name(self) -> str:
        return self.__name

    def set_name(self, name: str):
        self.__name = name

    def get_color(self) -> str:
        return self.__color

    def set_color(self, color: str):
        self.__color = color

    def get_born_on_date(self) -> date:
        return self.__born_on_date

    def set_born_on_date(self, born_on_date: date):
        self.__born_on_date = born_on_date

    def get_age_in_years(self) -> int:
        return self.__age_in_years

    def set_age_in_years(self):
        self.__age_in_years = self.compute_age_in_years()

    def is_molting(self) -> bool:
        return self.__molting

    def set_molting(self, molting: bool):
        self.__molting = molting
