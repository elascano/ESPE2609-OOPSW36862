"""
@author Paucar Matias, ByteWorks, @ESPE
"""
from basicoperations.model.operation import Operation


def main() -> None:
    print("Operations!")

    # declare and instantiate an object
    # declare and initialize parameters
    # call the method

    operation = Operation()
    print("The addition of 1.2 and 2.4 is -->" + str(operation.add(1.2, 2.4)))
    print("The addition of 1.2 and 2.4 is -->" + str(operation.add(1.2, 2.4)))
    print("The addition of 1.2 and 2.4 is -->" + str(operation.add(1.2, 2.4)))

    print("divide --> " + str(operation.divide(1, 0)))

    # enter data from keyboard
    # call the method
    # observe the result


if __name__ == "__main__":
    main()
