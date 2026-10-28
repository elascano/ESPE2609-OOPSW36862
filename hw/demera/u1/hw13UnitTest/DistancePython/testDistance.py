import unittest
import math
from Distance import Distance

class DistanceTest(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        pass

    @classmethod
    def tearDownClass(cls):
        pass

    def setUp(self):
        pass

    def tearDown(self):
        pass

    def testComputeDistanceComplexDecimal(self):
        print("computeDistance with complex decimals")
        speed = 123.56
        time = 21.37
        instance = Distance()
        expResult = 2640.4772
        result = instance.computeDistance(speed, time)
        self.assertAlmostEqual(expResult, result, delta=0.001)

    def testComputeDistanceSmallValues(self):
        print("computeDistance with small decimal values")
        speed = 0.000123
        time = 0.00456
        instance = Distance()
        expResult = 0.00000056088
        result = instance.computeDistance(speed, time)
        self.assertAlmostEqual(expResult, result, delta=0.000000001)

    def testComputeDistanceLargeValues(self):
        print("computeDistance with large values")
        speed = 98765.43
        time = 123.45
        instance = Distance()
        expResult = 12192592.0
        result = instance.computeDistance(speed, time)
        self.assertAlmostEqual(expResult, result, delta=1.0)

    def testComputeDistanceNegativeTime(self):
        print("computeDistance with negative time")
        speed = 123.56
        time = -21.0
        instance = Distance()
        expResult = 0.0
        result = instance.computeDistance(speed, time)
        self.assertEqual(expResult, result)

    def testComputeDistanceOverflow(self):
        print("computeDistance with float overflow")
        speed = Distance.floatMax
        time = 1000.0
        instance = Distance()
        expResult = Distance.floatMax
        result = instance.computeDistance(speed, time)
        self.assertEqual(expResult, result)

    def testComputeDistanceIndetermination(self):
        print("computeDistance with NaN input")
        speed = float('nan')
        time = 123.45
        instance = Distance()
        expResult = 0.0
        result = instance.computeDistance(speed, time)
        self.assertEqual(expResult, result)

    def testComputeDistanceInfiniteInput(self):
        print("computeDistance with infinite input")
        speed = float('inf')
        time = 999.99
        instance = Distance()
        expResult = 999.99
        result = instance.computeDistance(speed, time)
        self.assertEqual(expResult, result)

    def testComputeDistanceNegativeSpeed(self):
        print("computeDistance with negative speed")
        speed = -987.654
        time = 123.456
        instance = Distance()
        expResult = 0.0
        result = instance.computeDistance(speed, time)
        self.assertEqual(expResult, result)

    def testComputeDistanceNegativeOverflow(self):
        print("computeDistance with negative overflow")
        speed = -Distance.floatMax
        time = 2.0
        instance = Distance()
        expResult = 0.0
        result = instance.computeDistance(speed, time)
        self.assertEqual(expResult, result)

    def testComputeDistanceMinimumFloat(self):
        print("computeDistance with minimum positive float")
        speed = Distance.floatMin
        time = 0.5
        instance = Distance()
        expResult = Distance.floatMin
        result = instance.computeDistance(speed, time)
        self.assertEqual(expResult, result)

if __name__ == '__main__':
    unittest.main()