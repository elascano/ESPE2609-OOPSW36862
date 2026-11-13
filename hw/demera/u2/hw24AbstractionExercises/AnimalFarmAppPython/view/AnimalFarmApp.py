import sys
from pathlib import Path
sys.path.append(str(Path(__file__).resolve().parent.parent))
from model.FarmAnimal import FarmAnimal
from model.Chicken import Chicken
from model.Pig import Pig
from model.Sheep import Sheep
from model.Cow import Cow
from datetime import date

def main():
    print("Francisco's Farm Animal")
    print("=======================")
    
    farmAnimal = FarmAnimal(1, "Holstein", date(2026, 9, 27), True)
    chicken = Chicken(2, "palish", date(2022, 10, 12), False, False, 10)
    cow = Cow(3,"Holstein", date(2024, 12, 23), False, True, 10.23)
    pig = Pig(4, "Mangalitsa", date(2019, 11, 30), False, True, 37.5)
    sheep = Sheep(5, "Manchega", date(2025, 11 , 24), False, True, date(2024, 9, 27))
    
    print(farmAnimal)
    print(chicken) 
    print(cow)
    print(pig)
    print(sheep)
    
    chicken.layAnEgg()
    cow.milk()
    pig.showPigMeatCuts()
    sheep.sheerSheep()
    
    
    
main()
