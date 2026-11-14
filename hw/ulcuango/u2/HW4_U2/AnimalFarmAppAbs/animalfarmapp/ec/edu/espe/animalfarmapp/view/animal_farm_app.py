from datetime import date
from ec.edu.espe.animalfarmapp.model.farm_animal import FarmAnimal
from ec.edu.espe.animalfarmapp.model.chicken import Chicken
from ec.edu.espe.animalfarmapp.model.cow import Cow
from ec.edu.espe.animalfarmapp.model.pig import Pig
from ec.edu.espe.animalfarmapp.model.sheep import Sheep

def main():
    print("Josselyn' Farm Animal")
    print("==================")
    
    farm_animal = FarmAnimal(1, "Holstein", date.today())
    print("My farm Animal is --> ", farm_animal)
    
    print("My farm Animal is a --> ", type(farm_animal).__name__)
    
    # Equivalente a new Date(2022 - 1900, 10 - 1, 10) -> año 2022, mes 10, día 10
    chicken = Chicken(True, 10, False, 2, "Palish", date(2022, 10, 10))
    print("Chicken --> ", chicken)
    print("Farm animal is a  --> ", type(farm_animal).__name__)
    print("Chicken is a --> ", type(chicken))
    
    cow = Cow(3, "Lola", date.today(), True)
    print("Cow --> ", cow)
    
    # Manteniendo la pequeña referencia del código original de Java (chicken.getClass())
    print("cow is  a --> ", type(chicken))  
    
    pig = Pig(4, "Pumba", date.today())
    print("Pig --> ", pig)

    sheep = Sheep(5, "Cotton", date.today(), date.today())
    print("Sheep --> ", sheep)
    
    cow.milk()
    chicken.lay_an_egg()

if __name__ == "__main__":
    main()