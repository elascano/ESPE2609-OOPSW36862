"""
@author Leslye Cuichan, Infinite loops, @eESPE
"""

import unittest
import distance_calculation

class DistanceCalculationTest(unittest.TestCase):

    def test_calculate_distance_zero_values(self):
        print("calculate with zero values")
        speed = 0.0
        time = 0.0
        instance = distance_calculation.distance_calculation()
        exp_result = 0.0
        result = instance.calculate_distance(speed, time)
        self.assertAlmostEqual(exp_result, result, delta=0.001)

    def test_calculate_distance_values(self):
        print("calculate with zero values")
        speed = 4.0
        time = 0.0
        instance = distance_calculation.distance_calculation()
        exp_result = 0.0
        result = instance.calculate_distance(speed, time)
        self.assertAlmostEqual(exp_result, result, delta=0.001)

    def test_calculate_distance_negative_speed_fail(self):
        print("calculate with negative speed")
        speed = -60.0
        time = 2.0
        instance = distance_calculation.distance_calculation()
        exp_result = 0.0
        result = instance.calculate_distance(speed, time)
        self.assertAlmostEqual(exp_result, result, delta=0.001)

    def test_calculate_distance_negative_time_fail(self):
        print("calculate with negative time")
        speed = 80.0
        time = -1.5
        instance = distance_calculation.distance_calculation()
        exp_result = 0.0
        result = instance.calculate_distance(speed, time)
        self.assertAlmostEqual(exp_result, result, delta=0.001)

    def test_calculate_distance_both_negative_fail(self):
        print("calculate with both negative")
        speed = -50.0
        time = -2.0
        instance = distance_calculation.distance_calculation()
        exp_result = 0.0
        result = instance.calculate_distance(speed, time)
        self.assertAlmostEqual(exp_result, result, delta=0.001)

    def test_calculate_distance(self):
        print("calculate with precision decimals")
        speed = 100.0
        time = 3.0001
        instance = distance_calculation.distance_calculation()
        exp_result = 300.01
        result = instance.calculate_distance(speed, time)
        self.assertAlmostEqual(exp_result, result, delta=0.00001)

    def test_calculate_distance_decimal_precision(self):
        print("calculate with decimals")
        speed = 12.5
        time = 0.030
        instance = distance_calculation.distance_calculation()
        exp_result = 0.375
        result = instance.calculate_distance(speed, time)
        self.assertAlmostEqual(exp_result, result, delta=0.001)

    def test_calculate_distance_zero_delta_fail(self):
        print("calculate with zero delta")
        speed = 10.1
        time = 3.0
        instance = distance_calculation.distance_calculation()
        exp_result = 30.3
        result = instance.calculate_distance(speed, time)
        self.assertEqual(exp_result, result)

    def test_calculate_distance_zero_speed(self):
        print("calculate with zero speed")
        speed = 0.0
        time = 15.5
        instance = distance_calculation.distance_calculation()
        exp_result = 0.0
        result = instance.calculate_distance(speed, time)
        self.assertAlmostEqual(exp_result, result, delta=0.01)

    def test_calculate_distance_zero_time(self):
        print("calculate with zero time")
        speed = 95.0
        time = 0.0
        instance = distance_calculation.distance_calculation()
        exp_result = 0.0
        result = instance.calculate_distance(speed, time)
        self.assertEqual(exp_result, result)

    def test_calculate_distance_large_numbers_fail(self):
        print("calculate with large numbers")
        speed = 3.4
        time = 7.82e1
        instance = distance_calculation.distance_calculation()
        exp_result = 265.88
        result = instance.calculate_distance(speed, time)
        self.assertAlmostEqual(exp_result, result, delta=0.001)

if __name__ == "__main__":
    unittest.main()