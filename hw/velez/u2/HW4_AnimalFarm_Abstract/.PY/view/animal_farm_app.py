from datetime import date
from model.chicken import Chicken
from model.cow import Cow
from model.pig import Pig
from model.sheep import Sheep

class AnimalFarmApp:
    def show_farm(self):
        print("Yandry's Farm Animal")
        print("=======================")

        farm_animal = Chicken(False, 10, 2, "palish", date(2022, 10, 10))
        print(f"chicken ---> {farm_animal}")
        print(f"chicken is a ---> {type(farm_animal).__name__}")

        chicken = Chicken(False, 10, 2, "palish", date(2022, 10, 10))
        cow = Cow(True, 23.5, 3, "Holstein", date(2024, 12, 23), False)
        pig = Pig(True, 30.23, 4, "Mangalitsa", date(2019, 11, 30), False)
        sheep = Sheep(True, date(2025, 11, 24), 5, "Manchega", date(2024, 9, 27), True)

        print(f"cow ---> {cow}")
        print(f"pig ---> {pig}")
        print(f"sheep ---> {sheep}")

        chicken.lay_an_egg()
        cow.milk()
        pig.show_pig_meat_cuts()
        sheep.shear_sheep()