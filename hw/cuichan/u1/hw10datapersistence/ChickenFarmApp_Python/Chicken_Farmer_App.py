import json
import os
from datetime import date
from Chicken import Chicken
from Egg import Egg
from Poop import Poop
from ChickenCoop import ChickenCoop

FILE_NAME = "chickens.json"

def read_int(prompt):
    while True:
        try:
            return int(input(prompt).strip())
        except ValueError:
            print("Error: Enter a valid integer.")

def read_string(prompt):
    while True:
        user_input = input(prompt).strip()
        if user_input:
            return user_input
        print("Error: Value cannot be empty.")

def read_boolean(prompt):
    while True:
        user_input = input(prompt).strip().lower()
        if user_input in ["true", "y", "yes"]:
            return True
        elif user_input in ["false", "n", "no"]:
            return False
        print("Error: Answer 'yes' or 'no'.")

def save_chickens(coop):
    chicken_list = []
    coop.reset_iteration()
    current = coop.next()
    while current is not None:
        chicken_data = {
            "id": current.get_id(),
            "name": current.get_name(),
            "color": current.get_color(),
            "born_on_date": current.get_born_on_date().strftime("%Y-%m-%d") if isinstance(current.get_born_on_date(), date) else str(current.get_born_on_date()),
            "age_in_years": current.get_age_in_years(),
            "molting": current.is_molting()
        }
        chicken_list.append(chicken_data)
        current = coop.next()

    try:
        with open(FILE_NAME, "w", encoding="utf-8") as file:
            json.dump(chicken_list, file, indent=4)
    except Exception as e:
        print(f"Error saving data: {e}")

def load_chickens(coop):
    if not os.path.exists(FILE_NAME):
        return

    try:
        with open(FILE_NAME, "r", encoding="utf-8") as file:
            data = json.load(file)
            for item in data:
                birth_date = date.fromisoformat(item["born_on_date"])
                chicken = Chicken(
                    item["id"],
                    item["name"],
                    item["color"],
                    birth_date,
                    item["age_in_years"],
                    item["molting"]
                )
                coop.add(chicken)
    except Exception as e:
        print(f"Error loading data: {e}")

def show_menu():
    print("\n---MENU---")
    print("1. Register new chicken")
    print("2. Delete chicken by ID")
    print("3. Display chicken")
    print("4. Inspect next chicken")
    print("5. View coop status")
    print("6. Exit")

def add_chicken_to_coop(coop):
    print("\n--- REGISTER CHICKEN ---")
    chicken_id = read_int("Enter ID: ")
    name = read_string("Enter Name: ")
    color = read_string("Enter Color: ")
    is_molting = read_boolean("Is molting? (yes/no): ")

    new_chicken = Chicken(chicken_id, name, color, date.today(), 0, is_molting)
    new_chicken.set_age_in_years()

    if coop.add(new_chicken):
        save_chickens(coop)
        print("Chicken successfully registered.")
    else:
        print("Error: Coop is full.")

def remove_chicken_from_coop(coop):
    print("\n--- DELETE CHICKEN ---")
    remove_id = read_int("Enter ID to delete: ")
    if coop.remove(remove_id):
        save_chickens(coop)
        print("Delete request completed.")
    else:
        print(f"Error: Chicken with ID {remove_id} not found.")

def show_all_chickens(coop):
    print("\n--- REGISTERED CHICKENS ---")
    coop.reset_iteration()
    current = coop.next()
    if current is None:
        print("No chickens found.")
    else:
        while current is not None:
            print(f"-> {current}")
            current = coop.next()

def show_next_chicken(coop):
    print("\n--- NEXT ENTRY ---")
    next_chicken = coop.next()
    if next_chicken is not None:
        print(f"-> {next_chicken}")
    else:
        print("End of list reached.")

def main():
    print("Hello Chicken Farmers. this is Leslye Cuichan`s Farm")

    coop = ChickenCoop(1)
    load_chickens(coop)

    option = 0

    while option != 6:
        show_menu()
        option = read_int("Select an option: ")

        if option == 1:
            add_chicken_to_coop(coop)
        elif option == 2:
            remove_chicken_from_coop(coop)
        elif option == 3:
            show_all_chickens(coop)
        elif option == 4:
            show_next_chicken(coop)
        elif option == 5:
            print("\n--- CURRENT COOP DETAILS ---")
            print(coop)
        elif option == 6:
            save_chickens(coop)
            print("One more line for saying Good Bye")
            print("Good job!")
        else:
            print("\nInvalid option. Try again.")

if __name__ == "__main__":
    main()