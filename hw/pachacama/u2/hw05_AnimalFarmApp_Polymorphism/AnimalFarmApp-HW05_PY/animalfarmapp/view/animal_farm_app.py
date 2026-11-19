# @author Andrés Pachacama, ByteWorks, @ESPE
from datetime import date, datetime

from animalfarmapp.model.chicken import Chicken
from animalfarmapp.model.cow import Cow
from animalfarmapp.model.food import Food
from animalfarmapp.model.location import Location
from animalfarmapp.model.pig import Pig
from animalfarmapp.model.sheep import Sheep


def main():
    food = Food("corn")
    location = Location("graze")
    chicken = Chicken(False, 10, 2, "palish", date(3922, 11, 10))
    cow = Cow(True, 23.5, 3, "Holstein", date(3925, 1, 23), False)
    pig = Pig(True, 30.23, 4, "Mangalitsa", date(3919, 12, 30), False)
    sheep = Sheep(True, datetime(3925, 12, 24), 5, "Manchega", date(3924, 10, 27), True)

    print("Andres's Farm Animal")
    print("=======================")
    print("Chicken: ")
    print(chicken)
    chicken.lay_an_egg()
    chicken.feed(food, 3)
    chicken.sell_animal(12.50)

    food = Food("hay")
    print("Cow:")
    print(cow)
    cow.feed(food, 2)
    cow.send_to_graze(location, datetime.now())
    cow.milk()
    cow.milk(25.4)
    cow.milk(14)
    cow.liters_of_milk_per_week

    food = Food("corn")
    print("Pig:")
    print(pig)
    pig.feed(food, 12)
    pig.show_pig_meat_cuts()
    pig.get_weight_gain_per_week(32)
    pig.sell_animal(45.4)

    food = Food("grass")
    print("Sheep:")
    print(sheep)
    sheep.feed(food, 3)
    sheep.last_sheering
    sheep.send_to_graze(location, datetime.now())
    sheep.shear_sheep()


if __name__ == "__main__":
    main()
