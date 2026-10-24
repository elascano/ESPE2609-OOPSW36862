from datetime import datetime
from model.egg import Egg
from model.poop import Poop

class Chicken:
    def __init__(self, ide, name, color, born_on_date, age, molting):
        self.ide = ide
        self.name = name
        self.color = color
        self.born_on_date = born_on_date
        self.age = age
        self.molting = molting

    def lay_egg(self):
        return Egg(0.05, "white")

    def poop_action(self):
        return Poop(1, "soft")

    def to_dict(self):
        return {
            "ide": self.ide,
            "name": self.name,
            "color": self.color,
            "born_on_date": self.born_on_date.strftime("%Y-%m-%d") if isinstance(self.born_on_date, datetime) else self.born_on_date,
            "age": self.age,
            "molting": self.molting
        }

    @classmethod
    def from_dict(cls, data):
        born_date = datetime.strptime(data["born_on_date"], "%Y-%m-%d") if isinstance(data["born_on_date"], str) else data["born_on_date"]
        return cls(
            data["ide"],
            data["name"],
            data["color"],
            born_date,
            data["age"],
            data["molting"]
        )

    def __str__(self):
        date_str = self.born_on_date.strftime("%Y-%m-%d") if isinstance(self.born_on_date, datetime) else self.born_on_date
        return f"Chicken [ID: {self.ide}, Name: {self.name}, Color: {self.color}, Birth Date: {date_str}, Age: {self.age} years, Molting: {self.molting}]"