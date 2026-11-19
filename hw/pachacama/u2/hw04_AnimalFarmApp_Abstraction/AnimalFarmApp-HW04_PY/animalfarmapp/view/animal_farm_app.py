# @author Andrés Pachacama, ByteWorks, @ESPE
from datetime import date, datetime

from animalfarmapp.model.chicken import Chicken
from animalfarmapp.model.cow import Cow
from animalfarmapp.model.farm_animal import FarmAnimal
from animalfarmapp.model.pig import Pig
from animalfarmapp.model.sheep import Sheep


def main():
    farm_animal: FarmAnimal

    print("Andres's Farm Animal")
    print("=======================")

    farm_animal = Chicken(False, 10, 2, "palish", date(3922, 11, 10))
    print("chicken --->" + str(farm_animal))
    animal_class = type(farm_animal)
    print("chicken is a --->class " + animal_class.__module__ + "." + animal_class.__name__)
    print("chicken is a --->" + animal_class.__name__)

    chicken = Chicken(False, 10, 2, "palish", date(3922, 11, 10))
    print("chicken --->" + str(chicken))
    print("chicken is a --->" + type(chicken).__name__)

    cow = Cow(True, 23.5, 3, "Holstein", date(3925, 1, 23), False)
    print("cow --->" + str(cow))
    print("cow is a --->" + type(cow).__name__)

    pig = Pig(True, 30.23, 4, "Mangalitsa", date(3919, 12, 30), False)
    print("pig --->" + str(pig))
    print("pig is a --->" + type(pig).__name__)

    sheep = Sheep(True, datetime(3925, 12, 24), 5, "Manchega", date(3924, 10, 27), True)
    print("sheep --->" + str(sheep))
    print("sheep is a --->" + type(sheep).__name__)

    chicken.lay_an_egg()
    cow.milk()
    pig.show_pig_meat_cuts()
    sheep.shear_sheep()


if __name__ == "__main__":
    main()
