from datetime import date

class Chicken:
    def __init__(self, id_chicken: int, name: str, color: str, age: int, molting: bool, id_coop: int, date_of_birth: date):
        self.id_chicken = id_chicken
        self.name = name
        self.color = color
        self.age = age
        self.molting = molting
        self.id_coop = id_coop
        self.date_of_birth = date_of_birth

    def __str__(self):
        return (f"Chicken(id={self.id_chicken}, name='{self.name}', color='{self.color}', "
                f"age={self.age}, molting={self.molting}, coop={self.id_coop})")
