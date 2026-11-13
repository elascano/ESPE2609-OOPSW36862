from ec.edu.espe.animalfarmapp.model.chicken import Chicken
from ec.edu.espe.animalfarmapp.model.cow import Cow

def main():
    print("ANIMAL FARM APP")

    chicken = Chicken(name="Pepa", age=1, breed="Rhode Island Red", is_molting=False)
    cow = Cow(name="Margarita", age=4, breed="Holstein", milk_production_liters=22.5)

    farm_animals = [chicken, cow]

    for animal in farm_animals:
        print(f"\nInformación: {animal}")
        print(f"Sonido: {animal.make_sound()}")
        
        if isinstance(animal, Chicken):
            print(animal.lay_egg())
        elif isinstance(animal, Cow):
            print(animal.produce_milk())

if __name__ == "__main__":
    main()
