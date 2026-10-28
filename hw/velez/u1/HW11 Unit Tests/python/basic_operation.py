"""
Operaciones básicas + Ley de Ohm, con pruebas unitarias.
Ejecutar:  python basic_operation.py -v
@author Yandry Velez, CodeLex, @ESPE
"""
import unittest


class Operation:
    def add(self, a: float, b: float) -> float:
        return round(a + b, 2)

    def subtract(self, a: float, b: float) -> float:
        return a - b

    def multiply(self, a: float, b: float) -> float:
        return a * b

    def divide(self, a: float, b: float) -> float:
        return a / b  # b = 0 lanza ZeroDivisionError


class Square:
    def calculate_voltage(self, current: float, resistance: float) -> float:
        return current * resistance

    def calculate_current(self, voltage: float, resistance: float) -> float:
        return voltage / resistance

    def calculate_resistance(self, voltage: float, current: float) -> float:
        return voltage / current


class BasicOperationTest(unittest.TestCase):
    def setUp(self):  # se ejecuta antes de cada prueba (@BeforeEach)
        self.op = Operation()
        self.sq = Square()

    # ---- Operation (5) ----
    def test_add(self):
        self.assertAlmostEqual(4.8, self.op.add(1.4, 3.4), places=2)

    def test_subtract(self):
        self.assertEqual(-2.0, self.op.subtract(2.0, 4.0))

    def test_multiply(self):
        self.assertEqual(6.0, self.op.multiply(3.0, 2.0))

    def test_divide(self):
        self.assertEqual(3.0, self.op.divide(6.0, 2.0))

    def test_divide_by_zero(self):
        with self.assertRaises(ZeroDivisionError):
            self.op.divide(1.0, 0.0)

    # ---- Square / Ley de Ohm (5) ----
    def test_calculate_voltage(self):
        self.assertEqual(20.0, self.sq.calculate_voltage(4.0, 5.0))

    def test_calculate_current(self):
        self.assertEqual(200.0, self.sq.calculate_current(500.0, 2.5))

    def test_calculate_resistance(self):
        self.assertEqual(200.0, self.sq.calculate_resistance(500.0, 2.5))

    def test_calculate_resistance_decimal(self):
        # 2/3 = 0.6666...: los decimales se comparan con tolerancia
        self.assertAlmostEqual(0.67, self.sq.calculate_resistance(2.0, 3.0), places=2)

    def test_calculate_current_decimal(self):
        # 13/3 = 4.3333...
        self.assertAlmostEqual(4.33, self.sq.calculate_current(13.0, 3.0), places=2)


if __name__ == "__main__":
    unittest.main()
