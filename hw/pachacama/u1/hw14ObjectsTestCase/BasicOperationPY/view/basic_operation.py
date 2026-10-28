"""
@author Andres Pachacama, Byte Work, @ESPE
"""
from model.operation import Operation


def main():
    print("Operations!")

    # declare and instantiate an object
    # declare and initialize parameters
    # call the method

    operation = Operation()
    print("the addition of 1.2 and 2.4 is ---> " + str(operation.add(1.2, 2.4)))
    print("the addition of 1.2 and 2.4 is ---> " + str(operation.add(1.2, 2.4)))
    print("the addition of 1.2 and 2.4 is ---> " + str(operation.add(1.2, 2.4)))

    print("divide ---> " + str(operation.divide(1, 0)))

    # enter data from keyboard
    # call the method
    # observe the results
