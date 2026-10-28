from datetime import date
import random

from chickenfarmapp.model.chicken import Chicken
from chickenfarmapp.model.chicken_coop import ChickenCoop
from chickenfarmapp.model.egg import Egg
from chickenfarmapp.model.poop import Poop


def read_int(message: str) -> int:
    while True:
        try:
            return int(input(message).strip())
        except ValueError:
            print("Invalid input. Enter a whole number.")


def read_text(message: str) -> str:
    while True:
        value = input(message).strip()
        if value:
            return value
        print("This field cannot be empty.")


def read_boolean(message: str) -> bool:
    while True:
        value = input(message).strip().lower()
        if value == "true":
            return True
        if value == "false":
            return False
        print("Enter true or false.")


def read_birth_date() -> date:
    while True:
        year = read_int("Enter birth year: ")
        month = read_int("Enter birth month (1-12): ")
        day = read_int("Enter birth day: ")
        try:
            birth_date = date(year, month, day)
            if birth_date > date.today():
                print("The birth date cannot be in the future.")
                continue
            return birth_date
        except ValueError:
            print("Invalid date. Check the year, month and day.")


def generate_unique_id(chickens: list[Chicken | None]) -> int:
    while True:
        chicken_id = random.randint(1000, 9999)
        if not any(chicken is not None and chicken.get_id() == chicken_id
                   for chicken in chickens):
            return chicken_id


def add_chicken(coop: ChickenCoop) -> None:
    chicken_id = generate_unique_id(coop.get_chickens())
    print("\nAdding a new chicken")
    print(f"Generated chicken ID: {chicken_id}")
    name = read_text("Enter chicken name: ")
    color = read_text("Enter chicken color: ")
    birth_date = read_birth_date()
    molting = read_boolean("Is the chicken molting? (true/false): ")
    chicken = Chicken(chicken_id, name, color, birth_date, 0, molting)
    chicken.set_age_in_years()
    coop.add(chicken)
    print("Chicken added successfully.")
    print(chicken)


def remove_chicken(coop: ChickenCoop) -> None:
    chicken_id = read_int("Enter the ID of the chicken to remove: ")
    coop.remove(chicken_id)


def show_chickens(coop: ChickenCoop) -> None:
    print("\n===== CHICKENS IN THE COOP =====")
    coop.reset_iteration()
    found = False
    while True:
        chicken = coop.next()
        if chicken is None:
            break
        print(chicken)
        found = True
    if not found:
        print("There are no chickens in the coop.")


def main() -> None:
    print("Renata Olalla's Chicken Farm App")
    print("=================================")

    poop = Poop(1, "brown", "S")
    egg = Egg(1, "M")
    chicken = Chicken(1, "Lucy", "brown and white", date(2024, 9, 15), 0, True)
    chicken1 = Chicken(2, "Renata", "white", date(2024, 9, 15), 0, False)

    chicken.set_age_in_years()
    chicken1.set_age_in_years()

    print("chicken --> " + str(chicken))
    print("egg --> " + str(egg))
    print("egg size --> " + egg.get_size())
    print("poop --> " + str(poop))
    print("chicken1 --> " + str(chicken1))
    print(f"The age of {chicken.get_name()} is {chicken.compute_age_in_years()} years.")
    chicken.do_stuff()

    chickens: list[Chicken | None] = [None] * 10
    chickens[0] = Chicken(3, "Pepita", "black", date(2020, 3, 2), 0, True)
    chickens[1] = chicken
    print("\nInitial chickens array:")
    for i, item in enumerate(chickens):
        print(f"chickens[{i}] --> {item}")

    for i in range(len(chickens)):
        birth_year = 2010 + i
        chickens[i] = Chicken(i + 1, f"Chicken {i + 1}", "white",
                              date(birth_year, 4, 3), 0, True)
        chickens[i].set_age_in_years()

    print("\nGenerated chickens array:")
    for i, item in enumerate(chickens):
        print(f"chickens[{i}] --> {item}")
    print(f"Length of chickens array --> {len(chickens)}")

    coop_chickens: list[Chicken | None] = [None] * 10
    coop = ChickenCoop(1, coop_chickens)

    option = 0
    while option != 4:
        print("\n===== CHICKEN COOP MENU =====")
        print("1. Add a chicken")
        print("2. Remove a chicken")
        print("3. Show chickens")
        print("4. Exit")
        option = read_int("Choose an option: ")
        if option == 1:
            add_chicken(coop)
        elif option == 2:
            remove_chicken(coop)
        elif option == 3:
            show_chickens(coop)
        elif option == 4:
            print("Exiting Chicken Coop...")
        else:
            print("Invalid option. Choose a number from 1 to 4.")


if __name__ == "__main__":
    main()
