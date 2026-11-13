from datetime import date
from animalfarmapp.model import FarmAnimal, Chicken, Cow

# @author Matias Paucar, ByteWorks, @ESPE


def main():
    print(" Matias's Animal Farm")
    print("=====================")

    farm_animal = FarmAnimal(1, "holstein", date.today())
    print("my farm animal is --> " + str(farm_animal))

    chicken = Chicken(True, 10, False, 2, "Palish", date(2022, 10, 10))
    print("chicken --> " + str(chicken))

    cow: FarmAnimal = Cow(3, "holstein", date.today(),
                          is_producing_milk=True, liters_of_milk_per_week=100.5)
    print("cow --> " + str(cow))


if __name__ == "__main__":
    main()
