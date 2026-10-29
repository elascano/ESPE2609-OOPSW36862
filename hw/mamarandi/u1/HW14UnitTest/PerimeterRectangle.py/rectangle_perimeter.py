class RectanglePerimeter:
    def calculatePerimeter(self, base: float, height: float) -> float:
        if base <= 0 or height <= 0:
            raise ValueError("Invalid dimensions")
        perimeter = 2 * (base + height)
        return perimeter
