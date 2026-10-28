import math
import unittest

from bodymassindex.model.body_mass_index import BmiCategory, BodyMassIndex


class BodyMassIndexTest(unittest.TestCase):
    def setUp(self) -> None:
        self.bmi = BodyMassIndex()

    # --- Casos normales -------------------------------------------------
    def test_normal_weight(self):
        result = self.bmi.calculate_bmi(70.0, 1.75)
        self.assertAlmostEqual(result, 22.86, places=2)
        self.assertEqual(self.bmi.classify(result), BmiCategory.NORMAL_WEIGHT)

    def test_overweight(self):
        result = self.bmi.calculate_bmi(85.0, 1.80)
        self.assertAlmostEqual(result, 26.23, places=2)
        self.assertEqual(self.bmi.classify(result), BmiCategory.OVERWEIGHT)

    def test_underweight(self):
        result = self.bmi.calculate_bmi(48.0, 1.70)
        self.assertAlmostEqual(result, 16.61, places=2)
        self.assertEqual(self.bmi.classify(result), BmiCategory.UNDERWEIGHT)

    def test_obesity(self):
        result = self.bmi.calculate_bmi(110.0, 1.70)
        self.assertEqual(self.bmi.classify(result), BmiCategory.OBESITY)

    # --- Valores límite -------------------------------------------------
    def test_boundary_underweight_to_normal(self):
        result = self.bmi.calculate_bmi(74.0, 2.0)
        self.assertEqual(result, 18.5)
        self.assertEqual(self.bmi.classify(result), BmiCategory.NORMAL_WEIGHT)

    def test_boundary_normal_to_overweight(self):
        result = self.bmi.calculate_bmi(100.0, 2.0)
        self.assertEqual(result, 25.0)
        self.assertEqual(self.bmi.classify(result), BmiCategory.OVERWEIGHT)

    def test_boundary_overweight_to_obesity(self):
        result = self.bmi.calculate_bmi(120.0, 2.0)
        self.assertEqual(result, 30.0)
        self.assertEqual(self.bmi.classify(result), BmiCategory.OBESITY)

    def test_just_below_boundaries(self):
        self.assertEqual(self.bmi.classify(18.49), BmiCategory.UNDERWEIGHT)
        self.assertEqual(self.bmi.classify(24.99), BmiCategory.NORMAL_WEIGHT)
        self.assertEqual(self.bmi.classify(29.99), BmiCategory.OVERWEIGHT)

    # --- Redondeo half-up (no banker's rounding) ------------------------
    def test_rounds_half_up(self):
        # 0.5 / 4 = 0.125 -> half-up = 0.13 ; round() de Python daría 0.12
        self.assertEqual(self.bmi.calculate_bmi(0.5, 2.0), 0.13)

    # --- Entradas inválidas ----------------------------------------------
    def test_weight_zero(self):
        with self.assertRaisesRegex(ValueError, "weight"):
            self.bmi.calculate_bmi(0.0, 1.70)

    def test_height_zero(self):
        with self.assertRaisesRegex(ValueError, "height"):
            self.bmi.calculate_bmi(70.0, 0.0)

    def test_negative_weight(self):
        with self.assertRaises(ValueError):
            self.bmi.calculate_bmi(-70.0, 1.75)

    def test_negative_height(self):
        with self.assertRaises(ValueError):
            self.bmi.calculate_bmi(70.0, -1.75)

    def test_nan_and_infinity_rejected(self):
        for bad in (math.nan, math.inf, -math.inf):
            with self.subTest(value=bad):
                with self.assertRaises(ValueError):
                    self.bmi.calculate_bmi(bad, 1.75)
                with self.assertRaises(ValueError):
                    self.bmi.calculate_bmi(70.0, bad)

    def test_non_numeric_rejected(self):
        with self.assertRaises(TypeError):
            self.bmi.calculate_bmi("70", 1.75)  # type: ignore[arg-type]
        with self.assertRaises(TypeError):
            self.bmi.calculate_bmi(True, 1.75)  # type: ignore[arg-type]


if __name__ == "__main__":
    unittest.main()
