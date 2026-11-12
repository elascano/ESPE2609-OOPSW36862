from datetime import date
from model.farm_animal import FarmAnimal
from model.chicken import Chicken
from model.cow import Cow
from model.pig import Pig
from model.sheep import Sheep

# @author David Clavón, Infinite Loops, @ESPE
def main():
    print("Hello This is David's Animal Farm")
    
    farm_animal = FarmAnimal(1, "holstein", date.today())
    print("my farm animal --->", farm_animal)
    
    chicken = Chicken(True, 10, True, 2, "Paleesh", date.today())
    print("chicken --->", chicken)
    
    cow = Cow(True, 100.5, 3, "holstein", date(2022, 8, 20))
    print("cow -->", cow)

    pig = Pig(85.5, True, 4, "Landrace", date.today())
    print("pig -->", pig)

    sheep = Sheep(date.today(), True, 4.2, 5, "Merino", date.today())
    print("sheep -->", sheep)

if __name__ == "__main__":
    main()