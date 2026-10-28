import unittest
from operation import Operation


class OperationTest(unittest.TestCase):
    def setUp(self):
        self.operation = Operation()

    def test_add(self):
        self.assertAlmostEqual(4.6, self.operation.add(1.2, 3.4), places=2)

    def test_subtract(self):
        self.assertEqual(0.0, self.operation.subtract(0.0, 0.0))

    def test_multiply(self):
        self.assertEqual(0.0, self.operation.multiply(0.0, 0.0))

    def test_divide(self):
        self.assertEqual(2.0, self.operation.divide(6.0, 3.0))

    def test_divide_by_zero_raises(self):
        with self.assertRaises(ZeroDivisionError):
            self.operation.divide(1.0, 0.0)


if __name__ == "__main__":
    unittest.main()
