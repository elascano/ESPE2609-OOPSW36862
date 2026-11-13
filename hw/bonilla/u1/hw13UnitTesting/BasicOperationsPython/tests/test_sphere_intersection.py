import unittest
from model.sphere_intersection import SphereIntersection

class TestSphereIntersection(unittest.TestCase):

    DELTA = 0.01

    def setUp(self):
        self.instance = SphereIntersection()


    def test_compute_volume_normal_case(self):
        self.assertAlmostEqual(12.5664, self.instance.compute_volume(3.0, 2.0, 3.0), delta=self.DELTA)

    
    def test_compute_volume_equal_spheres(self):
        self.assertAlmostEqual(35.3429, self.instance.compute_volume(3.0, 3.0, 3.0), delta=self.DELTA)

   
    def test_compute_volume_unit_values(self):
        self.assertAlmostEqual(1.3090, self.instance.compute_volume(1.0, 1.0, 1.0), delta=self.DELTA)

   
    def test_compute_volume_decimals(self):
        self.assertAlmostEqual(8.9012, self.instance.compute_volume(2.5, 1.5, 2.0), delta=self.DELTA)

    
    def test_compute_volume_large_values(self):
        self.assertAlmostEqual(1308.9969, self.instance.compute_volume(10.0, 10.0, 10.0), delta=self.DELTA)

   
    def test_compute_volume_small_values(self):
        self.assertAlmostEqual(0.1636, self.instance.compute_volume(0.5, 0.5, 0.5), delta=self.DELTA)

    
    def test_compute_volume_zero_radius(self):
        self.assertAlmostEqual(0.0, self.instance.compute_volume(0.0, 5.0, 2.0), delta=self.DELTA)

    
    def test_compute_volume_asymmetric_spheres(self):
        self.assertAlmostEqual(25.9181, self.instance.compute_volume(4.0, 2.0, 3.0), delta=self.DELTA)

    
    def test_compute_volume_is_commutative(self):
        self.assertAlmostEqual(
            self.instance.compute_volume(4.0, 2.0, 3.0),
            self.instance.compute_volume(2.0, 4.0, 3.0),
            delta=self.DELTA
        )


    def test_compute_volume_negative_value(self):
        with self.assertRaises(ValueError):
            self.instance.compute_volume(-3.0, 2.0, 3.0)

if __name__ == "__main__":
    unittest.main()