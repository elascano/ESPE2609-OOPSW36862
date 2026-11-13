from datetime import date
from model.farm_animal import FarmAnimal
from model.chicken import Chicken
from model.cow import Cow
from model.pig import Pig
from model.sheep import Sheep


def main():
    print("Farm Animals")
    print("============")

    farm_animal = FarmAnimal(1, "Holstein", date.today(), False)
    print("My Farm Animal is --->", farm_animal)

    chicken = Chicken(False, 10, 2, "Palish", date(2022, 10, 10))
    print("Chicken --->", chicken)

    cow = Cow(True, 23.5, 3, "Holstein",
              date(2024, 12, 23), False)
    print("Cow --->", cow)

    pig = Pig(True, 30.23, 4, "Mangalitsa",
              date(2019, 11, 30), False)
    print("Pig --->", pig)

    sheep = Sheep(True, date(2025, 11, 24), 5,
                  "Manchega", date(2024, 9, 27), True)
    print("Sheep --->", sheep)
