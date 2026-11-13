import unittest
from datetime import date

from animalfarmapp.model import Chicken, Cow, FarmAnimal, Pig, Sheep


class FarmAnimalTests(unittest.TestCase):
    def test_farm_animal_is_abstract(self):
        with self.assertRaises(TypeError):
            FarmAnimal(1, "x", date(2020, 1, 1), False)

    def test_subclasses_are_farm_animals(self):
        for animal in (
            Chicken(False, 10, 1, "a", date(2022, 10, 10)),
            Cow(True, 1.5, 2, "b", date(2022, 10, 10), False),
            Pig(True, 1.5, 3, "c", date(2022, 10, 10), False),
            Sheep(True, date(2025, 1, 1), 4, "d", date(2022, 10, 10), True),
        ):
            self.assertIsInstance(animal, FarmAnimal)

    def test_cow_to_string(self):
        cow = Cow(True, 23.5, 3, "Holstein", date(2024, 12, 23), False)
        self.assertEqual(
            str(cow),
            "Cow{FarmAnimal{id=3, breed=Holstein, is meat=false, bornOnDate=23 / 12 / 2024}, "
            "isProducingMilk=true, litersOfMilkPerWeek=23.5}",
        )

    def test_chicken_passes_is_molting_as_is_meat(self):
        chicken = Chicken(True, 5, 1, "x", date(2022, 10, 10))
        self.assertTrue(chicken.is_meat)

    def test_setters(self):
        pig = Pig(True, 1.0, 1, "x", date(2022, 1, 1), False)
        pig.weight_gain_per_week = 2.5
        pig.breed = "y"
        self.assertEqual(pig.weight_gain_per_week, 2.5)
        self.assertEqual(pig.breed, "y")


if __name__ == "__main__":
    unittest.main()
