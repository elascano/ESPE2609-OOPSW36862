"""
@author Paucar Matias, ByteWorks, @ESPE
"""
from datetime import date

from controller import file_manager
from model.chicken import Chicken
from model.chicken_coop import ChickenCoop

COOP_ID = 1

# the coop is loaded from the file when the program starts
coop = None

# counter to generate the ids automatically
next_chicken_id = 1


def main():
    global coop

    print("Matias Paucar's Chicken Farm App")
    print("#################################")

    coop = file_manager.load(COOP_ID)
    update_next_chicken_id()
    print("Chicken coop id: " + str(COOP_ID))
    print(str(coop.get_number_of_chickens()) + " chicken(s) loaded from " + file_manager.FILE_NAME)

    option = -1
    while option != 0:
        print("\n----------- MENU -----------")
        print("1. Add chickens")
        print("2. Remove a chicken")
        print("3. List chickens")
        print("0. Exit")
        option = read_int("Choose an option: ")

        if option == 1:
            add_chickens()
        elif option == 2:
            remove_chickens()
        elif option == 3:
            show_chickens()
        elif option == 0:
            print("Goodbye!")
        else:
            print("Invalid option, try again")


# option 1: the customer says how many chickens to add
def add_chickens():
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
        if coop.add(chicken):
            print("The chicken " + chicken.get_name() + " was added to the coop")
        else:
            print("The coop is full, we can not add any more chickens")

    # persistence: the file is updated with the old chickens and the new ones
    file_manager.save(coop)
    print("The file " + file_manager.FILE_NAME + " was updated")


# option 2: the customer says how many chickens to remove
def remove_chickens():
    if coop.get_number_of_chickens() == 0:
        print("The coop is empty, there are no chickens to remove")
        return

    show_chickens()

    while True:
        maximum = coop.get_number_of_chickens()
        quantity_to_remove = read_int("How many chickens do you want to remove? (1-" + str(maximum) + "): ")
        if 1 <= quantity_to_remove <= maximum:
            break
        print("Invalid quantity, it must be between 1 and " + str(maximum))

    for i in range(quantity_to_remove):
        id_to_remove = read_int("Enter the id of the chicken to remove: ")
        chicken = coop.find_by_id(id_to_remove)
        if chicken is not None and coop.remove(id_to_remove):
            print("The chicken " + chicken.get_name() + " was removed from the coop")
        else:
            print("There is no chicken with the id " + str(id_to_remove))

    # persistence: the file is updated without the removed chickens
    file_manager.save(coop)
    print("The file " + file_manager.FILE_NAME + " was updated")


# option 3: shows the chickens that are saved in the file
def show_chickens():
    global coop

    # persistence: we read the file again to show what is really saved
    coop = file_manager.load(COOP_ID)

    if coop.get_number_of_chickens() == 0:
        print("The coop is empty")
        return

    print("\n=== Chickens in the coop (" + file_manager.FILE_NAME + ") ===")
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

    name = read_text("Name: ")
    color = read_text("Color: ")

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


# the next id is the biggest id of the file + 1, so the ids are never repeated
def update_next_chicken_id():
    global next_chicken_id

    max_id = 0
    coop.reset_iteration()
    while coop.has_next():
        chicken = coop.next()
        if chicken.get_id() > max_id:
            max_id = chicken.get_id()
    next_chicken_id = max_id + 1


# this function reads a text that can not be empty
def read_text(message: str) -> str:
    while True:
        text = input(message).strip()
        if text != "":
            return text
        print("This field is required")


# this function reads a number and asks again if the customer writes letters
def read_int(message: str) -> int:
    while True:
        try:
            return int(input(message).strip())
        except ValueError:
            print("Please enter a valid number")


if __name__ == "__main__":
    main()
