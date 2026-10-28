
from math import floor


class Operation:
    def add(self, addend1: float, addend2: float) -> float:
        total = addend1 + addend2
        return floor(total * 100.0 + 0.5)

    def subtract(self, minuend: float, subtrahend: float) -> float:
        return minuend - subtrahend

    def multiply(self, multiplicand: float, multiplier: float) -> float:
        return multiplicand * multiplier

    def divide(self, dividend: float, divisor: float) -> float:
        return dividend / divisor
