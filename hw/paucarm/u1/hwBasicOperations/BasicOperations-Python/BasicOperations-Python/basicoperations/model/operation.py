"""
@author Paucar Matias, ByteWorks, @ESPE
"""
import math


class Operation:

    def add(self, addend1: float, addend2: float) -> float:
        sum_ = addend1 + addend2
        # complex algorithm
        # (math.floor(x + 0.5) imita a Math.round de Java; round() de Python redondea distinto)
        sum_ = math.floor(sum_ * 100.0 + 0.5) / 100.0
        return sum_

    def subtract(self, minuhend: float, subtrahend: float) -> float:
        difference = minuhend - subtrahend
        return difference

    def multiply(self, multiplicand: float, multiplier: float) -> float:
        product = multiplicand * multiplier
        return product

    def divide(self, dividend: float, divisor: float) -> float:
        # En Java, dividir un float para 0 devuelve Infinity (no lanza error).
        # Python lanzaria ZeroDivisionError, asi que se replica el comportamiento de Java.
        if divisor == 0:
            if dividend == 0:
                return math.nan
            return math.inf if dividend > 0 else -math.inf
        quotient = dividend / divisor
        return quotient
