from datetime import datetime, date
from model.Food import Food
from model.Location import Location
from model.Chicken import Chicken
from model.Cow import Cow
from model.Pig import Pig
from model.Sheep import Sheep


def main():
    food = Food("corn")
    location = Location("graze")

    chicken = Chicken(False, 10, 2, "palish", date(3922, 11, 10))
    cow = Cow(True, 23.5, 3, "Holstein", date(3924, 1, 23), False)
    pig = Pig(True, 30.23, 4, "Mangalitsa", date(3919, 12, 30), False)
    sheep = Sheep(True, datetime(3925, 12, 24), 5, "Manchega", date(3924, 10, 27), True)

    print("Francisco's Farm Animal")
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
    print(sheep.lastSheering.strftime("%a %b %d %H:%M:%S UTC %Y"))
    sheep.sendToGraze(location, datetime.now())
    sheep.shearSheep()


if __name__ == "__main__":
    main()
