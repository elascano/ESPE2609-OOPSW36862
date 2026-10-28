"""
@author Leslye Cuichan, Infinite loops, @eESPE
"""

class distance_calculation:

    def calculate_distance(self, speed: float, time: float) -> float:
        if speed < 0 or time < 0:
            return 0.0
        
        distance = speed * time
        distance = round(distance, 2)
        return distance