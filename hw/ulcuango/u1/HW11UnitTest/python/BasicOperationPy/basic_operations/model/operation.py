class Operation:

  def add(self, addend1: float, addend2: float) -> float:
    sum_val = addend1 + addend2
    sum_val = round(sum_val * 100.0) / 100.0
    return sum_val

  def subtract(self, minuhend: float, subtrahend: float) -> float:
    difference = minuhend - subtrahend
    return difference

  def multiply(self, multiplicand: float, multiplier: float) -> float:
    product = multiplicand * multiplier
    return product

  def divide(self, dividend: float, divisor: float) -> float:
    if divisor == 0:
      raise ValueError("Division by zero is not allowed")
    quotient = dividend / divisor
    return quotient