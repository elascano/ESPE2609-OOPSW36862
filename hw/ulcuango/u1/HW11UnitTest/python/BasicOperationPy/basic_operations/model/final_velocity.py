class FinalVelocity:

  def __init__(self, initial_velocity: float, acceleration: float, time: float):
    if time < 0:
      raise ValueError("Time cannot be negative")
    self._initial_velocity = initial_velocity
    self._acceleration = acceleration
    self._time = time

  def calculate_final_velocity(self) -> float:
    return self._initial_velocity + (self._acceleration * self._time)

  @property
  def initial_velocity(self) -> float:
    return self._initial_velocity

  @initial_velocity.setter
  def initial_velocity(self, value: float):
    self._initial_velocity = value

  @property
  def acceleration(self) -> float:
    return self._acceleration

  @acceleration.setter
  def acceleration(self, value: float):
    self._acceleration = value

  @property
  def time(self) -> float:
    return self._time

  @time.setter
  def time(self, value: float):
    if value < 0:
      raise ValueError("Time cannot be negative")
    self._time = value