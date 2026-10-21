"""
@author Josselyn Ulcuango, Codelex, @ESPE
"""

import sys
import os

# Ajuste para permitir importaciones locales entre carpetas
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

from datetime import date
from model.chicken import Chicken
from model.egg import Egg
from model.poop import Poop

def main():
    chicken_list = []
    
    print("Josselyn Ulcuango's Chicken Farm App")
    print("=====================================")
    
    # --- INITIAL DEMO (Misma salida inicial que en Java) ---
    chicken = Chicken(1, "Lucy", "brown and white", date(2024, 9, 15), 0, True)
    egg = Egg(1, "M")
    poop = Poop(1, "brown", "S")
    chicken1 = Chicken(1, "Renata", "white", date(2024, 9, 15), 0, False)
    
    print(f"chicken --> {chicken}")
    print(f"egg --> {egg}")
    print(f"poop --> {poop}")
    print(f"chicken1 --> {chicken1}")
    
    chicken.do_stuff()
    
    # Cargar los 10 pollos iniciales
    for i in range(1, 11):
        birth_year = 2009 + i
        c = Chicken(i, f"chicken {i}", "white", date(birth_year, 4, 3), 0, True)
        c.set_age_in_years(c.compute_age_in_years())
        chicken_list.append(c)

    option = 0
    next_id = 11

    # --- CONSOLE INTERACTIVE MENU ---
    while option != 4:
        print("\n=====================================")
        print("         CHICKEN FARM MENU           ")
        print("=====================================")
        print("1. Add new chickens")
        print("2. View registered chickens and actions")
        print("3. Remove a chicken")
        print("4. Exit")
        
        try:
            option = int(input("Select an option: "))
        except ValueError:
            print("Please enter a valid number.")
            continue

        if option == 1:
            try:
                quantity = int(input("\nHow many chickens do you want to add?: "))
                for i in range(quantity):
                    print(f"\n--- Registering Chicken {i + 1} of {quantity} ---")
                    name = input("Enter the chicken's name: ")
                    color = input("Enter the chicken's color: ")
                    year = int(input("Enter birth year (e.g., 2024): "))
                    month = int(input("Enter birth month (1-12): "))
                    day = int(input("Enter birth day (1-31): "))
                    
                    born_date = date(year, month, day)
                    
                    molting_input = input("Is it molting? (true/false): ").strip().lower()
                    molting = molting_input in ["true", "t", "yes", "y", "1"]
                    
                    new_chicken = Chicken(next_id, name, color, born_date, 0, molting)
                    new_chicken.set_age_in_years(new_chicken.compute_age_in_years())
                    chicken_list.append(new_chicken)
                    
                    print(f"Chicken '{name}' successfully added with ID: {next_id}!")
                    next_id += 1
            except ValueError:
                print("Invalid input. Registration cancelled for this entry.")

        elif option == 2:
            print("\n--- CURRENT CHICKENS LIST AND ACTIONS ---")
            if not chicken_list:
                print("There are no chickens registered on the farm.")
            else:
                for index, c in enumerate(chicken_list):
                    c.set_age_in_years(c.compute_age_in_years())
                    print(f"\n[Index {index}] chickens --> {c}")
                    print(f"--> Action of {c.get_name()}:")
                    c.do_stuff()
                print(f"\nlength of chickens --> {len(chicken_list)}")

        elif option == 3:
            print("\n--- REMOVE A CHICKEN ---")
            if not chicken_list:
                print("There are no chickens available to remove.")
            else:
                print("Chickens on the farm:")
                for index, c in enumerate(chicken_list):
                    print(f"Index [{index}] -> Name: {c.get_name()} (ID: {c.get_id()})")
                
                try:
                    index_to_remove = int(input("Enter the index of the chicken you want to remove: "))
                    if 0 <= index_to_remove < len(chicken_list):
                        removed_chicken = chicken_list.pop(index_to_remove)
                        print(f"Chicken '{removed_chicken.get_name()}' has been successfully removed from the farm!")
                    else:
                        print("Index out of range. Please try again.")
                except ValueError:
                    print("Invalid input. Please enter a valid number.")

        elif option == 4:
            print("\nExiting Chicken Farm App. Goodbye, Josselyn!")

        else:
            print("Invalid option. Please choose between 1 and 4.")

if __name__ == "__main__":
    main()