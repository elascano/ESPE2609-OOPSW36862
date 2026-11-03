import unittest

from ellipse import Ellipse


class EllipseTest(unittest.TestCase):
    """Autor: Isaac Acurio, @ESPE"""

    DELTA = 0.01

    # 1. Caso normal: a=3, b=2 -> 18.8496
    def test_compute_area_normal_case(self):
        self.assertAlmostEqual(18.8496, Ellipse().compute_area(3, 2), delta=self.DELTA)

    # 2. Círculo (a = b): a=5, b=5 -> 78.5398
    def test_compute_area_circle(self):
        self.assertAlmostEqual(78.5398, Ellipse().compute_area(5, 5), delta=self.DELTA)

    # 3. Valores unitarios: a=1, b=1 -> 3.1416
    def test_compute_area_unit_values(self):
        self.assertAlmostEqual(3.1416, Ellipse().compute_area(1, 1), delta=self.DELTA)

    # 4. Decimales: a=2.5, b=1.5 -> 11.7810
    def test_compute_area_decimals(self):
        self.assertAlmostEqual(11.7810, Ellipse().compute_area(2.5, 1.5), delta=self.DELTA)

    # 5. Valores grandes: a=100, b=50 -> 15707.9633
    def test_compute_area_large_values(self):
        self.assertAlmostEqual(15707.9633, Ellipse().compute_area(100, 50), delta=0.1)

    # 6. Valores pequeños: a=0.5, b=0.5 -> 0.7854
    def test_compute_area_small_values(self):
        self.assertAlmostEqual(0.7854, Ellipse().compute_area(0.5, 0.5), delta=self.DELTA)

    # 7. Un semieje es cero: a=0, b=5 -> 0
    def test_compute_area_zero_semi_axis(self):
        self.assertAlmostEqual(0, Ellipse().compute_area(0, 5), delta=self.DELTA)

    # 8. Conmutatividad: area(a,b) == area(b,a)
    def test_compute_area_is_commutative(self):
        e = Ellipse()
        self.assertAlmostEqual(e.compute_area(4, 7), e.compute_area(7, 4), delta=self.DELTA)

    # 9. Elipse alargada: a=10, b=1 -> 31.4159
    def test_compute_area_elongated_ellipse(self):
        self.assertAlmostEqual(31.4159, Ellipse().compute_area(10, 1), delta=self.DELTA)

    # 10. Valor negativo: debe lanzar excepción
    def test_compute_area_negative_value(self):
        with self.assertRaises(ValueError):
            Ellipse().compute_area(-3, 2)


if __name__ == "__main__":
    unittest.main()