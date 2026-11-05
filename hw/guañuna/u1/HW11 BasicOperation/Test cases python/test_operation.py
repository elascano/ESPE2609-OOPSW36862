import math
import pytest
from operation import Operation


class TestOperation:

    def setup_method(self):
        """Equivalente a @BeforeEach"""
        pass

    def teardown_method(self):
        """Equivalente a @AfterEach"""
        pass

    @classmethod
    def setup_class(cls):
        """Equivalente a @BeforeAll"""
        pass

    @classmethod
    def teardown_class(cls):
        """Equivalente a @AfterAll"""
        pass

    # ---------- Tests de calculateSphereArea ----------

    def test_calculate_sphere_area_number(self):
        print("calculateSphereArea")
        radius = 2
        exp_result = 50.265
        result = Operation.calculate_sphere_area(radius)
        assert result == pytest.approx(exp_result, abs=0)

    def test_calculate_sphere_area_decimal(self):
        print("calculateSphereArea")
        radius = 4.5
        exp_result = 254.469
        result = Operation.calculate_sphere_area(radius)
        assert result == pytest.approx(exp_result, abs=0)

    def test_calculate_sphere_area_negative(self):
        print("calculateSphereArea")
        radius = -7
        exp_result = 0
        result = Operation.calculate_sphere_area(radius)
        assert result == pytest.approx(exp_result, abs=0)

    def test_calculate_sphere_area_long_number(self):
        print("calculateSphereArea")
        radius = 800
        exp_result = 8042477.193
        result = Operation.calculate_sphere_area(radius)
        assert result == pytest.approx(exp_result, abs=0)

    def test_calculate_sphere_area_long_decimal(self):
        print("calculateSphereArea")
        radius = 5.36982
        exp_result = 362.351
        result = Operation.calculate_sphere_area(radius)
        assert result == pytest.approx(exp_result, abs=0)

    def test_calculate_sphere_area_letter(self):
        print("calculateSphereArea")
        radius = ord('r')  # 114
        exp_result = 163312.553  # <--- Valor corregido
        result = Operation.calculate_sphere_area(radius)
        assert result == pytest.approx(exp_result, abs=0)

    def test_calculate_sphere_area_pi(self):
        print("calculateSphereArea")
        radius = ord('π')  # 960
        exp_result = 11581167.158  # <--- Valor corregido
        result = Operation.calculate_sphere_area(radius)
        assert result == pytest.approx(exp_result, abs=0)

    def test_calculate_sphere_area_number_powered(self):
        print("calculateSphereArea")
        radius = math.pow(1, 3)
        exp_result = 12.566
        result = Operation.calculate_sphere_area(radius)
        assert result == pytest.approx(exp_result, abs=0)

    def test_calculate_sphere_area_pi(self):
        print("calculateSphereArea")
        # En Java 'π' es un carácter Unicode (960). Aquí usamos su valor
        radius = ord('π')  # 960
        exp_result = 11582539.216
        result = Operation.calculate_sphere_area(radius)
        assert result == pytest.approx(exp_result, abs=0)

    def test_calculate_sphere_area_null(self):
        print("calculateSphereArea")
        radius = float('nan')  # equivalente a Double.NaN
        exp_result = 0
        result = Operation.calculate_sphere_area(radius)
        assert result == pytest.approx(exp_result, abs=0)