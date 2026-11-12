from datetime import datetime, date
from model.farm_animal import FarmAnimal
from model.chicken import Chicken
from model.cow import Cow

"""
@author Daniel Sangoquiza, SaborCode, @ESPE
"""

def main():
    print("Daniel Sangoquiza's Farm Animal")
    print("=======================")

    farm_animal = FarmAnimal(1, "Holstein", datetime.now(), False)
    print(f"My Farm Animal is--->{farm_animal}")

    chicken = Chicken(False, 10, 2, "palish", date(2022, 10, 10))
    print(f"chicken --->{chicken}")

    cow = Cow(True, 23.5, 3, "Holstein", date(2024, 12, 23), False)
    print(f"cow --->{cow}")

if __name__ == "__main__":
    main()