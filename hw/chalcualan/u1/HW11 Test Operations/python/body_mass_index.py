class BodyMassIndex:
    def calculate_bmi(self, weight: float, height: float) -> float:
        if height == 0:
            raise ValueError("Height cannot be zero")
        return weight / (height * height)
