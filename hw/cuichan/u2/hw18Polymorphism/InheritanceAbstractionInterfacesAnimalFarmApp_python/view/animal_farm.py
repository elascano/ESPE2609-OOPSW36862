import sys
import os
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))
from datetime import date
from model.chicken import Chicken
from model.sheep import Sheep
from model.pig import Pig
from model.cow import Cow
from model.food import Food
from model.location import Location

def main():
    born_on_date = date(2022, 9, 12)
    print("Hello This is Leslye's Animal Farms!")
    
    farm_animal = Chicken(True, 10, True, 1, "Palish", date.today())
    print(f"FarmAminal is a ---> {type(farm_animal)}")
    print(f"farm animal ---> {farm_animal}")
    
    cow = Cow(True, 150.0, 7, "holsteing", born_on_date)
    print(f"cow is {cow}")
    print(f"cow is a   {type(cow).__name__}")
    
    chicken = Chicken(True, 20, False, 2, "Palish", date.today())
    print(f"My Chicken {chicken}")
    
    location = Location(2, "PASTURE 1")
    
    sheep = Sheep(date.today(), True, False, 301, "Merino", date.today())
    print(f"My Sheep {sheep}")

    pig = Pig(401, "Landrace", date.today(), 110.5, True)
    print(f"My Pig {pig}")
    
    farm_animal_for_feeding = Chicken(True, 10, True, 5, "Palish", date.today())
    print(f"cow is a {type(farm_animal_for_feeding).__name__}")
    
    farm_animal_for_feeding = Cow(True, 100.5, 3, "holstein", date.today())
    print(f"cow is a {type(farm_animal_for_feeding).__name__}")
    
    farm_animal_for_feeding = Chicken(True, 4, True, 4, "PALEESH", date.today())
    print(f"cow is a {type(farm_animal_for_feeding).__name__}")
    
    cow.milk(3.4)
    
    food = Food("feed")
    cow.milk(3.5)
    chicken.lay_in_egg()
    cow.milk(20.5)
    cow.milk()
     
    chicken.feed(food, 2)
    cow.sendto_graze(location)
     
    sheep.feed(food, 3)
    sheep.born_on_date = born_on_date
    pig.feed(food, 5)

if __name__ == "__main__":
    main()