from ec.edu.espe.baiscoperation.model.operation import Operation
from utils.java_float import format_float


def main() -> None:
    print("operation")
    operation = Operation()
    print("the addition of 2 andd 3 is -->" + format_float(operation.add(1.2, 2.4)))
    print("the addition of 2 andd 3 is -->" + format_float(operation.add(1.2, 2.4)))
    print("the addition of 2 andd 3 is -->" + format_float(operation.add(1.2, 2.4)))

    print("divide -->" + format_float(operation.divide(1, 0)))


if __name__ == "__main__":
    main()
