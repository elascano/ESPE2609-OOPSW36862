import unittest
import sys
import os

sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '../src')))

from basicoperations.model.operation import Operation

class TestOperation(unittest.TestCase):
    
    @classmethod
    def setUpClass(cls):
        pass

    @classmethod
    def tearDownClass(cls):

        pass

    def setUp(self):
        self.instance = Operation()

    def tearDown(self):
        pass

    def test_add(self):
        print("add")
        addend1 = 1.2
        addend2 = 3.4
        exp_result = 4.6
        result = self.instance.add(addend1, addend2)
        self.assertAlmostEqual(exp_result, result, places=2)

    def test_substract(self):
        print("substract")
        minuend = 2.0
        subtrahend = 4.0
        exp_result = -2.0
        result = self.instance.substract(minuend, subtrahend)
        self.assertEqual(exp_result, result)

    def test_multiply(self):
        print("multiply")
        multiplicand = 3.0
        multiplier = 2.0
        exp_result = 6.0
        result = self.instance.multiply(multiplicand, multiplier)
        self.assertEqual(exp_result, result)

    def test_divide(self):
        print("divide")
        dividend = 6.0
        divisor = 2.0
        exp_result = 3.0
        result = self.instance.divide(dividend, divisor)
        self.assertEqual(exp_result, result)

if __name__ == "__main__":
    unittest.main()