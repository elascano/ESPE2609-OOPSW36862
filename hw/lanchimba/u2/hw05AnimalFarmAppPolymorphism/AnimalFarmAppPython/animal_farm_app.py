"""Main application view demonstrating Polymorphism in Python."""

from datetime import date
from model import Chicken, Cow, FarmAnimal, IFarmAnimal, Pig, Sheep


def main() -> None:
    # Substitutability Principle (Liskov Substitution):
    # A Chicken IS-A FarmAnimal, so a Chicken instance fits inside list[FarmAnimal].
    # Conversely, a FarmAnimal is NOT guaranteed to be a Chicken, so a FarmAnimal
    # cannot safely be placed into list[Chicken] (static type checkers reject it):
    #   chickens: list[Chicken] = []
    #   any_animal: FarmAnimal = Cow(99, "Holstein")
    #   chickens.append(any_animal)  # Type error: FarmAnimal is not assignable to Chicken
    farm_animals: list[FarmAnimal] = [
        Chicken(1, "Rhode Island Red", date(2025, 5, 10), False),
        Chicken(2, "Leghorn", date(2024, 11, 20), True),
        Cow(3, "Holstein", date(2022, 3, 15), True),
        Cow(4, "Jersey", date(2023, 8, 1), False),
        Pig(5, "Yorkshire", date(2025, 1, 12)),
        # Overloaded constructor with minimal parameters (born_on defaults to today):
        Pig(6, "Duroc"),
        Sheep(7, "Merino", date(2023, 4, 18), date(2026, 5, 10)),
        Sheep(8, "Suffolk", date(2024, 9, 5), date(2026, 8, 22)),
    ]

    print("=== ESPE Animal Farm App - Polymorphism (Python) ===")
    print(f"Total registered animals: {len(farm_animals)}")
    print("------------------------------------------------------------")

    total_monthly_cost: float = 0.0

    # Dynamic polymorphism: each concrete subclass executes its own overridden
    # methods at runtime without using if or isinstance checks.
    for animal in farm_animals:
        cost = animal.compute_food_cost()
        total_monthly_cost += cost

        print(animal)
        print(f"  -> Age: {animal.get_age_in_months()} months")
        print(f"  -> Feed (default): {animal.feed()}")
        print(f"  -> Feed (overloaded): {animal.feed('balanced farm mix', 2.5)}")
        print(f"  -> Production: {animal.produce()}")
        print(f"  -> Monthly food cost: ${cost:.2f}")

    print("------------------------------------------------------------")
    print(f"Total monthly farm food cost: ${total_monthly_cost:.2f}")

    # Interface-based polymorphism using an IFarmAnimal reference:
    interface_ref: IFarmAnimal = farm_animals[2]
    print("\n=== Interface Reference (IFarmAnimal) ===")
    print(f"Feed: {interface_ref.feed()}")
    print(f"Production: {interface_ref.produce()}")
    print(f"Monthly food cost: ${interface_ref.compute_food_cost():.2f}")


if __name__ == "__main__":
    main()
