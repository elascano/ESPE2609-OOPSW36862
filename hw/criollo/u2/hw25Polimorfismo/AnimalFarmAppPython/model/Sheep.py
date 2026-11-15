from .FarmAnimal import FarmAnimal
from .GrazeAnimalI import GrazeAnimalI


class Sheep(FarmAnimal, GrazeAnimalI):
    def __init__(self, is_sheared: bool, last_sheering, animal_id: int,
                 breed: str, born_on_date, is_meat: bool):
        super().__init__(animal_id, breed, born_on_date, is_meat)
        self.isSheared = is_sheared
        self.lastSheering = last_sheering

    def feed(self, food, amount: int):
        print(f"feeding the sheep with {amount} kg of {food}")

    def sendToGraze(self, location, date_time):
        print(f"send the sheep to {location} at {date_time.hour} : {date_time.minute}")

    def shearSheep(self):
        print("Sheep is sheared")

    def __str__(self):
        last = self.lastSheering.strftime("%a %b %d %H:%M:%S UTC %Y")
        return (f"Sheep{{{super().__str__()}isSheared={str(self.isSheared).lower()}, "
                f"lastSheering={last}}}")
