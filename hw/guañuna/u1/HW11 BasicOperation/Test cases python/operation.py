import math


class Operation:

    @staticmethod
    def calculate_sphere_area(radius: float) -> float:
        # Validar NaN (equivalente a Double.NaN en Java)
        if radius != radius:  # NaN nunca es igual a sí mismo
            return 0
        if radius < 0:
            return 0
        area = 4 * math.pi * math.pow(radius, 2)
        # Redondear a 3 decimales (Math.round(area * 1000) / 1000)
        area = round(area * 1000.0) / 1000.0
        return area

    def add(self, addend1: float, addend2: float) -> float:
        total = addend1 + addend2
        total = round(total * 100.0) / 100.0
        return total

    def substract(self, minuhend: float, subtrahend: float) -> float:
        difference = minuhend - subtrahend
        return difference

    def multiply(self, multiplicand: float, multiplier: float) -> float:
        product = multiplicand * multiplier
        return product

    def divide(self, dividend: float, divisor: float) -> float:
        if divisor == 0:
            raise ZeroDivisionError("No se puede dividir para cero")
        quotient = dividend / divisor
        return quotient