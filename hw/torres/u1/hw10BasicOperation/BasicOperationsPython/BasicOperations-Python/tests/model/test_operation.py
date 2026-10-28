"""
@author Paucar Matias, ByteWorks, @ESPE
"""
import pytest

from basicoperations.model.operation import Operation


class TestOperation:

    def setup_method(self):
        self.instance = Operation()

    def test_add(self):
        print("add")
        addend1 = 1.0
        addend2 = 3.4
        exp_result = 4.6
        result = self.instance.add(addend1, addend2)
        assert result == exp_result

    def test_add_other_decimals(self):
        print("add")
        # read from file and initialize the values
        addend1 = 1.4
        addend2 = 3.4
        exp_result = 4.8
        result = self.instance.add(addend1, addend2)
        assert result == exp_result

    def test_subtract(self):
        print("subtract")
        minuhend = 2.0
        subtrahend = 4.0
        exp_result = -2.0
        result = self.instance.subtract(minuhend, subtrahend)
        assert result == exp_result

    def test_multiply(self):
        print("multiply")
        multiplicand = 3.0
        multiplier = 2.0
        exp_result = 6.0
        result = self.instance.multiply(multiplicand, multiplier)
        assert result == exp_result

    def test_divide(self):
        print("divide")
        dividend = 6.0
        divisor = 2.0
        exp_result = 3.0
        result = self.instance.divide(dividend, divisor)
        assert result == exp_result
