import json
import random
from datetime import date
from pathlib import Path

from phyton.model.Chicken import Chicken
from model.ChickenCoop import ChickenCoop
from model.Egg import Egg
from model.Poop import Poop

FILE_NAME = Path(__file__).with_name("chickens.json")
CAPACITY = 10


def read_int(message: str) -> int:
    while True:
        try:
            return int(input(message).strip())
        except ValueError:
            print("Enter a whole number.")


def read_text(message: str) -> str:
    while True:
        value = input(message).strip()
        if value:
            return value
        print("This field cannot be empty.")


def read_boolean(message: str) -> bool:
    while True:
        value = input(message).strip().lower()
        if value in ("yes", "true", "y", "si", "sí"):
            return True
        if value in ("no", "false", "n"):
            return False
        print("Enter yes or no.")


def read_birth_date() -> date:
    while True:
        try:
            year = read_int("Enter birth year: ")
            month = read_int("Enter birth month (1-12): ")
            day = read_int("Enter birth day (1-31): ")
            born = date(year, month, day)
            if born > date.today():
                print("Birth date cannot be in the future.")
                continue
            return born
        except ValueError:
            print("Invalid birth date. Try again.")


def generate_unique_id(chickens) -> int:
    existing_ids = {chicken.id for chicken in chickens}
    while True:
        chicken_id = random.randint(1000, 9999)
        if chicken_id not in existing_ids:
            return chicken_id


def load_coop_from_json():
    if not FILE_NAME.exists():
        print("No saved file. Starting empty.")
        return []
    try:
        with FILE_NAME.open("r", encoding="utf-8") as file:
            data = json.load(file)
        chickens = []
        seen_ids = set()
        for item in data:
            if len(chickens) >= CAPACITY:
                break
            chicken = Chicken.from_dict(item)
            if chicken.id not in seen_ids:
                chickens.append(chicken)
                seen_ids.add(chicken.id)
        print(f"Loaded {len(chickens)} chicken(s) from JSON.")
        return chickens
    except (OSError, json.JSONDecodeError, KeyError, TypeError, ValueError) as error:
        print(f"Error loading JSON: {error}")
        return []


def save_coop_to_json(chickens):
    try:
        data = [chicken.to_dict() for chicken in chickens]
        with FILE_NAME.open("w", encoding="utf-8") as file:
            json.dump(data, file, ensure_ascii=False, indent=2)
        print(f"Data saved to {FILE_NAME.name}")
    except OSError as error:
        print(f"Error saving JSON: {error}")


def add_chickens(coop: ChickenCoop):
    free_spaces = CAPACITY - len(coop.chickens)
    if free_spaces <= 0:
        print("The coop is full.")
        return

    print(f"Available spaces: {free_spaces}")
    while True:
        quantity = read_int("How many chickens do you want to add? ")
        if 1 <= quantity <= free_spaces:
            break
        print(f"Enter a quantity from 1 to {free_spaces}.")

    for index in range(quantity):
        print(f"\n--- Chicken {index + 1} ---")
        chicken_id = generate_unique_id(coop.chickens)
        print(f"Generated chicken ID: {chicken_id}")
        name = read_text("Enter chicken name: ")
        color = read_text("Enter chicken color: ")
        born = read_birth_date()
        molting = read_boolean("Is the chicken molting? (yes/no): ")
        chicken = Chicken(chicken_id, name, color, born, 0, molting)
        coop.add(chicken)
        print(f"Chicken added: {chicken}")
    save_coop_to_json(coop.chickens)


def remove_chicken(coop: ChickenCoop):
    chicken_id = read_int("Enter the ID of the chicken to remove: ")
    if coop.remove(chicken_id):
        save_coop_to_json(coop.chickens)


def show_chickens(chickens):
    print("\n===== CHICKENS IN THE COOP =====")
    for chicken in chickens:
        chicken.set_age_in_years()
        print(chicken)
    print(f"Total chickens: {len(chickens)}")


def main():
    print("Renata Olalla's Chicken Farm App")
    print("=================================")
    egg = Egg(1, "M")
    poop = Poop(1, "brown", "S")
    print(f"egg --> {egg}")
    print(f"poop --> {poop}")

    chickens = load_coop_from_json()
    coop = ChickenCoop(1, chickens, CAPACITY)

    while True:
        print("\n===== CHICKEN COOP MENU =====")
        print("1. Add a chicken")
        print("2. Remove a chicken")
        print("3. Show chickens")
        print("4. Exit")
        option = read_int("Choose an option: ")

        if option == 1:
            add_chickens(coop)
        elif option == 2:
            remove_chicken(coop)
        elif option == 3:
            show_chickens(coop.chickens)
        elif option == 4:
            save_coop_to_json(coop.chickens)
            print("Exiting and saving data...")
            break
        else:
            print("Invalid option.")


if __name__ == "__main__":
    main()