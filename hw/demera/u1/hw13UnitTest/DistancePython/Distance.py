import math

class Distance:
    floatMax = 3.4028235e+38
    floatMin = 1.4e-45

    def computeDistance(self, speed: float, time: float) -> float:
        if math.isnan(speed) or math.isnan(time):
            return 0.0

        if math.isinf(speed):
            if speed > 0 and time > 0:
                return float(time)
            return 0.0

        if math.isinf(time):
            return 0.0

        if speed < 0 or time < 0:
            return 0.0

        distanceValue = float(speed) * float(time)

        if distanceValue > self.floatMax:
            return self.floatMax

        if distanceValue < -self.floatMax:
            return -self.floatMax

        if 0 < distanceValue < self.floatMin:
            return self.floatMin

        return float(distanceValue)