import unittest

from operation import Operation


class OperationTest(unittest.TestCase):
    def setUp(self):
        self.operation = Operation()

    def test_add(self):
        result = self.operation.add(1.2, 3.4)
        self.assertAlmostEqual(4.6, result, delta=0)

    def test_subtract(self):
        result = self.operation.subtract(2.0, 4.0)
        self.assertAlmostEqual(-2.0, result, delta=1e-6)

    def test_multiply(self):
        result = self.operation.multiply(3.0, 2.0)
        self.assertAlmostEqual(6.0, result, delta=1e-6)

    def test_divide(self):
        result = self.operation.divide(6.0, 2.0)
        self.assertAlmostEqual(3.0, result, delta=1e-6)

    def test_add_rounds_to_two_decimals(self):
        result = self.operation.add(1.236, 2.0)
        self.assertAlmostEqual(3.24, result, delta=1e-6)

    def test_add_rounds_positive_half_as_java(self):
        result = self.operation.add(1.125, 0.0)
        self.assertAlmostEqual(1.13, result, delta=1e-6)

    def test_add_rounds_negative_half_as_java(self):
        result = self.operation.add(-1.125, 0.0)
        self.assertAlmostEqual(-1.12, result, delta=1e-6)

    def test_add_negative_numbers(self):
        result = self.operation.add(-1.2, -3.4)
        self.assertAlmostEqual(-4.6, result, delta=1e-6)

    def test_subtract_decimals(self):
        result = self.operation.subtract(4.7, 1.2)
        self.assertAlmostEqual(3.5, result, delta=1e-6)

    def test_multiply_by_zero(self):
        result = self.operation.multiply(7.5, 0.0)
        self.assertEqual(0.0, result)

    def test_divide_decimal_result(self):
        result = self.operation.divide(7.0, 2.0)
        self.assertAlmostEqual(3.5, result, delta=1e-6)

    def test_divide_negative_dividend(self):
        result = self.operation.divide(-6.0, 2.0)
        self.assertAlmostEqual(-3.0, result, delta=1e-6)

    def test_divide_by_zero(self):
        with self.assertRaises(ZeroDivisionError):
            self.operation.divide(6.0, 0.0)


if __name__ == "__main__":
    unittest.main()
