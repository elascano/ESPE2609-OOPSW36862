from utils.java_float import divide, to_float32


class CentripetalAcceleration:
    def calculate(self, velocity: float, radius: float) -> float:
        v = to_float32(velocity)
        acceleration = divide(to_float32(v * v), radius)
        return acceleration
