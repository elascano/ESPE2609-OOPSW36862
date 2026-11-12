from model import FarmAnimal


class AnimalFarmView:
    def show_title(self) -> None:
        print("Enrique's Anima Farm")
        print("========================")

    def show_animal(self, label: str, animal: FarmAnimal) -> None:
        print(f"{label} --->{animal}")

    def show_animal_type(self, label: str, animal: FarmAnimal) -> None:
        print(f"{label} --> is a {type(animal).__name__}")

    def show_animal_class(self, label: str, animal: FarmAnimal) -> None:
        print(f"{label} --> class {type(animal).__name__}")

    def show_message(self, message: str) -> None:
        print(message)
