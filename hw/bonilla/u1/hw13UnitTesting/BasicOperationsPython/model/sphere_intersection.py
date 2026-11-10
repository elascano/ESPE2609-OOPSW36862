import math

class SphereIntersection:
    """
    @author Angel Bonilla, @ESPE
    """
    def compute_volume(self, radius1: float, radius2: float, distance: float) -> float:
        if radius1 < 0 or radius2 < 0 or distance < 0:
            raise ValueError("Radii and distance cannot be negative")

        if radius1 == 0 or radius2 == 0 or distance >= radius1 + radius2:
            return 0.0

        if distance <= abs(radius1 - radius2):
            min_radius = min(radius1, radius2)
            return (4.0 / 3.0) * math.pi * (min_radius ** 3)

        part1 = (radius1 + radius2 - distance) ** 2
        part2 = (
            (distance ** 2)
            + (2 * distance * radius1)
            - (3 * (radius1 ** 2))
            + (2 * distance * radius2)
            + (6 * radius1 * radius2)
            - (3 * (radius2 ** 2))
        )

        return (math.pi * part1 * part2) / (12.0 * distance)