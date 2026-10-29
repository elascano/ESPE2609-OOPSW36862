import math
import unittest
from circle import Circle


class CircleTest(unittest.TestCase):

  def setUp(self):
    self.circle = Circle()
    self.DELTA = 0.0001

  def test_calculate_area_radius_one(self):
    result = self.circle.calculate_area(1.0)
    self.assertAlmostEqual(math.pi, result, delta=self.DELTA)

  def test_calculate_area_radius_five(self):
    result = self.circle.calculate_area(5.0)
    self.assertAlmostEqual(78.5398, result, delta=self.DELTA)

  def test_calculate_area_radius_decimal(self):
    result = self.circle.calculate_area(2.5)
    self.assertAlmostEqual(19.6349, result, delta=self.DELTA)

  def test_calculate_area_radius_zero(self):
    result = self.circle.calculate_area(0.0)
    self.assertAlmostEqual(0.0, result, delta=self.DELTA)

  def test_calculate_area_radius_large(self):
    result = self.circle.calculate_area(100.0)
    self.assertAlmostEqual(31415.9265, result, delta=self.DELTA)

  def test_calculate_area_radius_negative(self):
    with self.assertRaises(ValueError):
      self.circle.calculate_area(-5.0)

  def test_calculate_perimeter_radius_three(self):
    result = self.circle.calculate_perimeter(3.0)
    self.assertAlmostEqual(18.8495, result, delta=self.DELTA)

  def test_calculate_perimeter_radius_small(self):
    result = self.circle.calculate_perimeter(0.5)
    self.assertAlmostEqual(3.1415, result, delta=self.DELTA)

  def test_calculate_perimeter_radius_ten(self):
    result = self.circle.calculate_perimeter(10.0)
    self.assertAlmostEqual(62.8318, result, delta=self.DELTA)

  def test_calculate_perimeter_radius_negative(self):
    with self.assertRaises(ValueError):
      self.circle.calculate_perimeter(-2.0)


if __name__ == "__main__":
  unittest.main()