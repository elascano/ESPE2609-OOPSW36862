from controller import AnimalFarmController
from view import AnimalFarmView


def main() -> None:
    AnimalFarmController(AnimalFarmView()).run()


if __name__ == "__main__":
    main()
