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
    print(" Leslye's Animal Farm!")
    print("=================================================")
    
    farm_animal = FarmAnimal(1, "holstein", date.today())
    print(f"My farm animal --> {farm_animal}")
    
    chicken = Chicken(True, 10, False, 2, "Palish", date(2022, 10, 10))
    print(f"chicken --> {chicken}") 
    
    cow = Cow(True, 50.5, 3, "Holstein", date(2009, 1, 15))
    print(f"cow --> {cow}")

    pig = Pig(85.0, True, 4, "Landrace", date(2011, 5, 20))
    print(f"pig --> {pig}")

    sheep = Sheep(date(2024, 8, 1), True, True, 5, "Merino", date(1989, 11, 5))
    print(f"sheep --> {sheep}")
