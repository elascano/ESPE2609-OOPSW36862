from utils.java_float import divide as _divide, to_float32


class Operation:
    def add(self, addend1: float, addend2: float) -> float:
        total = to_float32(to_float32(addend1) + to_float32(addend2))
        return total

    def substract(self, minuhend: float, subtrahend: float) -> float:
        difference = to_float32(to_float32(minuhend) - to_float32(subtrahend))
        return difference

    def multiply(self, multiplicand: float, multiplier: float) -> float:
        product = to_float32(to_float32(multiplicand) * to_float32(multiplier))
        return product

    def divide(self, dividend: float, divisor: float) -> float:
        quotient = _divide(dividend, divisor)
        return quotient
