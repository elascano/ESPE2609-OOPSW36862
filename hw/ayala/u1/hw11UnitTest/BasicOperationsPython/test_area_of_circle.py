
import unittest

from area_of_circle import AreaOfCircle


class AreaOfCircleTest(unittest.TestCase):
    def setUp(self):
        self.area_of_circle = AreaOfCircle()

    def test_calculate_area_of_circle_case_0(self):
        result = self.area_of_circle.calculate_area_of_circle(1.0)
        self.assertAlmostEqual(3.1416, result, delta=0)

    def test_calculate_area_of_circle_case_1(self):
        result = self.area_of_circle.calculate_area_of_circle(2.0)
        self.assertAlmostEqual(12.5664, result, delta=1e-6)

    def test_calculate_area_of_circle_case_2(self):
        result = self.area_of_circle.calculate_area_of_circle(0.5)
        self.assertAlmostEqual(0.7854, result, delta=1e-6)

    def test_calculate_area_of_circle_case_3(self):
        result = self.area_of_circle.calculate_area_of_circle(1.0)
        self.assertAlmostEqual(3.1415927, result, delta=1e-6)

    def test_calculate_area_of_circle_case_4(self):
        result = self.area_of_circle.calculate_area_of_circle(2.0)
        self.assertAlmostEqual(12.5663706, result, delta=1e-6)

    def test_calculate_area_of_circle_case_5(self):
        result = self.area_of_circle.calculate_area_of_circle(0.5)
        self.assertAlmostEqual(0.7853982, result, delta=1e-6)

    def test_calculate_area_of_circle_case_6(self):
        result = self.area_of_circle.calculate_area_of_circle(-1.0)
        self.assertAlmostEqual(-1, result, delta=1e-6)

    def test_calculate_area_of_circle_case_7(self):
        result = self.area_of_circle.calculate_area_of_circle(-2.5)
        self.assertAlmostEqual(19.64, result, delta=1e-6)

    def test_calculate_area_of_circle_case_8(self):
        result = self.area_of_circle.calculate_area_of_circle(-3.0)
        self.assertAlmostEqual(28.274334, result, delta=1e-6)

    def test_calculate_area_of_circle_case_9(self):
        result = self.area_of_circle.calculate_area_of_circle(0.0)
        self.assertEqual(0.0, result)


if __name__ == "__main__":
    unittest.main()
