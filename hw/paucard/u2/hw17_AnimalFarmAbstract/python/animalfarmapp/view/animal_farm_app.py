from datetime import date

from ..model import Chicken, Cow, FarmAnimal, Pig, Sheep


def main() -> None:
    print("Farm Animal")
    print("=======================")

    # Polimorfismo: una variable del tipo abstracto apuntando a una subclase.
    farm_animal: FarmAnimal = Chicken(False, 10, 2, "palish", date(2022, 10, 10))
    print("chicken --->" + str(farm_animal))
    print(f"chicken is a --->{type(farm_animal)}")
    print(f"chicken is a --->{type(farm_animal).__name__}")

    chicken = Chicken(False, 10, 2, "palish", date(2022, 10, 10))
    print("chicken --->" + str(chicken))
    print(f"chicken is a --->{type(chicken).__name__}")

    cow = Cow(True, 23.5, 3, "Holstein", date(2024, 12, 23), False)
    print("cow --->" + str(cow))
    print(f"cow is a --->{type(cow).__name__}")

    pig = Pig(True, 30.23, 4, "Mangalitsa", date(2019, 11, 30), False)
    print("pig --->" + str(pig))
    print(f"pig is a --->{type(pig).__name__}")

    sheep = Sheep(True, date(2025, 11, 24), 5, "Manchega", date(2024, 9, 27), True)
    print("sheep --->" + str(sheep))
    print(f"sheep is a --->{type(sheep).__name__}")

    chicken.lay_an_egg()
    cow.milk()
    pig.show_pig_meat_cuts()
    sheep.shear_sheep()


if __name__ == "__main__":
    main()
