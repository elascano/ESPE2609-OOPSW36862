from operation import Operation


def main():
    print("Operations!")

    operation = Operation()

    print("the addition of 1.2 and 2.4 is --> " + str(operation.add(1.2, 2.4)))
    print("the addition of 1.2 and 2.4 is --> " + str(operation.add(1.2, 2.4)))
    print("the addition of 1.2 and 2.4 is --> " + str(operation.add(1.2, 2.4)))

    try:
        print("divide --> " + str(operation.divide(1, 0)))
    except ZeroDivisionError as e:
        print("divide --> Error:", e)


if __name__ == "__main__":
    main()