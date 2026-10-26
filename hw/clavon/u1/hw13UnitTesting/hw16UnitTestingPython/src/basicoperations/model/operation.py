class Operation:
    
    def add(self, addend1: float, addend2: float) -> float:
        total = addend1 + addend2
        return round(total, 2)
    
    def substract(self, minuend: float, subtrahend: float) -> float:
        return minuend - subtrahend
    
    def multiply(self, multiplicand: float, multiplier: float) -> float:
        return multiplicand * multiplier
    
    def divide(self, dividend: float, divisor: float) -> float:
        if divisor == 0:
            raise ValueError("No se puede dividir entre cero")
        return dividend / divisor