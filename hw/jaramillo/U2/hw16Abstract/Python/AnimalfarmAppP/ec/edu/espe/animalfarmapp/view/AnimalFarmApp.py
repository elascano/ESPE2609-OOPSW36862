from datetime import datetime
from ec.edu.espe.animalfarmapp.model.FarmAnimal import FarmAnimal
from ec.edu.espe.animalfarmapp.model.Chicken import Chicken
from ec.edu.espe.animalfarmapp.model.Cow import Cow


class AnimalFarmApp:
    @staticmethod
    def main() -> None:
        print("Julian's Farm Animal")
        print("====================")
        born_on_date = datetime.now()
        breed = "Palish"

        farm_animal: FarmAnimal
        farm_animal = Chicken(True, 0, True, 0, breed, born_on_date)
        print(f"farm animal -->{farm_animal}")
        print(f"farm Animal is a -->{type(farm_animal)}")

        farm_animal = Chicken(False, 20, False, 1, "layer", datetime.now())
        print(f"farmAnimal is a -->{type(farm_animal).__name__}")

        print(f"my Farm Animal -->{farm_animal}")

        chicken = Chicken(True, 10, True, 2, "Paleesh", datetime.now())
        print(f"Cchicken -->{chicken}")
        print(f"chicken is a -->{type(chicken)}")

        cow = Cow(True, 100.5, 3, "holstein", datetime.now())
        print(f"Cow -->{cow}")
        print(f"Cor is a -->{type(cow)}")


if __name__ == "__main__":
    AnimalFarmApp.main()