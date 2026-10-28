import unittest

class TemperatureConverter:
    def celsius_to_fahrenheit(self, celsius):
        return (celsius * 9.0 / 5.0) + 32.0

    def fahrenheit_to_celsius(self, fahrenheit):
        return (fahrenheit - 32.0) * 5.0 / 9.0


class TestTemperatureConverter(unittest.TestCase):

    def test_celsius_to_fahrenheit_freezing(self):
        instance = TemperatureConverter()
        self.assertAlmostEqual(32.0, instance.celsius_to_fahrenheit(0.0), delta=0.0001)

    def test_celsius_to_fahrenheit_boiling(self):
        instance = TemperatureConverter()
        self.assertAlmostEqual(212.0, instance.celsius_to_fahrenheit(100.0), delta=0.0001)

    def test_celsius_to_fahrenheit_cross_point(self):
        instance = TemperatureConverter()
        self.assertAlmostEqual(-40.0, instance.celsius_to_fahrenheit(-40.0), delta=0.0001)

    def test_fahrenheit_to_celsius_freezing(self):
        instance = TemperatureConverter()
        self.assertAlmostEqual(0.0, instance.fahrenheit_to_celsius(32.0), delta=0.0001)

    def test_fahrenheit_to_celsius_boiling(self):
        instance = TemperatureConverter()
        self.assertAlmostEqual(100.0, instance.fahrenheit_to_celsius(212.0), delta=0.0001)

    def test_fahrenheit_to_celsius_body_temp(self):
        instance = TemperatureConverter()
        self.assertAlmostEqual(37.0, instance.fahrenheit_to_celsius(98.6), delta=0.0001)

    def test_fahrenheit_to_celsius_cross_point(self):
        instance = TemperatureConverter()
        self.assertAlmostEqual(-40.0, instance.fahrenheit_to_celsius(-40.0), delta=0.0001)

    def test_celsius_to_fahrenheit_intentional_fail(self):
        instance = TemperatureConverter()
        self.assertAlmostEqual(60.0, instance.celsius_to_fahrenheit(10.0), delta=0.0001)

    def test_fahrenheit_to_celsius_intentional_fail(self):
        instance = TemperatureConverter()
        self.assertAlmostEqual(0.0, instance.fahrenheit_to_celsius(50.0), delta=0.0001)

    def test_celsius_to_fahrenheit_precision_fail(self):
        instance = TemperatureConverter()
        self.assertAlmostEqual(98.0, instance.celsius_to_fahrenheit(37.0), delta=0.0001)

if __name__ == '__main__':
    unittest.main()