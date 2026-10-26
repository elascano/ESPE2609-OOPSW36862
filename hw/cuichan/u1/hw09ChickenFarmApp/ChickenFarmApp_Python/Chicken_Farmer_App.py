from datetime import date
from Chicken import Chicken
from Egg import Egg
from Poop import Poop
from ChickenCoop import ChickenCoop

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
    id = read_int("Enter ID: ")
    name = read_string("Enter Name: ")
    color = read_string("Enter Color: ")
    is_molting = read_boolean("Is molting? (yes/no): ")

    new_chicken = Chicken(id, name, color, date.today(), 0, is_molting)
    new_chicken.set_age_in_years()

    coop.add(new_chicken)
    print("Chicken successfully registered.")

def remove_chicken_from_coop(coop):
    print("\n--- DELETE CHICKEN ---")
    remove_id = read_int("Enter ID to delete: ")
    coop.remove(remove_id)
    print("Delete request completed.")

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
    print("Leslye Cuichan's Chicken Farm App")
    print("================================")

    chicken = Chicken(1, "Lucy", "brown and white", date(2000, 9, 20), 0, True)
    chicken.set_age_in_years()
    egg = Egg(1, 'M')
    poop = Poop(1, "brown", 'S')
    chicken1 = Chicken(2, "Lucy", "white", date.today(), 0, False)

    print(f"chicken --> {chicken}")
    print(f"egg --> {egg}")
    print(f"poop --> {poop}")
    print(f"chicken 2 --> {chicken1}")

    local_age = chicken.compute_age_in_years()
    print(f"the age of the chicken --> {chicken.get_name()}, is --> {local_age}")
    print(f"the age of the chicken ---> {chicken.get_name()}, is ---> {chicken.get_age_in_years()}")

    chicken.do_stuff()

    poop2 = Poop(2, "green", 'N')
    print(f"poop--> {poop2}")

    egg2 = Egg(2, 'O')
    print(f"egg--> {egg2}")

    coop = ChickenCoop(1)

    for i in range(10):
        print(f"chickens --> {coop.next()}")

    chickens = [None] * 10

    for i in range(len(chickens)):
        age_for_the_loop = 2010 + i
        chickens[i] = Chicken(i, f"chicken {i + 1}", "white", date(age_for_the_loop, 3, 3), 0, True)
        chickens[i].set_age_in_years()
        coop.add(chickens[i])

    for i in range(10):
        print(f"chickens --> {chickens[i]}")

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
            print("\nClosing application...")
        else:
            print("\nInvalid option. Try again.")

if __name__ == "__main__":
    main()