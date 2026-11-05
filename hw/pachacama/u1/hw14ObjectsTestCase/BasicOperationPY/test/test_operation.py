"""
Para correr las pruebas:  python -m unittest -v
@author Andres Pachacama, Byte Work, @ESPE
"""
import unittest

from model.operation import Operation


class OperationTest(unittest.TestCase):

    @classmethod
    def setUpClass(cls):        # @BeforeAll
        pass

    @classmethod
    def tearDownClass(cls):     # @AfterAll
        pass

    def setUp(self):            # @BeforeEach
        pass

    def tearDown(self):         # @AfterEach
        pass

    def test_add(self):
        """Test of add method, of class Operation."""
        print("add")
        addend1 = 1.2
        addend2 = 2.4
        instance = Operation()
        exp_result = 4.6
        result = instance.add(addend1, addend2)
        self.assertAlmostEqual(exp_result, result, delta=0)

    def test_subtract(self):
        """Test of subtract method, of class Operation."""
        print("subtract")
        minuend = 2.0
        subtrahend = 4.0
        instance = Operation()
        exp_result = -2.0
        result = instance.subtract(minuend, subtrahend)
        self.assertAlmostEqual(exp_result, result, delta=0)

    def test_multiply(self):
        """Test of multiply method, of class Operation."""
        print("multiply")
        multiplicand = 3.0
        multiplier = 2.0
        instance = Operation()
        exp_result = 6.0
        result = instance.multiply(multiplicand, multiplier)
        self.assertAlmostEqual(exp_result, result, delta=0)

    def test_divide(self):
        """Test of divide method, of class Operation."""
        print("divide")
        dividend = 6.0
        divisor = 2.0
        instance = Operation()
        exp_result = 3.0
        result = instance.divide(dividend, divisor)
        self.assertAlmostEqual(exp_result, result, delta=0)


if __name__ == "__main__":
    unittest.main()
