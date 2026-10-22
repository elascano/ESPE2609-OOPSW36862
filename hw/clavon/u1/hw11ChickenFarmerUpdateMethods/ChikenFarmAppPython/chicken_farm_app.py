from datetime import datetime
from chicken import Chicken
from chicken_coop import ChickenCoop

class ChickenFarmApp:

    @staticmethod
    def main():
        coop = ChickenCoop(101)
        option = 0

        print("=========================================")
        print("  David Clavón's Chicken Farm App")
        print("=========================================")

        while option != 7:
            ChickenFarmApp._show_menu(coop.chicken_coop_id)
            option = ChickenFarmApp._read_int("Select an option: ")

            if option == 1:
                ChickenFarmApp._add_chicken_to_coop(coop)
            elif option == 2:
                ChickenFarmApp._remove_chicken_from_coop(coop)
            elif option == 3:
                ChickenFarmApp._show_all_chickens(coop)
            elif option == 4:
                ChickenFarmApp._show_next_chicken(coop)
            elif option == 5:
                coop.reset_iteration()
                print("\nCoop iterator reset to the first position.")
            elif option == 6:
                print("\n--- CHICKEN COOP STATUS ---")
                print(coop)
            elif option == 7:
                print("\nExiting Chicken Farm App...")
            else:
                print("\nInvalid option. Please try again.")

    @staticmethod
    def _show_menu(coop_id: int) -> None:
        print(f"\n--- CHICKEN COOP MENU (ID: {coop_id}) ---")
        print("1. Add a chicken")
        print("2. Remove a chicken by ID")
        print("3. View all chickens")
        print("4. View next chicken")
        print("5. Reset iteration reader")
        print("6. Display full coop status")
        print("7. Exit")

    @staticmethod
    def _add_chicken_to_coop(coop: ChickenCoop) -> None:
        print("\n--- REGISTER NEW CHICKEN ---")
        chicken_id = ChickenFarmApp._read_int("Enter chicken ID: ")
        name = ChickenFarmApp._read_string("Enter Name: ")
        color = ChickenFarmApp._read_string("Enter Color: ")
        is_molting = ChickenFarmApp._read_boolean("Is molting? (yes/no or true/false): ")

        new_chicken = Chicken(chicken_id, name, color, datetime.now(), is_molting)
        if coop.add(new_chicken):
            print(f"Chicken {name} successfully added to the coop.")
        else:
            print("Could not add chicken. The coop is full or the object is invalid.")

    @staticmethod
    def _remove_chicken_from_coop(coop: ChickenCoop) -> None:
        print("\n--- REMOVE CHICKEN ---")
        remove_id = ChickenFarmApp._read_int("Enter chicken ID to remove: ")
        if coop.remove(remove_id):
            print(f"Chicken with ID {remove_id} was successfully removed.")
        else:
            print(f"No chicken found with ID {remove_id}.")

    @staticmethod
    def _show_all_chickens(coop: ChickenCoop) -> None:
        print("\n--- DISPLAYING ALL REGISTERED CHICKENS ---")
        coop.reset_iteration()
        current = coop.next()
        if current is None:
            print("The coop is empty.")
        else:
            while current is not None:
                print(f"-> {current}")
                current = coop.next()

    @staticmethod
    def _show_next_chicken(coop: ChickenCoop) -> None:
        print("\n--- NAVIGATE TO NEXT CHICKEN ---")
        next_chicken = coop.next()
        if next_chicken is not None:
            position = coop.get_current_position()
            total = coop.count
            print(f"Viewing Chicken {position} of {total}:")
            print(f"-> {next_chicken}")
        else:
            print("Reached the end of the list (or you need to reset the iteration).")

    @staticmethod
    def _read_int(prompt: str) -> int:
        while True:
            try:
                user_input = input(prompt).strip()
                return int(user_input)
            except ValueError:
                print("Error: Please enter a valid integer number.")

    @staticmethod
    def _read_string(prompt: str) -> str:
        while True:
            user_input = input(prompt).strip()
            if user_input:
                return user_input
            print("Error: Field cannot be empty.")

    @staticmethod
    def _read_boolean(prompt: str) -> bool:
        while True:
            user_input = input(prompt).strip().lower()
            if user_input in ("true", "y", "yes"):
                return True
            elif user_input in ("false", "n", "no"):
                return False
            else:
                print("Error: Please enter a valid response ('yes'/'no' or 'true'/'false').")