from model.operation import Operation


def main():
  print("Operations!")

  operation = Operation()
  print(
      "the adition of 1.2 and 2.4 is -->"
      f" {operation.add(1.2, 2.4)}"
  )


if __name__ == "__main__":
  main()