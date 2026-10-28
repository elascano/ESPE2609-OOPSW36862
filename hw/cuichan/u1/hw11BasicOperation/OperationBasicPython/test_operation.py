"""
@author Leslye Cuichan, Infinite loops, @eESPE
"""

import unittest
from operation import Operation

class OperationTest(unittest.TestCase):

    def test_add(self):
        print("add")
        addend1 = 1.2
        addend2 = 3.4
        instance = Operation()
        exp_result = 4.6
        result = instance.add(addend1, addend2)
        self.assertEqual(exp_result, result)

    def test_subtract(self):
        print("subtract")
        minuhend = 2.0
        subtrahend = 4.0
        instance = Operation()
        exp_result = -2.0
        result = instance.subtract(minuhend, subtrahend)
        self.assertEqual(exp_result, result)

    def test_multiply(self):
        print("multiply")
        multiplicand = 10.0
        multiplier = 0.0
        instance = Operation()
        exp_result = 0.0
        result = instance.multiply(multiplicand, multiplier)
        self.assertEqual(exp_result, result)

    def test_divide(self):
        print("divide")
        dividend = 0.0
        divisor = 0.1
        instance = Operation()
        exp_result = 0.0
        result = instance.divide(dividend, divisor)
        self.assertEqual(exp_result, result)

if __name__ == "__main__":
    unittest.main()