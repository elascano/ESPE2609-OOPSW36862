import unittest

from ec.edu.espe.baiscoperation.model.centripetal_acceleration import CentripetalAcceleration
from utils.java_float import format_float, to_float32


class CentripetalAccelerationTest(unittest.TestCase):
    """@author Nacimba Mateo, Group5, @ESPE"""

    @classmethod
    def setUpClass(cls) -> None:
        pass

    @classmethod
    def tearDownClass(cls) -> None:
        pass

    def setUp(self) -> None:
        pass

    def tearDown(self) -> None:
        pass

    def assert_equals(self, exp_result: float, result: float, delta: float = 0) -> None:
        """Equivale a assertEquals(float, float, delta) de JUnit 5."""
        exp, res = to_float32(exp_result), to_float32(result)
        both_nan = exp != exp and res != res
        if not both_nan and not (exp == res or abs(exp - res) <= delta):
            self.fail(f"expected: <{format_float(exp)}> but was: <{format_float(res)}>")

    def test_calculate(self) -> None:
        """Test of calculate method, of class CentripetalAcceleration."""
        print("calculate")
        velocity = 0.0
        radius = 0.0
        instance = CentripetalAcceleration()
        exp_result = 0.0
        result = instance.calculate(velocity, radius)
        self.assert_equals(exp_result, result, 0)

    def test_calculate1(self) -> None:
        """PASA: 10² / 5 = 20"""
        print("calculate1")
        velocity = 10.0
        radius = 5.0
        instance = CentripetalAcceleration()
        exp_result = 20.0
        result = instance.calculate(velocity, radius)
        self.assert_equals(exp_result, result, 0)

    def test_calculate2(self) -> None:
        """PASA: velocidad 0 → aceleración 0"""
        print("calculate2")
        velocity = 0.0
        radius = 5.0
        instance = CentripetalAcceleration()
        exp_result = 0.0
        result = instance.calculate(velocity, radius)
        self.assert_equals(exp_result, result, 0)

    def test_calculate3(self) -> None:
        """PASA: 3² / 4 = 2.25"""
        print("calculate3")
        velocity = 3.0
        radius = 4.0
        instance = CentripetalAcceleration()
        exp_result = 2.25
        result = instance.calculate(velocity, radius)
        self.assert_equals(exp_result, result, 0)

    def test_calculate4(self) -> None:
        """PASA: 6² / 3 = 12"""
        print("calculate4")
        velocity = 6.0
        radius = 3.0
        instance = CentripetalAcceleration()
        exp_result = 12.0
        result = instance.calculate(velocity, radius)
        self.assert_equals(exp_result, result, 0)

    def test_calculate5(self) -> None:
        """PASA: (-4)² / 2 = 8"""
        print("calculate5")
        velocity = -4.0
        radius = 2.0
        instance = CentripetalAcceleration()
        exp_result = 8.0
        result = instance.calculate(velocity, radius)
        self.assert_equals(exp_result, result, 0)

    def test_calculate6(self) -> None:
        """PASA: 5² / 2 = 12.5"""
        print("calculate6")
        velocity = 5.0
        radius = 2.0
        instance = CentripetalAcceleration()
        exp_result = 12.5
        result = instance.calculate(velocity, radius)
        self.assert_equals(exp_result, result, 0)

    def test_calculate7(self) -> None:
        """PASA: 20² / 10 = 40"""
        print("calculate7")
        velocity = 20.0
        radius = 10.0
        instance = CentripetalAcceleration()
        exp_result = 40.0
        result = instance.calculate(velocity, radius)
        self.assert_equals(exp_result, result, 0)

    def test_calculate8(self) -> None:
        """PASA: 10² / -5 = -20 (radio negativo da resultado negativo)"""
        print("calculate8")
        velocity = 10.0
        radius = -5.0
        instance = CentripetalAcceleration()
        exp_result = -20.0
        result = instance.calculate(velocity, radius)
        self.assert_equals(exp_result, result, 0)


if __name__ == "__main__":
    unittest.main()
