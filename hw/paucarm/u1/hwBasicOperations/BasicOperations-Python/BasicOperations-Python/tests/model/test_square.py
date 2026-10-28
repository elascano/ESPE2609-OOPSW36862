"""
@author Paucar Matias, ByteWorks, @ESPE
"""
import pytest

from basicoperations.model.square import Square


class TestSquare:

    def setup_method(self):
        self.instance = Square()

    def test_compute_area_good1(self):
        print("computeArea - Good 1")
        assert self.instance.compute_area(2.0) == 4.0

    def test_compute_area_good2(self):
        print("computeArea - Good 2")
        assert self.instance.compute_area(5.0) == 25.0

    def test_compute_area_good3(self):
        print("computeArea - Good 3")
        assert self.instance.compute_area(12.0) == 144.0

    def test_compute_area_strong1(self):
        print("computeArea - Strong 1")
        assert self.instance.compute_area(0.0) == 0.0

    def test_compute_area_strong2(self):
        print("computeArea - Strong 2")
        assert self.instance.compute_area(2.5) == pytest.approx(6.25, abs=0.0001)

    def test_compute_area_strong3(self):
        print("computeArea - Strong 3")
        assert self.instance.compute_area(0.5) == pytest.approx(0.25, abs=0.0001)

    def test_compute_area_strong4(self):
        print("computeArea - Strong 4")
        assert self.instance.compute_area(9.9) == pytest.approx(98.01, abs=0.01)

    def test_compute_area_bad1(self):
        print("computeArea - Bad 1")
        assert self.instance.compute_area(3.5) == 12.25

    def test_compute_area_bad2(self):
        print("computeArea - Bad 2")
        assert self.instance.compute_area(1.5) == 2.251

    def test_compute_area_bad3(self):
        print("computeArea - Bad 3")
        assert self.instance.compute_area(8.0) == 64.0
