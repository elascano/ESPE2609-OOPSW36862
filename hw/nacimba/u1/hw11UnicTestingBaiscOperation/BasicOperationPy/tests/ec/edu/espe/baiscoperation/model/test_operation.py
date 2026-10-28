import unittest

from ec.edu.espe.baiscoperation.model.operation import Operation
from utils.java_float import format_float, to_float32


class OperationTest(unittest.TestCase):
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

    def test_add(self) -> None:
        """Test of add method, of class Operation."""
        print("add")
        addend1 = 1.2
        addend2 = 3.4
        instance = Operation()
        exp_result = 4.6
        result = instance.add(addend1, addend2)
        self.assert_equals(exp_result, result, 0)

    def test_substract(self) -> None:
        """Test of substract method, of class Operation."""
        print("substract")
        minuhend = 0.0
        subtrahend = 0.0
        instance = Operation()
        exp_result = 0.0
        result = instance.substract(minuhend, subtrahend)
        self.assert_equals(exp_result, result, 0)

    def test_multiply(self) -> None:
        """Test of multiply method, of class Operation."""
        print("multiply")
        multiplicand = 0.0
        multiplier = 0.0
        instance = Operation()
        exp_result = 0.0
        result = instance.multiply(multiplicand, multiplier)
        self.assert_equals(exp_result, result, 0)

    def test_divide(self) -> None:
        """Test of divide method, of class Operation."""
        print("divide")
        dividend = 0.0
        divisor = 0.0
        instance = Operation()
        exp_result = 0.0
        result = instance.divide(dividend, divisor)
        self.assert_equals(exp_result, result, 0)


if __name__ == "__main__":
    unittest.main()
