from datetime import date
from model import FarmAnimal, Chicken, Cow, Pig, Sheep

def main():
    print("Cristopher's Animal Farm")
    print("========================")
    
    born_on_date = date(2020, 11, 10)
    
    farm_animal = FarmAnimal(1, "Holstein", born_on_date)
    print(f"FarmAnimal --> is a {type(farm_animal).__name__}")
    print(f"FarmAnimal --> {type(farm_animal)}")
    print(f"farmAnimal ---> {farm_animal}")
    print()

    farm_animal = Chicken(True, 10, False, 2, "Paleesh", born_on_date)
    print(f"FarmAnimal --> is a {type(farm_animal).__name__}")
    print(f"FarmAnimal --> {type(farm_animal)}")
    print(f"chicken ---> {farm_animal}")
    farm_animal.lay_an_egg()
    print()

    farm_animal = Cow(True, 100.5, 3, "Holstein", born_on_date)
    print(f"FarmAnimal --> is a {type(farm_animal).__name__}")
    print(f"FarmAnimal --> {type(farm_animal)}")
    print(f"cow ---> {farm_animal}")
    farm_animal.milk()
    print()

    farm_animal = Pig(85.5, True, 4, "Duroc", born_on_date)
    print(f"FarmAnimal --> is a {type(farm_animal).__name__}")
    print(f"FarmAnimal --> {type(farm_animal)}")
    print(f"pig ---> {farm_animal}")
    farm_animal.eat()
    print()

    farm_animal = Sheep(born_on_date, 5.5, 5, "Merino", born_on_date)
    print(f"FarmAnimal --> is a {type(farm_animal).__name__}")
    print(f"FarmAnimal --> {type(farm_animal)}")
    print(f"sheep ---> {farm_animal}")
    farm_animal.shear()

if __name__ == "__main__":
    main()