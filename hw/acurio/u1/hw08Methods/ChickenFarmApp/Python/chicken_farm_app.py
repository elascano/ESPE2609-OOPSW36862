from datetime import datetime
from models import Chicken, Egg, Poop
from chicken_coop import ChickenCoop


def main() -> None:
    print("Hello, Isaac Acurio Chicken Farm App")
    print("------------------------------------------")

    chicken = Chicken(1, "Lucy", "brown and white", datetime(2020, 2, 28), True)
    egg = Egg(1, "M")
    poop = Poop(1, "brown", "S")
    chicken1 = Chicken(2, "Arturita", "white", datetime(2007, 4, 28), True)
    chickens = [None] * 10

    print("chicken ---", chicken)
    print("egg ---", egg)
    print("poop---", poop)
    print("chicken 2 ---", chicken1)

    local_age = chicken.compute_age_in_years()
    print(f"the age of the chicken {chicken.name} is {local_age}")
    chicken.do_stuff()

    chickens[0] = Chicken(3, "Pepita", "Gray", datetime(2020, 2, 2), True)
    chickens[1] = chicken
    chickens[2] = chicken1

    chicken_coop = ChickenCoop(1, chickens)

    for i in range(len(chickens)):
        if i > 2:
            chickens[i] = Chicken(i + 1, "chicken", "black", datetime(2020 - i + 1, 4, 27), True)
        print("chicken----", chickens[i])

    # TESTING METHODS
    print("\n--- TESTING ITERATION WITH next() AND reset_iteration() ---")
    chicken_coop.reset_iteration()

    current = chicken_coop.next()
    while current is not None:
        print(f"Iterated chicken: {current.name} (ID: {current.id})")
        current = chicken_coop.next()

    print("\n--- TESTING REMOVE (remove) ---")
    print("Removing chicken with ID 1 (Lucy)...")
    chicken_coop.remove(1)

    print("\n--- TESTING ADD (add) ---")
    new_chicken = Chicken(99, "Little Chick", "Yellow", datetime.now(), True)
    print(f"Adding {new_chicken.name} into available space...")
    chicken_coop.add(new_chicken)

    print("\nFinal Chicken Coop status:")
    print(chicken_coop)


if __name__ == "__main__":
    main()
