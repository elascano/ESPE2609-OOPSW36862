from datetime import datetime, date
from model import FarmAnimal, Chicken, Cow, Pig, Sheep, Food, Location

def main():
    print("Hello. This is Cristopher Animal Farm\n")

    born_on_date = date(2020, 10, 10)
    
    corn = Food("corn")
    grass = Food("grass")
    north_pasture = Location(1, "North Pasture")

    # 1. CHICKEN
    chicken = Chicken(True, 10, True, 2, "Paleesh", born_on_date)
    print(f"Chicken --> {chicken}")
    chicken.feed(corn, 2)
    chicken.sen_to_graze(north_pasture, datetime.now())
    chicken.lay_an_egg()
    chicken.lay_an_egg(5)
    print()

    # 2. COW
    cow = Cow(True, 100.5, 3, "holstein", born_on_date)
    print(f"Cow --> {cow}")
    cow.feed(grass, 5)
    cow.sen_to_graze(north_pasture, datetime.now())
    
    cow.milk()
    cow.milk(3.2)
    cow.milk(100)
    cow.milk(2.5, datetime.now())
    print(f"Total liters of milk per week: {cow._liters_of_milk_per_week}")
    print()

    # 3. PIG
    pig = Pig(85.5, True, 4, "Duroc", born_on_date)
    print(f"Pig --> {pig}")
    pig.feed(corn, 10)
    pig.sen_to_graze(north_pasture, datetime.now())
    pig.eat()
    pig.eat(5.5)
    print()

    # 4. SHEEP
    sheep = Sheep(born_on_date, 5.5, 5, "Merino", born_on_date)
    print(f"Sheep --> {sheep}")
    sheep.feed(grass, 3)
    sheep.sen_to_graze(north_pasture, datetime.now())
    sheep.shear()
    sheep.shear(2.5)
    print()

if __name__ == "__main__":
    main()