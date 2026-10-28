"""Modelo Operation.

@author David Clavón, Infinite Loops, @ESPE
"""
import math


class Operation:
    def add(self, addend1: float, addend2: float) -> float:
        total = addend1 + addend2
        return total

    def substract(self, minuhed: float, subtrahend: float) -> float:
        difference = minuhed - subtrahend
        return difference

    def multiply(self, multiplicand: float, multiplier: float) -> float:
        product = multiplicand * multiplier
        return product

    def divide(self, dividend: float, divisor: float) -> float:
        # Igual que en Java (float): x/0 -> ±Infinity y 0/0 -> NaN,
        # en lugar de lanzar ZeroDivisionError como hace Python por defecto.
        if divisor == 0:
            if dividend == 0 or math.isnan(dividend):
                return math.nan
            negative = (dividend < 0) != (math.copysign(1.0, divisor) < 0)
            return -math.inf if negative else math.inf
        quotient = dividend / divisor
        return quotient
