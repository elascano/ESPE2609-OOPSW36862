"""Versión Python de ChickenFarmApp.java. Ejecutar: python main.py"""
import json
from datetime import date
from pathlib import Path

from model.chicken import Chicken
from model.chicken_coop import ChickenCoop
from model.egg import Egg
from model.poop import Poop

FILE_NAME = Path(__file__).resolve().parent / "chickens.json"


def load_coop_from_json(coop: ChickenCoop) -> None:
    if not FILE_NAME.exists():
        return
    try:
        data = json.loads(FILE_NAME.read_text(encoding="utf-8"))
        if not isinstance(data, list):
            raise ValueError("JSON must be an array")
        for item in data:
            coop.add(Chicken.from_dict(item))
        print(f"--> Data loaded successfully from {FILE_NAME.name}")
    except (OSError, ValueError, TypeError, KeyError) as error:
        print(f"Error loading data from JSON: {error}")


def save_coop_to_json(coop: ChickenCoop) -> None:
    data = [chicken.to_dict() for chicken in coop.get_chickens() if chicken is not None]
    try:
        FILE_NAME.write_text(json.dumps(data, indent=2, ensure_ascii=False) + "\n", encoding="utf-8")
    except OSError as error:
        print(f"Error saving data to JSON: {error}")


def print_menu() -> None:
    print("---------- MENU ----------")
    print("1. Add chicken")
    print("2. Remove chicken")
    print("3. Next chicken")
    print("4. Reset iteration")
    print("5. Show coop")
    print("6. Run demo")
    print("0. Exit")


def read_int(message: str, minimum: int, maximum: int) -> int:
    while True:
        try:
            value = int(input(message).strip())
        except ValueError:
            print("Invalid number. Try again.")
            continue
        if minimum <= value <= maximum:
            return value
        print(f"Enter a number between {minimum} and {maximum}.")


def read_boolean(message: str) -> bool:
    while True:
        value = input(message).strip().lower()
        if value in {"true", "yes", "y", "s", "si"}:
            return True
        if value in {"false", "no", "n"}:
            return False
        print("Invalid input. Please enter 'yes'/'no' or 'true'/'false'.")


def add_chicken(coop: ChickenCoop) -> None:
    chicken_id = read_int("Id: ", 0, 2147483647)
    name = input("Name: ").strip()
    color = input("Color: ").strip()

    while True:
        print("Birth date:")
        day = read_int("  Day (1-31): ", 1, 31)
        month = read_int("  Month (1-12): ", 1, 12)
        year = read_int("  Year (e.g. 2022): ", 1900, 2100)
        try:
            born_on_date = date(year, month, day)
            break
        except ValueError:
            print("Invalid calendar date. Try again.")

    molting = read_boolean("Is molting? (yes/no): ")
    chicken = Chicken(chicken_id, name, color, born_on_date)
    chicken.set_molting(molting)
    chicken.set_age_in_years()
    if coop.add(chicken):
        save_coop_to_json(coop)


def remove_chicken(coop: ChickenCoop) -> None:
    chicken_id = read_int("Id of the chicken to remove: ", 0, 2147483647)
    if coop.remove(chicken_id):
        save_coop_to_json(coop)


def run_demo() -> None:
    chicken = Chicken(1, "Lucy", "brown and withe", date(1970, 12, 17))
    egg = Egg(1, "M")
    poop = Poop(1, "brown", "S")
    chicken1 = Chicken(2, "Renata", "white", date(1970, 11, 17))

    print(f"chicken -->{chicken}")
    print(f"egg -->{egg}")
    print(f"poop -->{poop}")
    print(f"chicken 2-->{chicken1}")
    print(f"The age of the chicken --> {chicken.get_name()} is {chicken.compute_age_in_years()}")
    chicken.do_stuff()
    chicken1.do_stuff()

    chickens: list[Chicken | None] = [None] * 10
    chickens[0] = Chicken(3, "Pepita", "black", date(2020, 2, 2))
    chickens[1] = chicken
    chickens[2] = chicken1
    for item in chickens:
        print(f"chickens -->{str(item) if item is not None else 'null'}")

    for i in range(10):
        chickens[i] = Chicken(i, f"chicken {i + 1}", "white ", date(2010 + i, 1, 1))
    for item in chickens:
        print(f"chickens --> {item}")


def main() -> None:
    print("Isaac Acurio Chicken Farm App")
    print("===============================")
    coop = ChickenCoop(1)
    load_coop_from_json(coop)

    while True:
        print_menu()
        option = read_int("Choose an option: ", 0, 6)
        print()
        if option == 1:
            add_chicken(coop)
        elif option == 2:
            remove_chicken(coop)
        elif option == 3:
            coop.next()
        elif option == 4:
            coop.reset_iteration()
        elif option == 5:
            print(coop)
        elif option == 6:
            run_demo()
        elif option == 0:
            save_coop_to_json(coop)
            print("Exiting and saving data...")
            break
        print()


if __name__ == "__main__":
    try:
        main()
    except (KeyboardInterrupt, EOFError):
        print("\nProgram interrupted.")
