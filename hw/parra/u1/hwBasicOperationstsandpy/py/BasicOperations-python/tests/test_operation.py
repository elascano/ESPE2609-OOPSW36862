import math
import unittest

from ec.edu.espe.basicoperations.model.operation import Operation


class OperationTest(unittest.TestCase):
    def setUp(self):
        self.instance = Operation()

    def test_add(self):
        self.assertAlmostEqual(self.instance.add(1.2, 2.4), 3.6)
        self.assertEqual(self.instance.add(0, 0), 0)

    def test_substract(self):
        self.assertAlmostEqual(self.instance.substract(5.5, 2.5), 3.0)
        self.assertEqual(self.instance.substract(0, 0), 0)

    def test_multiply(self):
        self.assertAlmostEqual(self.instance.multiply(1.5, 4), 6.0)
        self.assertEqual(self.instance.multiply(0, 7), 0)

    def test_divide(self):
        self.assertAlmostEqual(self.instance.divide(9, 3), 3.0)

    def test_divide_by_zero_returns_infinity(self):
        self.assertEqual(self.instance.divide(1, 0), math.inf)
        self.assertEqual(self.instance.divide(-1, 0), -math.inf)

    def test_divide_zero_by_zero_is_nan(self):
        # Este era el caso que fallaba en el test generado de Java (esperaba 0.0, daba NaN)
        self.assertTrue(math.isnan(self.instance.divide(0, 0)))


if __name__ == "__main__":
    unittest.main()
