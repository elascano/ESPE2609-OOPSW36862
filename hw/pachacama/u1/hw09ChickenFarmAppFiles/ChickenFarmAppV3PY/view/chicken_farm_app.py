"""
@author Andres Pachacama, Byte Work, @ESPE
"""
import os
from datetime import date

from model.chicken import Chicken
from model.chicken_coop import ChickenCoop
from utils.file_manager import FileManager

WIDTH = 60                          
FILE_NAME = "chickens.csv"          
FILE_PATH = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), FILE_NAME)

coop = None                         
unsaved_changes = False             


def main():
    load_at_start()

    option = -1
    while option != 0:
        print_menu()
        option = read_int(" Choose an option: ")
        match option:
            case 1:
                insert_chickens()
            case 2:
                save_chickens()
            case 3:
                read_saved_chickens()
            case 4:
                show_coop()
            case 5:
                remove_chicken()
            case 6:
                walk_through_coop()
            case 0:
                exit_app()
            case _:
                print(" [X] Invalid option, choose a number from the menu")

        if option != 0:
            pause()


# =========================== MENU ===========================

def print_menu():
    print()
    print_header()

    if unsaved_changes:
        status = "unsaved changes (*)"
    else:
        status = "everything saved"
    print(" Coop #" + str(coop.chicken_coop_id)
          + "   |   chickens: " + str(coop.get_count()) + "/" + str(coop.get_capacity())
          + "   |   " + status)
    print("-" * WIDTH)
    print("   [1]  Insert chickens")
    print("   [2]  Save chickens to file")
    print("   [3]  Read saved chickens")
    print("   [4]  Show chickens in the coop")
    print("   [5]  Remove a chicken")
    print("   [6]  Walk through the coop (next / reset)")
    print("   [0]  Exit")
    print("-" * WIDTH)


def pause():
    print()
    input(" Press Enter to go back to the menu...")


# =========================== OPTIONS ===========================

def load_at_start():
    global coop        
    try:
        coop = FileManager.load(FILE_PATH, 1)
        if coop.is_empty():
            print(" No saved chickens yet, starting with an empty coop.")
        else:
            print(" [OK] " + str(coop.get_count()) + " chickens loaded from " + FILE_NAME)
    except OSError as error:
        coop = ChickenCoop(1)
        print(" [X] The file could not be read: " + str(error))


# [1]
def insert_chickens():
    global unsaved_changes
    print_section("INSERT CHICKENS")
    add_another = True

    while add_another:
        if coop.is_full():
            print(" [X] The coop is full (" + str(coop.get_capacity()) + " chickens)")
            return

        print()
        print(" Chicken #" + str(coop.get_count() + 1))
        chicken = read_chicken()
        coop.add(chicken)
        unsaved_changes = True
        print(" [OK] " + chicken.name + " was added to the coop ("
              + str(coop.get_count()) + "/" + str(coop.get_capacity()) + ")")

        add_another = ask_yes_no(" Add another chicken? (y/n): ")

    print(" Remember to save them with option [2]")


# [2]
def save_chickens():
    global unsaved_changes
    print_section("SAVE CHICKENS TO FILE")
    try:
        FileManager.save(coop, FILE_PATH)
        unsaved_changes = False
        print(" [OK] " + str(coop.get_count()) + " chickens saved in:")
        print("      " + FileManager.full_path(FILE_PATH))
    except OSError as error:
        print(" [X] The chickens could not be saved: " + str(error))


# [3]
def read_saved_chickens():
    if not FileManager.exists(FILE_PATH):
        print_section("SAVED CHICKENS (" + FILE_NAME + ")")
        print(" There is no saved file yet, use option [2] first")
        return
    try:
        saved_coop = FileManager.load(FILE_PATH, coop.chicken_coop_id)
        print_chicken_table(saved_coop, "SAVED CHICKENS (" + FILE_NAME + ")")
        if unsaved_changes:
            print(" (*) The coop has changes that are not saved in the file yet")
    except OSError as error:
        print(" [X] The file could not be read: " + str(error))


# [4]
def show_coop():
    print_chicken_table(coop, "CHICKENS IN THE COOP")
    print()
    print(" coop --> " + str(coop))     # the toString of V2


# [5]
def remove_chicken():
    global unsaved_changes
    print_chicken_table(coop, "REMOVE A CHICKEN")
    if coop.is_empty():
        return

    id = read_int(" Enter the id of the chicken to remove: ")
    before = coop.get_count()
    coop.remove(id)

    if coop.get_count() < before:       # if count went down, it was removed
        unsaved_changes = True
        print(" [OK] The chicken with id " + str(id) + " was removed")
        print(" Remember to save the change with option [2]")
    else:
        print(" [X] There is no chicken with id " + str(id))


# [6]
def walk_through_coop():
    print_section("WALK THROUGH THE COOP")
    if coop.is_empty():
        print(" The coop is empty")
        return

    coop.reset_iteration()
    print(" reset_iteration()  ->  we start at the first chicken")
    print(" [n] next()   [r] reset_iteration()   [b] back to the menu")

    option = ""
    while option != "b":
        option = read_text(" > ").lower()
        match option:
            case "n":
                chicken = coop.next()
                if chicken is not None:
                    print("   next()             ->  " + chicken.name + " (id " + str(chicken.id) + ")")
                else:
                    print("   next()             ->  None (no more chickens, press r)")
            case "r":
                coop.reset_iteration()
                print("   reset_iteration()  ->  back to the first chicken")
            case "b":
                pass
            case _:
                print("   [X] Use n, r or b")


def exit_app():
    if unsaved_changes:
        print()
        save = ask_yes_no(" You have unsaved changes. Save them before leaving? (y/n): ")
        if save:
            save_chickens()
    print_section("GOODBYE")
    print(" Thanks for using the Chicken Farm App!")
    print("=" * WIDTH)


# =========================== TABLE ===========================

def print_chicken_table(chicken_coop, title):
    print_section(title)

    if chicken_coop.is_empty():
        print(" The coop is empty")
        return

    print(f" {'ID':<4}| {'NAME':<11}| {'COLOR':<11}| {'BORN':<11}| {'AGE':<4}| MOLT")
    print(" ----+------------+------------+------------+-----+-----")

    chicken_coop.reset_iteration()
    chicken = chicken_coop.next()
    while chicken is not None:
        if chicken.molting:
            molting = "yes"
        else:
            molting = "no"
        print(f" {chicken.id:<4}| {chicken.name[:10]:<11}| {chicken.color[:10]:<11}"
              f"| {chicken.born_on_date.isoformat():<11}| {chicken.age_in_years:<4}| {molting}")
        chicken = chicken_coop.next()

    print(" Total: " + str(chicken_coop.get_count()) + "/" + str(chicken_coop.get_capacity()) + " chickens")


# =========================== KEYBOARD ===========================

def read_chicken():
    id = read_int("   id .................. ")
    while coop.exists(id):
        print("   [X] There is already a chicken with id " + str(id))
        id = read_int("   id .................. ")
    name = read_required_text("   name ................ ")
    color = read_required_text("   color ............... ")
    born_on_date = read_date()
    molting = ask_yes_no("   molting? (y/n) ...... ")

    chicken = Chicken(id, name, color, born_on_date, 0, molting)
    chicken.set_age_in_years()
    return chicken


def read_date():
    while True:
        year = read_int("   birth year .......... ")
        month = read_int("   birth month (1-12) .. ")
        day = read_int("   birth day ........... ")
        try:
            born_on_date = date(year, month, day)
            if born_on_date > date.today():
                print("   [X] The chicken can not be born in the future, try again")
            else:
                return born_on_date
        except ValueError:
            print("   [X] That date does not exist, try again")


def read_text(message):
    return input(message).strip()


def read_required_text(message):
    text = read_text(message)
    while text == "":
        print("   [X] This field can not be empty")
        text = read_text(message)
    return text


def read_int(message):
    while True:
        text = read_text(message)
        try:
            return int(text)
        except ValueError:
            print("   [X] Please enter a whole number")


def ask_yes_no(message):
    while True:
        answer = read_text(message).lower()
        if answer == "y":
            return True
        if answer == "n":
            return False
        print("   [X] Please answer y or n")


# =========================== DRAWING ===========================

def print_header():
    inside = WIDTH - 2
    print("+" + "=" * inside + "+")
    print("|" + "ANDRES PACHACAMA'S CHICKEN FARM APP".center(inside) + "|")
    print("|" + "version 3.0".center(inside) + "|")
    print("+" + "=" * inside + "+")


def print_section(title):
    print()
    print((" " + title + " ").center(WIDTH, "-"))
