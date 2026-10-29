import unittest
from rectangle_perimeter import RectanglePerimeter


class RectanglePerimeterTest(unittest.TestCase):
    def testCalculatePerimeter1(self):
        base = 4.0
        height = 3.0
        instance = RectanglePerimeter()
        expResult = 14.0
        result = instance.calculatePerimeter(base, height)
        self.assertEqual(expResult, result)

    def testCalculatePerimeter2(self):
        base = 5.0
        height = 2.0
        instance = RectanglePerimeter()
        expResult = 14.0
        result = instance.calculatePerimeter(base, height)
        self.assertEqual(expResult, result)

    def testCalculatePerimeter3(self):
        base = 6.0
        height = 4.0
        instance = RectanglePerimeter()
        expResult = 20.0
        result = instance.calculatePerimeter(base, height)
        self.assertEqual(expResult, result)

    def testCalculatePerimeter4(self):
        base = 8.0
        height = 3.0
        instance = RectanglePerimeter()
        expResult = 22.0
        result = instance.calculatePerimeter(base, height)
        self.assertEqual(expResult, result)

    def testCalculatePerimeter5(self):
        base = 1.0
        height = 1.0
        instance = RectanglePerimeter()
        expResult = 4.0
        result = instance.calculatePerimeter(base, height)
        self.assertEqual(expResult, result)

    def testCalculatePerimeter6(self):
        base = 0.5
        height = 0.5
        instance = RectanglePerimeter()
        expResult = 2.0
        result = instance.calculatePerimeter(base, height)
        self.assertEqual(expResult, result)

    def testCalculatePerimeter7(self):
        base = 1000.0
        height = 2000.0
        instance = RectanglePerimeter()
        expResult = 6000.0
        result = instance.calculatePerimeter(base, height)
        self.assertEqual(expResult, result)

    def testCalculatePerimeter8(self):
        base = 1.0
        height = 100.0
        instance = RectanglePerimeter()
        expResult = 202.0
        result = instance.calculatePerimeter(base, height)
        self.assertEqual(expResult, result)

    def testCalculatePerimeter9(self):
        base = 7.0
        height = 3.0
        instance = RectanglePerimeter()
        expResult = 20.0
        result = instance.calculatePerimeter(base, height)
        self.assertEqual(expResult, result)

    def testCalculatePerimeter10(self):
        base = 9.0
        height = 2.0
        instance = RectanglePerimeter()
        expResult = 22.0
        result = instance.calculatePerimeter(base, height)
        self.assertEqual(expResult, result)


if __name__ == "__main__":
    unittest.main(verbosity=2)
