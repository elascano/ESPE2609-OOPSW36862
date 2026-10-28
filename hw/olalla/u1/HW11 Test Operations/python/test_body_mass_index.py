import unittest
from body_mass_index import BodyMassIndex


class BodyMassIndexTest(unittest.TestCase):
    def setUp(self):
        self.bmi = BodyMassIndex()

    def test_negative_weight_returns_negative_bmi(self):
        self.assertLess(self.bmi.calculate_bmi(-64.0, 1.6), 0)

    def test_zero_height_raises(self):
        with self.assertRaises(ValueError):
            self.bmi.calculate_bmi(60.0, 0.0)

    def test_calculate_bmi(self):
        self.assertAlmostEqual(24.22, self.bmi.calculate_bmi(70.0, 1.7), delta=0.01)

    def test_zero_weight(self):
        self.assertAlmostEqual(0.0, self.bmi.calculate_bmi(0.0, 1.7), delta=0.01)

    def test_integer_values(self):
        self.assertAlmostEqual(20.0, self.bmi.calculate_bmi(80.0, 2.0), delta=0.01)

    def test_small_weight(self):
        self.assertAlmostEqual(12.5, self.bmi.calculate_bmi(20.0, 1.264911), delta=0.01)

    def test_large_weight(self):
        self.assertAlmostEqual(40.0, self.bmi.calculate_bmi(100.0, 1.581139), delta=0.01)

    def test_height_one(self):
        self.assertAlmostEqual(60.0, self.bmi.calculate_bmi(60.0, 1.0), delta=0.01)

    def test_decimal_values(self):
        self.assertAlmostEqual(22.84, self.bmi.calculate_bmi(65.0, 1.687), delta=0.02)

    def test_negative_weight_exact(self):
        self.assertAlmostEqual(-25.0, self.bmi.calculate_bmi(-64.0, 1.6), delta=0.01)

    def test_negative_height(self):
        self.assertAlmostEqual(25.0, self.bmi.calculate_bmi(64.0, -1.6), delta=0.01)


if __name__ == "__main__":
    unittest.main()
