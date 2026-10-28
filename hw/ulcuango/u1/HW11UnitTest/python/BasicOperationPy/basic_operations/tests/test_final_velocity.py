import pytest
from model.final_velocity import FinalVelocity


def test_calculate_final_velocity():
  fv = FinalVelocity(10.0, 2.0, 3.0)
  exp_result = 16.0
  assert fv.calculate_final_velocity() == exp_result


def test_negative_time_exception():
  with pytest.raises(ValueError, match="Time cannot be negative"):
    FinalVelocity(10.0, 2.0, -1.0)


def test_set_negative_time_exception():
  fv = FinalVelocity(10.0, 2.0, 3.0)
  with pytest.raises(ValueError, match="Time cannot be negative"):
    fv.time = -5.0