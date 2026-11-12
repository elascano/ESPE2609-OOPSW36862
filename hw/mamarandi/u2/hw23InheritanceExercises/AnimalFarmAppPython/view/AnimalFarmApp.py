import sys
from datetime import datetime
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent.parent))

from model.FarmAnimal import FarmAnimal
from model.Chicken import Chicken
from model.Cow import Cow
from model.Pig import Pig
from model.Sheep import Sheep
from model.JavaDate import java_date


def main() -> None:
    print("Francisco's Farm Animal")
    print("=======================")

    farmAnimal = FarmAnimal(1, "Holstein", datetime.now(), False)
    print("My Farm Animal is--->" + str(farmAnimal))

    chicken = Chicken(False, 10, 2, "palish", java_date(2022, 10, 10))
    print("chicken --->" + str(chicken))

    cow = Cow(True, 23.5, 3, "Holstein", java_date(2024, 12, 23), False)
    print("cow --->" + str(cow))

    pig = Pig(True, 30.23, 4, "Mangalitsa", java_date(2019, 11, 30), False)
    print("pig --->" + str(pig))

    sheep = Sheep(True, java_date(2025, 11, 24), 5, "Manchega", java_date(2024, 9, 27), True)
    print("sheep --->" + str(sheep))


if __name__ == "__main__":
    main()
