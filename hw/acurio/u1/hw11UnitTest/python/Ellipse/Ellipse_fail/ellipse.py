class Ellipse:
    """Autor: Isaac Acurio, @ESPE  (VERSIÓN CON ERRORES)"""

    def compute_area(self, semi_axis_a: float, semi_axis_b: float) -> float:
        # ERROR 1: la validación está mal (usa 'and' y '<= 0')
        if semi_axis_a <= 0 and semi_axis_b <= 0:
            raise ValueError("Semi-axes cannot be negative")
        # ERROR 2: usa 3.14 en vez de math.pi
        return 3.14 * semi_axis_a * semi_axis_b