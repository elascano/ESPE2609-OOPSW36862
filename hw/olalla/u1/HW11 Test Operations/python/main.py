from operation import Operation


def main() -> None:
    print("Operations")
    operation = Operation()
    print("the addition of 2 and 3 is -->", operation.add(2, 3))
    print("the addition of 1.2 and 2.4 is -->", operation.add(1.2, 2.4))

    for dividend, divisor in [(0, 0), (1, 0)]:
        try:
            print(f"divide --> {operation.divide(dividend, divisor)}")
        except ZeroDivisionError as error:
            print(f"divide --> Error: {error}")


if __name__ == "__main__":
    main()
