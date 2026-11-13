"""
@author Ostin Torres, CodeLex, @ESPE

Run from the AnimalFarmAppPython folder:
    python -m ec.edu.espe.animalfarmapp.view.animal_farm_app
"""
from datetime import date

from ec.edu.espe.animalfarmapp.model.chicken import Chicken
from ec.edu.espe.animalfarmapp.model.cow import Cow
from ec.edu.espe.animalfarmapp.model.farm_animal import FarmAnimal
from ec.edu.espe.animalfarmapp.model.pig import Pig
from ec.edu.espe.animalfarmapp.model.sheep import Sheep


class AnimalFarmApp:

    @staticmethod
    def main() -> None:
        farm_animal: FarmAnimal

        print("Chris' Farm Animal")
        print("=======================")

        farm_animal = FarmAnimal(1, "Holstein", date.today(), False)
        print("My Farm Animal is--->" + str(farm_animal))

        chicken = Chicken(False, 10, 2, "palish", date(2022, 10, 10))
        print("chicken --->" + str(chicken))

        cow = Cow(True, 23.5, 3, "Holstein", date(2024, 12, 23), False)
        print("cow --->" + str(cow))

        pig = Pig(True, 30.23, 4, "Mangalitsa", date(2019, 11, 30), False)
        print("pig --->" + str(pig))

        sheep = Sheep(True, date(2025, 11, 24), 5, "Manchega", date(2024, 9, 27), True)
        print("sheep --->" + str(sheep))


if __name__ == "__main__":
    AnimalFarmApp.main()
