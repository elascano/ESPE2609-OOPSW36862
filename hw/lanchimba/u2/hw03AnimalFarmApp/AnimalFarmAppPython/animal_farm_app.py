"""Main application view for Animal Farm App (Python)."""

from datetime import date
from model import Chicken, Cow, FarmAnimal, Pig, Sheep


def main() -> None:
    farm_animals: list[FarmAnimal] = [
        Chicken(1, "Rhode Island Red", date(2025, 5, 10), False),
        Chicken(2, "Leghorn", date(2024, 11, 20), True),
        Cow(3, "Holstein", date(2022, 3, 15), True),
        Cow(4, "Jersey", date(2023, 8, 1), False),
        Pig(5, "Yorkshire", date(2025, 1, 12)),
        Pig(6, "Duroc", date(2024, 6, 30)),
        Sheep(7, "Merino", date(2023, 4, 18), date(2026, 5, 10)),
        Sheep(8, "Suffolk", date(2024, 9, 5), date(2026, 8, 22)),
    ]

    print("=== ESPE Animal Farm App (Python) ===")
    print(f"Total registered animals: {len(farm_animals)}")
    print("------------------------------------------------------------")

    for animal in farm_animals:
        print(animal)
        print(f"  -> Age in months: {animal.get_age_in_months()} months")


if __name__ == "__main__":
    main()
