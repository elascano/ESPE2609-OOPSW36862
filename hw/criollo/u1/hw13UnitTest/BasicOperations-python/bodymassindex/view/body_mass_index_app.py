"""Interfaz de consola. Port de BodyMassIndexApp.java."""

from __future__ import annotations

from bodymassindex.model.body_mass_index import BodyMassIndex


def read_float(prompt: str) -> float:
    """Pide un número por consola y repite hasta recibir uno válido."""
    while True:
        raw = input(prompt).strip().replace(",", ".")  # admite coma decimal
        try:
            return float(raw)
        except ValueError:
            print("Error --> Please enter a valid number")


def main() -> None:
    print("Body Mass Index - BMI")

    body_mass_index = BodyMassIndex()
    weight_kg = read_float("Enter the weight in kg: ")
    height_m = read_float("Enter the height in m: ")

    try:
        bmi = body_mass_index.calculate_bmi(weight_kg, height_m)
        print(f"Your BMI is --> {bmi}")
        print(f"Category --> {body_mass_index.classify(bmi).value}")
    except ValueError as error:
        print(f"Error --> {error}")


if __name__ == "__main__":
    main()
