from datetime import date
from Egg import Egg
from Poop import Poop
class Chicken:
    def __init__(self, id, name, color, bornOnDate, molting):
        self.id = id
        self.name = name
        self.color = color
        self.bornOnDate = bornOnDate
        self.age_in_years = 0
        self.molting = molting

    def do_stuff(self):
        self.cluck()
        self.wander()
        self.eat()
        self.drink(1.3)

        print("poop ----", self.poop())
        print("egg ----", self.layAnEgg())

    def _cluck(self):
        print(f"The chicken {self.name} is clucking")

    def _wander(self):
        print(f"The chicken {self.name} is wandering")

    def _eat(self):
        print(f"The chicken {self.name} is eating")

    def _drink(self, amountOfWater):
        print(f"The chicken {self.name} is drinking "f"{amountOfWater} ml of water")

    def _poop(self):
        return Poop(2, "green", 'M')

    def _layAnEgg(self):
        return Egg(2, 'S')

    def computeAgeInYears(self):
        today = date.today()
        self._ageInYears = today.year - self.bornOnDate.year
        if (today.month, today.day) < (self.bornOnDate.month,self.bornOnDate.day):
            self._ageInYears -= 1
        return self._ageInYears

    def __str__(self):
        return (f"Chicken{{id={self.id}, name={self.name}, "f"color={self.color}, bornOnDate={self.bornOnDate}, "f"age={self.computeAgeInYears()}, "f"molting={self.molting}}}")  