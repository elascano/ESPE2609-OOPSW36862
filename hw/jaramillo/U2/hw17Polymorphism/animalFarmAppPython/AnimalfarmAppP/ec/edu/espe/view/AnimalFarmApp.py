from datetime import datetime
from ec.edu.espe.model.Chicken import Chicken
from ec.edu.espe.model.Cow import Cow
from ec.edu.espe.model.Food import Food

def main():
    print("Hello. This is Julian Animal Farm")

    born_on_date = datetime(2020, 10, 10)
    food = Food("corn")

    farm_animal = Chicken(True, 4, True, 3, "Poultry", born_on_date)

    print(f"farmAnimal is a --> {type(farm_animal)}")
    print(f"My farm animal --> {farm_animal}")

    chicken = Chicken(True, 10, True, 2, "Paleesh", datetime.now())

    print(f"Chicken --> {chicken}")
    chicken.lay_an_egg()

    print(f"farmAnimal is a --> {type(chicken)}")
    print(f"farmAnimal is a --> {type(chicken).__name__}")

    cow = Cow(True, 100.5, 3, "holstein", datetime.now())

    print(f"Cow --> {cow}")
    print(f"Cow is --> {type(cow)}")
    print(f"Cow is --> {type(cow).__name__}")

    cow.milk()

    chicken1 = Chicken(True, 4, True, 0, "palish", born_on_date)
    print(f"Chicken --> {chicken1}")

    cow1 = Cow(True, 0.0, 0, "Molestein", born_on_date)

    print(f"Cow is --> {type(cow1)}")

    cow1.milk()

    chicken.feed(food, 1)

    food = Food("hay")

    cow1.feed(food, 1)
    
    food = Food("grass")
    cow.feed(food, 1)

    cow1.milk()
    cow1.milk(3.2)
    cow1.milk(100)
    cow1.milk(2.1)

    print(f"Total liters of milk per week: {cow1.liters_of_milk_per_week}")

if __name__ == "__main__":
    main()