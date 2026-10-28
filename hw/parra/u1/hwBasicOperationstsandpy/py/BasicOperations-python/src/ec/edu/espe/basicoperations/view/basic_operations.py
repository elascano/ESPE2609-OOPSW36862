"""Vista BasicOperations (punto de entrada).

@author David Clavón, Infinite Loops, @ESPE
"""
from ec.edu.espe.basicoperations.model.operation import Operation


def main() -> None:
    print("Operations")

    operation = Operation()

    print("The addition of 1.2 and 2.4 ---> ", operation.add(1.2, 2.4), sep="")
    print("The addition of 1.2 and 2.4 ---> ", operation.add(1.2, 2.4), sep="")
    print("The addition of 1.2 and 2.4 ---> ", operation.add(1.2, 2.4), sep="")

    print("divide ---> ", operation.divide(1, 0), sep="")


if __name__ == "__main__":
    main()
