from datetime import datetime
from model.farm_animal import FarmAnimal
from model.chicken import Chicken
from model.cow import Cow

class AnimalFarmApp:
    @staticmethod
    def main():
        print("David's Farm Animal")
        print("====================")
        born_on_date = datetime.now()
        breed = "Palish"

        farm_animal = FarmAnimal(1, breed, born_on_date)

        print(f"FarmAnimalis -->{type(farm_animal)}")
        farm_animal = Chicken(True, 0, True, 0, breed, born_on_date)
        print(f"farm animal --> {farm_animal}")
        print(f"farm Animal is a --> {type(farm_animal)}")

        farm_animal = Chicken(False, 20, False, 1, "layer", datetime.now())
        print(f"farmAnimal is a -->{type(farm_animal).__name__}")

        print(f"my Farm Animal -->{farm_animal}")

        chicken = Chicken(True, 10, True, 2, "Palish", datetime.now())
        print(f"Chicken -->{chicken}")
        print(f"chicken is a -->{type(chicken)}")

        cow = Cow(True, 100.5, 3, "holstein", datetime.now())
        print(f"Cow -->{cow}")
        print(f"Cow is a -->{type(cow)}")
        cow.milk()
        chicken.lay_an_egg()


if __name__ == "__main__":
    AnimalFarmApp.main()