"""Animal Farm App. Author: Mateo Nacimba, @ESPE"""
from datetime import datetime

from model import Chicken, Cow, Food, Location, Pig, Sheep


def main() -> None:
    food = Food("corn")
    location = Location("graze")
    chicken = Chicken(False, 10, 2, "palish", datetime(2022, 10, 10))
    cow = Cow(True, 23.5, 3, "Holstein", datetime(2024, 12, 23), False)
    pig = Pig(True, 30.23, 4, "Mangalitsa", datetime(2019, 11, 30), False)
    sheep = Sheep(True, datetime(2025, 11, 24), 5, "Manchega", datetime(2024, 9, 27), True)

    print("Mateo Nacimba's Farm Animals")
    print("=============================")
    print("-----------------------------")
    print("Chicken:")
    print(chicken)
    chicken.lay_an_egg()
    chicken.feed(food, 3)
    chicken.sell_animal(12.50)

    food = Food("hay")
    print("-----------------------------")
    print("Cow:")
    print(cow)
    cow.feed(food, 2)
    cow.send_to_graze(location, datetime.now())
    cow.milk()
    cow.milk(23.4)
    cow.milk(14)

    food = Food("corn")
    print("-----------------------------")
    print("Pig:")
    print(pig)
    pig.feed(food, 12)
    pig.show_pig_meat_cuts()
    pig.report_weight_gain(32)
    pig.sell_animal(130.4)

    food = Food("grass")
    print("-----------------------------")
    print("Sheep:")
    print(sheep)
    sheep.feed(food, 3)
    sheep.send_to_graze(location, datetime.now())
    sheep.shear_sheep()


if __name__ == "__main__":
    main()
