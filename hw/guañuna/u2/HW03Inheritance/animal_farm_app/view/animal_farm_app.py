from datetime import date
from model import FarmAnimal, Chicken, Cow, Pig, Sheep

def main():
    print("Cristopher's Farm Animals")
    print("=================================")

    today = date.today()

    farm_animal = FarmAnimal(1, "Holstein", today)
    print(f"My Farm Animal is ---> {farm_animal}")

    chicken = Chicken(True, 2, "polish", today)
    print(f"Chicken --> {chicken}")

    cow = Cow(True, 3, "Angus", today)
    print(f"Cow --> {cow}")

    pig = Pig(4, "Duroc", today)
    print(f"Pig --> {pig}")

    sheep = Sheep(today, 5, "Merino", today)
    print(f"Sheep --> {sheep}")

if __name__ == "__main__":
    main()