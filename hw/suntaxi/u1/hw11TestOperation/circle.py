import math


class Circle:

  def calculate_area(self, radius: float) -> float:
    if radius < 0:
      raise ValueError("Radius cannot be negative")
    return math.pi * (radius**2)

  def calculate_perimeter(self, radius: float) -> float:
    if radius < 0:
      raise ValueError("Radius cannot be negative")
    return 2 * math.pi * radius