from datetime import datetime
from model.food import Food
from model.location import Location
from model.chicken import Chicken
from model.cow import Cow
from model.pig import Pig
from model.sheep import Sheep


def main():
    food = Food("corn")
    location = Location("graze")
    chicken = Chicken(False, 10, 2, "palish", datetime(2022, 10, 10))
    cow = Cow(True, 23.5, 3, "Holstein", datetime(2024, 12, 23), False)
    pig = Pig(True, 30.23, 4, "Mangalitsa", datetime(2019, 11, 30), False)
    sheep = Sheep(True, datetime(2025, 11, 24), 5, "Manchega", datetime(2024, 9, 27), True)

    print("Isaac's Farm Animal")
    print("=======================")
    print("============")
    print("Chicken: ")
    print(chicken)
    chicken.layAnEgg()
    chicken.feed(food, 3)
    chicken.sellAnimal(12.50)

    food = Food("hay")
    print("============")
    print("Cow:")
    print(cow)
    cow.feed(food, 2)
    cow.sendToGraze(location, datetime.now())
    cow.milk()
    cow.milk(23.4)
    cow.milk(14)
    cow.getLitersOfMilkPerWeek()

    food = Food("corn")
    print("============")
    print("Pig:")
    print(pig)
    pig.feed(food, 12)
    pig.showPigMeatCuts()
    pig.getWeightGainPerWeek(32)
    pig.sellAnimal(130.4)

    food = Food("grass")
    print("============")
    print("Sheep:")
    print(sheep)
    sheep.feed(food, 3)
    sheep.getLastSheering()
    sheep.sendToGraze(location, datetime.now())
    sheep.shearSheep()


if __name__ == "__main__":
    main()
