"""
@author Andres Pachacama, Byte Work, @ESPE
"""


class Operation:

    def add(self, addend1, addend2):
        sum = addend1 + addend2
        # complex algorithm
        return sum

    def subtract(self, minuend, subtrahend):
        difference = minuend - subtrahend
        return difference

    def multiply(self, multiplicand, multiplier):
        product = multiplicand * multiplier
        return product

    def divide(self, dividend, divisor):
        quotient = dividend / divisor
        return quotient
