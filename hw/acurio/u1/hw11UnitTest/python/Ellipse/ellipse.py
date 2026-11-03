import math

class Ellipse:
    """Autor: Isaac Acurio, @ESPE"""

    def compute_area(self, semi_axis_a: float, semi_axis_b: float) -> float:
        if semi_axis_a < 0 or semi_axis_b < 0:
            raise ValueError("Semi-axes cannot be negative")
        return math.pi * semi_axis_a * semi_axis_b