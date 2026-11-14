# @author David Clavón, Infinite loops, @ESPE

from datetime import date, datetime
from model.chicken import Chicken
from model.cow import Cow
from model.farm_animal import FarmAnimal
from model.food import Food


def main():
    born_on_date = date(2022, 10, 12)  # En Java new Date(2022, 9, 12) equivale a octubre (mes 9 base 0)
    print("Hello This is David's Animal Farms!")

    farm_animal: FarmAnimal = Chicken(True, 10, True, 1, "Paleesh", date.today())
    print(f"FarmAminal is a ---> {type(farm_animal)}")
    print(f"farm animal ---> {farm_animal}")

    cow = Cow(True, 150.0, 7, "holsteing", born_on_date)
    print(f"cow is {cow}")
    print(f"cow is a  {type(cow).__name__}")

    chicken = Chicken(True, 20, False, 2, "Paleesh", date.today())

    print("==============================================================")
    food = Food("corn")
    chicken.feed(food, 2)

    chicken.lay_in_egg()
    food = Food("grass")
    cow.feed(food, 1)

    hour_milking = datetime.now()
    cow.milk(15, hour_milking)

    cow.milk(3.5)
    chicken.lay_in_egg()
    cow.milk(20.5)

    chicken.feed(food, 1)
    cow.milk()
    cow.milk(1.5)
    cow.milk(100)


if __name__ == "__main__":
    main()