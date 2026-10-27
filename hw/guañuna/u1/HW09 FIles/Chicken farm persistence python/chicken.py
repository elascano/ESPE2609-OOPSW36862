from datetime import datetime

class Chicken:
    def __init__(self, id, name, color, born_on_date, molting):
        self.id = id
        self.name = name
        self.color = color
        self.born_on_date = born_on_date
        self.age_in_years = 0
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

    def drink(self, amount_of_water):
        print(f"The chicken {self.name} is drinking...")

    def poop(self):
        # Importación local para evitar importaciones circulares
        from poop import Poop
        return Poop(2, "green", 'M')

    def lay_an_egg(self):
        from egg import Egg
        return Egg(2, 'S')

    def compute_age_in_years(self):
        today = datetime.now()
        age = today.year - self.born_on_date.year

        if today.month < self.born_on_date.month:
            age -= 1
        elif today.month == self.born_on_date.month:
            if today.day < self.born_on_date.day:
                age -= 1
            elif today.day == self.born_on_date.day:
                print("Today is this chicken's birthday!")

        return age

    def to_dict(self):
        return {
            "id": self.id,
            "name": self.name,
            "color": self.color,
            "bornOnDate": self.born_on_date.strftime("%Y-%m-%d") if self.born_on_date else None,
            "ageInYears": self.age_in_years,
            "molting": self.molting
        }

    @classmethod
    def from_dict(cls, data):
        fecha = datetime.strptime(data["bornOnDate"], "%Y-%m-%d") if data.get("bornOnDate") else None
        pollo = cls(data["id"], data["name"], data["color"], fecha, data["molting"])
        pollo.age_in_years = data.get("ageInYears", 0)
        return pollo

    def __str__(self):
        fecha_str = self.born_on_date.strftime('%a %b %d %H:%M:%S %Z %Y') if self.born_on_date else "null"
        return f"Chicken{{id={self.id}, name={self.name}, color={self.color}, bornOnDate={fecha_str}, age={self.compute_age_in_years()}, molting={self.molting}}}"