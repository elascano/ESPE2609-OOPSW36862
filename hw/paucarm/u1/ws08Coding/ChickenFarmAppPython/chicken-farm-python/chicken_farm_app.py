"""
@author Paucar Matias, ByteWorks, @ESPE
"""
from datetime import date

from model.chicken import Chicken
from model.chicken_coop import ChickenCoop

# counters to generate the ids automatically
next_chicken_id = 1
next_coop_id = 1


def main():
    global next_coop_id

    print("Matias Paucar's Chicken Farm App")
    print("#################################")

    coop_id = next_coop_id
    next_coop_id += 1
    coop = ChickenCoop(coop_id)
    print("Chicken coop id: " + str(coop_id))

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
            remove_chickens(coop)
        elif option == 3:
            show_chickens(coop)
        elif option == 0:
            print("Goodbye!")
        else:
            print("Invalid option, try again")


# option 1: the customer says how many chickens to add
def add_chickens(coop: ChickenCoop):
    free_spaces = ChickenCoop.MAX_CHICKENS - coop.get_number_of_chickens()

    if free_spaces == 0:
        print("The coop is full, we can not add any more chickens")
        return

    while True:
        quantity_to_add = read_int("How many chickens do you want to add? (1-" + str(free_spaces) + "): ")
        if 1 <= quantity_to_add <= free_spaces:
            break
        print("Invalid quantity, it must be between 1 and " + str(free_spaces))

    for i in range(quantity_to_add):
        print("\n--- Chicken " + str(i + 1) + " ---")
        chicken = read_chicken()
        coop.add(chicken)


# option 2: the customer says how many chickens to remove
def remove_chickens(coop: ChickenCoop):
    if coop.get_number_of_chickens() == 0:
        print("The coop is empty, there are no chickens to remove")
        return

    show_chickens(coop)

    while True:
        maximum = coop.get_number_of_chickens()
        quantity_to_remove = read_int("How many chickens do you want to remove? (1-" + str(maximum) + "): ")
        if 1 <= quantity_to_remove <= maximum:
            break
        print("Invalid quantity, it must be between 1 and " + str(maximum))

    for i in range(quantity_to_remove):
        id_to_remove = read_int("Enter the id of the chicken to remove: ")
        coop.remove(id_to_remove)


# option 3: shows all the chickens of the coop
def show_chickens(coop: ChickenCoop):
    if coop.get_number_of_chickens() == 0:
        print("The coop is empty")
        return

    print("\n=== Chickens in the coop ===")
    coop.reset_iteration()
    while coop.has_next():
        current = coop.next()
        print("chickens --> " + str(current))
        print("Chicken's age: " + str(current.compute_age_in_years()))
        current.do_stuff()
    print("number of chickens --> " + str(coop.get_number_of_chickens()))


# this function asks the customer all the data of one chicken
def read_chicken() -> Chicken:
    global next_chicken_id

    chicken_id = next_chicken_id
    next_chicken_id += 1
    print("Chicken id: " + str(chicken_id))

    name = input("Name: ")
    color = input("Color: ")

    # we ask again if the date does not exist (for example 31 of February)
    while True:
        year = read_int("Year of birth (example 2024): ")
        month = read_int("Month of birth (1-12): ")
        day = read_int("Day of birth: ")
        try:
            born_on_date = date(year, month, day)
            break
        except ValueError:
            print("That date does not exist, please enter it again")

    molting = input("Is it molting? (true/false): ").strip().lower() == "true"

    return Chicken(chicken_id, name, color, born_on_date, molting)


# this function reads a number and asks again if the customer writes letters
def read_int(message: str) -> int:
    while True:
        try:
            return int(input(message).strip())
        except ValueError:
            print("Please enter a valid number")


if __name__ == "__main__":
    main()
