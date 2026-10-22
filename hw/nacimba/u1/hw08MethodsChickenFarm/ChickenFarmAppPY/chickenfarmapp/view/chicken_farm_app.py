"""Chicken Farm App (main program).
Author: Nacimba Mateo, Group5, @ESPE
"""
from datetime import date, datetime

from chickenfarmapp.model.chicken import Chicken
from chickenfarmapp.model.chicken_coop import ChickenCoop
from chickenfarmapp.model.egg import Egg
from chickenfarmapp.model.poop import Poop



def read_int(message):
    while True:
        try:
            return int(input(message).strip())
        except ValueError:
            print("Invalid number. Please try again.")


def read_int_in_range(message, minimum, maximum):
    while True:
        value = read_int(message)
        if minimum <= value <= maximum:
            return value
        print(f"The number must be between {minimum} and {maximum}.")


def read_non_empty(message):
    while True:
        value = input(message).strip()
        if value:
            return value
        print("This field cannot be empty.")


def read_past_date(message):
    while True:
        text = input(message).strip()
        try:
            value = datetime.strptime(text, "%d/%m/%Y").date()
        except ValueError:
            print("Invalid date. Use the format dd/MM/yyyy (e.g. 15/04/2024).")
            continue
        if value > date.today():
            print("The birth date cannot be in the future.")
        else:
            return value


def read_yes_no(message):
    while True:
        value = input(message).strip().lower()
        if value == "y":
            return True
        if value == "n":
            return False
        print("Please answer 'y' or 'n'.")


def read_consistency(message):
    while True:
        value = input(message).strip().upper()
        if value in ("S", "M", "L"):
            return value
        print("Invalid value. Use S, M or L.")


def add_chickens(coop):
    if coop.is_full():
        print(f"The chicken coop is full! (Limit of {ChickenCoop.CAPACITY} chickens reached).")
        return

    remaining = ChickenCoop.CAPACITY - coop.get_chicken_count()
    quantity = read_int_in_range(
        f"How many chickens do you want to add? (1-{remaining}): ", 1, remaining)

    for i in range(1, quantity + 1):
        print(f"\n--- Register chicken {i} of {quantity} ---")

        chicken_id = coop.generate_id()
        print(f"Generated ID: {chicken_id}")

        name = read_non_empty("Enter chicken name: ")
        color = read_non_empty("Enter chicken color: ")
        born_on_date = read_past_date("Enter birth date (dd/MM/yyyy): ")
        molting = read_yes_no("Is the chicken molting? (y/n): ")

        print("-- Poop data --")
        poop_color = read_non_empty("Enter poop color: ")
        consistency = read_consistency(
            "Enter poop consistency (S = solid, M = medium, L = liquid): ")

        chicken_poop = Poop(chicken_id, poop_color, consistency)
        new_chicken = Chicken(chicken_id, name, color, born_on_date, molting, chicken_poop)
        coop.add(new_chicken)


def remove_chicken(coop):
    if coop.get_chicken_count() == 0:
        print("The coop is empty. There is nothing to remove.")
        return

    list_chickens(coop)
    chicken_id = read_int("Enter the ID of the chicken to remove: ")

    if not coop.exists_id(chicken_id):
        print(f"Chicken with ID {chicken_id} not found.")
        return

    if read_yes_no(f"Are you sure you want to remove chicken {chicken_id}? (y/n): "):
        coop.remove(chicken_id)
    else:
        print("Operation cancelled.")


def list_chickens(coop):
    print("\n--- List of Chickens in the Coop ---")
    coop.reset_iteration()
    current = coop.next()

    if current is None:
        print("The coop is empty.")
    else:
        while current is not None:
            print(current)
            current = coop.next()
    print(f"Total chickens in the coop: {coop.get_chicken_count()} / {ChickenCoop.CAPACITY}")


def main():
    print("Mateo Nacimba Chicken Farm App")
    print("===============================")

    chicken = Chicken(1, "Lucy", "brown and white", date(2024, 4, 15), True)
    egg = Egg(1, "M")
    poop = Poop(1, "brown", "S")
    chicken1 = Chicken(2, "Renata", "white", date(2007, 7, 13), True)

    print("chicken --> " + str(chicken))
    print("egg --> " + str(egg))
    print("poop --> " + str(poop))
    print("chicken2 --> " + str(chicken1))

    local_age = chicken.compute_age_in_years()
    print(f"The age of the chicken --> {chicken.name}, is --> {local_age}")

    chicken.do_stuff()

    print("\n===============================")
    print("CHICKEN COOP MANAGEMENT")
    print("===============================")

    coop = ChickenCoop(1)

    option = -1
    while option != 0:
        print("\n----------- MENU -----------")
        print("1. Add chickens")
        print("2. Remove a chicken")
        print("3. List chickens")
        print("0. Exit")
        option = read_int("Choose an option: ")

        if option == 1:
            add_chickens(coop)
        elif option == 2:
            remove_chicken(coop)
        elif option == 3:
            list_chickens(coop)
        elif option == 0:
            print("Goodbye!")
        else:
            print("Invalid option. Try again.")


if __name__ == "__main__":
    main()
