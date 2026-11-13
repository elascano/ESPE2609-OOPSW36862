from datetime import date
from model.farm_animal import FarmAnimal
from model.chicken import Chicken
from model.cow import Cow
from model.pig import Pig
from model.sheep import Sheep


def main():
    print("Isaac Farm Animal")
    print("=======================")

    farmAnimal: FarmAnimal = Chicken(False, 10, 2, "palish", date(2022, 10, 10))
    print("chicken --->" + str(farmAnimal))
    print("chicken is a --->" + str(type(farmAnimal)))
    print("chicken is a --->" + type(farmAnimal).__name__)

    chicken = Chicken(False, 10, 2, "palish", date(2022, 10, 10))
    print("chicken --->" + str(chicken))
    print("chicken is a --->" + type(chicken).__name__)

    cow = Cow(True, 23.5, 3, "Holstein", date(2024, 12, 23), False)
    print("cow --->" + str(cow))
    print("cow is a --->" + type(cow).__name__)

    pig = Pig(True, 30.23, 4, "Mangalitsa", date(2019, 11, 30), False)
    print("pig --->" + str(pig))
    print("pig is a --->" + type(pig).__name__)

    sheep = Sheep(True, date(2025, 11, 24), 5, "Manchega", date(2024, 9, 27), True)
    print("sheep --->" + str(sheep))
    print("sheep is a --->" + type(sheep).__name__)

    chicken.layAnEgg()
    cow.milk()
    pig.showPigMeatCuts()
    sheep.shearSheep()


if __name__ == "__main__":
    main()
