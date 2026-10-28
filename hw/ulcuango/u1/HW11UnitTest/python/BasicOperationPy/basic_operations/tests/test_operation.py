import pytest
from model.operation import Operation


def test_add():
  print("add")
  addend1 = 1.2
  addend2 = 3.4
  instance = Operation()
  exp_result = 4.6
  result = instance.add(addend1, addend2)
  assert result == pytest.approx(exp_result, abs=0.01)


def test_subtract():
  print("subtract")
  minuhend = 2.0
  subtrahend = 4.0
  instance = Operation()
  exp_result = -2.0
  result = instance.subtract(minuhend, subtrahend)
  assert result == exp_result


def test_multiply():
  print("multiply")
  multiplicand = 3.0
  multiplier = 2.0
  instance = Operation()
  exp_result = 6.0
  result = instance.multiply(multiplicand, multiplier)
  assert result == exp_result


def test_divide():
  print("divide")
  dividend = 6.0
  divisor = 2.0
  instance = Operation()
  exp_result = 3.0
  result = instance.divide(dividend, divisor)
  assert result == exp_result