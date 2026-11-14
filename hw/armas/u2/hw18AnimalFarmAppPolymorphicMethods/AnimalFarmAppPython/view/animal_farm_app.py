import sys
from datetime import date
from pathlib import Path

if __name__ == "__main__" and not __package__:
    sys.path.insert(0, str(Path(__file__).resolve().parents[1]))

from model.chicken import Chicken
from model.cow import Cow
from model.farm_animal import FarmAnimal
from model.food import Food
from model.location import Location
from model.pig import Pig
from model.sheep import Sheep


class AnimalFarmApp:
    @staticmethod
    def main() -> None:
        born_on_date = date(2026, 11, 12)
        chicken = Chicken(1, "Polish", born_on_date, True, 4, False)
        cow = Cow(2, "Holstein", born_on_date, True, 0.0)
        sheep = Sheep(born_on_date, True, 2.5, 3, "Merino", born_on_date)
        pig = Pig(80.5, True, 4, "Yorkshire", born_on_date)

        feeding_schedule: list[tuple[FarmAnimal, Food, int]] = [
            (chicken, Food("corn"), 1),
            (cow, Food("grass"), 5),
            (sheep, Food("hay"), 3),
            (pig, Food("pellets"), 4),
        ]

        print("Animal Farm - Polymorphic Methods")
        for animal, food, amount in feeding_schedule:
            print(f"{type(animal).__name__}: {animal}")
            print(animal.feed(food, amount))

        print(chicken.lay_an_egg())
        print(chicken.lay_eggs(3))
        print(f"Total eggs laid this month: {chicken.eggs_laid_this_month}")

        print(cow.graze(Location(1, "the pasture")))
        print(sheep.shear())
        print(sheep.shear(1.5))
        print(sheep.shear(2))
        print(f"Total wool collected this month: {sheep.kg_of_wool_for_month} kg")

        print(pig.record_cut("pork loin", 2.5))
        print(pig.record_cut("ribs", 1.5))
        print(f"Total pork cuts recorded: {pig.total_cut_weight:.1f} kg")

        print(cow.milk())
        print(cow.milk(3.5))
        print(cow.milk(100))
        print(f"Total liters of milk per week: {cow.liters_of_milk_per_week}")


if __name__ == "__main__":
    AnimalFarmApp.main()
