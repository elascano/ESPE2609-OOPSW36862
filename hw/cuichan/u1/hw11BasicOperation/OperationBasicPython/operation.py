"""
@author Leslye Cuichan, Infinite loops, @eESPE
"""

class Operation:

    def add(self, addend1: float, addend2: float) -> float:
        sum_val = addend1 + addend2
        sum_val = round(sum_val, 2)
        return sum_val

    def subtract(self, minuhend: float, subtrahend: float) -> float:
        difference = minuhend - subtrahend
        return difference

    def multiply(self, multiplicand: float, multiplier: float) -> float:
        product = multiplicand * multiplier
        return product

    def divide(self, dividend: float, divisor: float) -> float:
        quotient = dividend / divisor
        return quotient