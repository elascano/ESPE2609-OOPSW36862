import sys
from datetime import date
from pathlib import Path

if __package__ in (None, ""):
    sys.path.insert(0, str(Path(__file__).resolve().parents[1]))

from model.chicken import Chicken
from model.cow import Cow
from model.farm_animal import FarmAnimal
from model.pig import Pig
from model.sheep import Sheep

# Demian Armas, Logical Lords, @ESPE

def main() -> None:
    today = date.today()
    chicken = Chicken(1, "Polish", today, False, 20, False)
    cow = Cow(2, "Holstein", today, True, 100.5)
    pig = Pig(95.5, True, 3, "Yorkshire", today)
    sheep = Sheep(today, True, 2.5, 4, "Merino", today)
    animals: list[FarmAnimal] = [chicken, cow, pig, sheep]

    print("Demian's Animal Farm")
    print("====================")
    for animal in animals:
        print(f"{type(animal).__name__} - {animal}")

    print(cow.milk())
    print(chicken.lay_an_egg())
    sheep.shear()


if __name__ == "__main__":
    main()
