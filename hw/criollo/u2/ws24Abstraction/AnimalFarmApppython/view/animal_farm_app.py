from datetime import date

from model.chicken import Chicken
from model.cow import Cow
from model.farm_animal import FarmAnimal
from model.pig import Pig
from model.sheep import Sheep


class AnimalFarmApp:
    """
    @author Dennis, Byte & Fabric Devs, @ESPE
    """

    @staticmethod
    def main() -> None:
        print("Dennis's Farm Animal")
        print("=======================")

        farmAnimal: FarmAnimal = Chicken(False, 10, 2, "palish", date(2022, 11, 10))
        print("chicken --->" + str(farmAnimal))
        print("chicken is a --->" + str(farmAnimal.__class__))
        print("chicken is a --->" + farmAnimal.__class__.__name__)

        chicken = Chicken(False, 10, 2, "palish", date(2022, 11, 10))
        print("chicken --->" + str(chicken))
        print("chicken is a --->" + chicken.__class__.__name__)

        cow = Cow(True, 23.5, 3, "Holstein", date(2025, 1, 23), False)
        print("cow --->" + str(cow))
        print("cow is a --->" + cow.__class__.__name__)

        pig = Pig(True, 30.23, 4, "Mangalitsa", date(2019, 12, 30), False)
        print("pig --->" + str(pig))
        print("pig is a --->" + pig.__class__.__name__)

        sheep = Sheep(True, date(2025, 12, 24), 5, "Manchega", date(2024, 10, 27), True)
        print("sheep --->" + str(sheep))
        print("sheep is a --->" + sheep.__class__.__name__)

        chicken.layAnEgg()
        cow.milk()


if __name__ == "__main__":
    AnimalFarmApp.main()
