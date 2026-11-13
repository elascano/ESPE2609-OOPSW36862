import os
import sys

sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

from datetime import date
from model.farm_animal import FarmAnimal
from model.Chicken import Chicken
from model.Cow import Cow
from model.Pig import Pig
from model.Sheep import Sheep

"""
@author Leslye Cuichan, Byte Fabric & devs, @ESPE
"""
if __name__ == "__main__":
    print("Leslye's Farm Animal")
    print("====================")
    
    born_on_date = date.today()
    breed = "Palish"
    
    farm_animal: FarmAnimal = Chicken(True, 0, True, 0, breed, born_on_date)
    print(f"farm animal -->{farm_animal}")
    print(f"farm Animal is a -->{type(farm_animal)}")
    
    farm_animal = Chicken(False, 20, False, 1, "layer", date.today())
    print(f"farmAnimal is a -->{type(farm_animal).__name__}")
    print(f"my Farm Animal -->{farm_animal}")
    
    chicken = Chicken(True, 10, True, 2, "Paleesh", date.today())
    print(f"Chicken -->{chicken}")
    print(f"chicken is a -->{type(chicken)}")
    print(f"chicken is a -->{type(chicken).__name__}")
    
    cow = Cow(True, 100.5, 3, "holstein", date.today())
    print(f"Cow -->{cow}")
    print(f"Cow is a -->{type(cow)}")
    print(f"Cow is a -->{type(cow).__name__}")
    
    pig = Pig(4, "Landrace", date(2011, 5, 20), 85.0, True)
    print(f"Pig -->{pig}")
    print(f"Pig is a -->{type(pig)}")
    print(f"Pig is a -->{type(pig).__name__}")

    sheep = Sheep(date(2024, 8, 1), True, True, 5, "Merino", date(1989, 11, 5))
    print(f"Sheep -->{sheep}")
    print(f"Sheep is a -->{type(sheep)}")
    print(f"Sheep is a -->{type(sheep).__name__}")

    chicken.layAnEgg()
    cow.milk()