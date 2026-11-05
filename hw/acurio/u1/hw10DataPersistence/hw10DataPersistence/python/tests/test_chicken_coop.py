import unittest
from datetime import date
from model.chicken import Chicken
from model.chicken_coop import ChickenCoop


class ChickenCoopTests(unittest.TestCase):
    def setUp(self):
        self.coop = ChickenCoop(1)

    def make_chicken(self, id):
        return Chicken(id, f"Chicken {id}", "white", date(2020, 1, 1))

    def test_rejects_duplicate_ids(self):
        self.assertTrue(self.coop.add(self.make_chicken(1)))
        self.assertFalse(self.coop.add(self.make_chicken(1)))

    def test_capacity_is_ten(self):
        for i in range(10):
            self.assertTrue(self.coop.add(self.make_chicken(i)))
        self.assertFalse(self.coop.add(self.make_chicken(10)))

    def test_remove_shifts_elements(self):
        for i in range(3):
            self.coop.add(self.make_chicken(i))
        self.assertTrue(self.coop.remove(1))
        self.assertEqual(self.coop.get_chickens()[1].get_id(), 2)
        self.assertIsNone(self.coop.get_chickens()[2])

    def test_json_round_trip(self):
        chicken = self.make_chicken(5)
        chicken.set_molting(True)
        reconstructed = Chicken.from_dict(chicken.to_dict())
        self.assertEqual(reconstructed.get_id(), 5)
        self.assertTrue(reconstructed.is_molting())
        self.assertEqual(reconstructed.get_born_on_date(), date(2020, 1, 1))


if __name__ == "__main__":
    unittest.main()
