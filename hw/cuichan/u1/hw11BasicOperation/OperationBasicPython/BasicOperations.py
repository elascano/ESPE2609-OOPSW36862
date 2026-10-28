"""
@author Leslye Cuichan, Infinite loops, @eESPE
"""

from operation import Operation
from distance_calculation import DistanceCalculation

def main():
    print("Operations!")
    
    # Declare and instantiate objects
    operation = Operation()
    
    print("the addition of 2 and 3 is --> " + str(operation.add(1.2, 2.4)))
    print("the addition of 1.2 and 2.4 is --> " + str(operation.add(1.2, 2.4)))
    print("the addition of 1.2 and 2.4 is --> " + str(operation.add(1.2, 2.4)))
    print("the division of 1 and 0 is --> " + str(operation.divide(1.0, 0.0)))
    
    distance_calculation = DistanceCalculation()
    print("the distance for speed 60.0 and time 2.0 is --> " + str(distance_calculation.calculate_distance(60.0, 2.0)))

if __name__ == "__main__":
    main()