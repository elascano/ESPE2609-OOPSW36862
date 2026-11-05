"""
@author Andres Pachacama, Byte Work, @ESPE
"""
import json
import os

from model.doll import Doll
from utils.json_file_manager import JsonFileManager

WIDTH = 60
FILE_NAME = "dolls.json"
FILE_PATH = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), FILE_NAME)

dolls = []
unsaved_changes = False


def main():
    load_at_start()

    option = -1
    while option != 0:
        print_menu()
        option = read_int(" Choose an option: ")
        match option:
            case 1:
                create_dolls()
            case 2:
                save_dolls()
            case 3:
                read_dolls_from_file()
            case 4:
                show_dolls()
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
    print(" Dolls in the list: " + str(len(dolls)) + "   |   " + status)
    print("-" * WIDTH)
    print("   [1]  Create dolls")
    print("   [2]  Save dolls to " + FILE_NAME)
    print("   [3]  Read dolls from " + FILE_NAME)
    print("   [4]  Show dolls in the list")
    print("   [0]  Exit")
    print("-" * WIDTH)


def pause():
    print()
    input(" Press Enter to go back to the menu...")


# =========================== OPTIONS ===========================

def load_at_start():
    global dolls
    try:
        dolls = JsonFileManager.load(FILE_PATH)
        if len(dolls) == 0:
            print(" No saved dolls yet, starting with an empty list.")
        else:
            print(" [OK] " + str(len(dolls)) + " dolls loaded from " + FILE_NAME)
    except (OSError, json.JSONDecodeError) as error:
        dolls = []
        print(" [X] The file could not be read: " + str(error))


# [1]
def create_dolls():
    global unsaved_changes
    print_section("CREATE DOLLS")
    add_another = True

    while add_another:
        print()
        print(" Doll #" + str(len(dolls) + 1))
        doll = read_doll()
        dolls.append(doll)
        unsaved_changes = True
        print(" [OK] " + doll.name + " was added to the list (" + str(len(dolls)) + " dolls)")

        add_another = ask_yes_no(" Add another doll? (y/n): ")

    print(" Remember to save them with option [2]")


# [2]
def save_dolls():
    global unsaved_changes
    print_section("SAVE DOLLS TO " + FILE_NAME)
    try:
        JsonFileManager.save(dolls, FILE_PATH)
        unsaved_changes = False
        print(" [OK] " + str(len(dolls)) + " dolls saved in:")
        print("      " + JsonFileManager.full_path(FILE_PATH))
    except OSError as error:
        print(" [X] The dolls could not be saved: " + str(error))


# [3]
def read_dolls_from_file():
    if not JsonFileManager.exists(FILE_PATH):
        print_section("DOLLS IN " + FILE_NAME)
        print(" There is no saved file yet, use option [2] first")
        return
    try:
        saved_dolls = JsonFileManager.load(FILE_PATH)
        print_doll_table(saved_dolls, "DOLLS IN " + FILE_NAME)
        if unsaved_changes:
            print(" (*) The list has changes that are not saved in the file yet")
    except (OSError, json.JSONDecodeError) as error:
        print(" [X] The file could not be read: " + str(error))


# [4]
def show_dolls():
    print_doll_table(dolls, "DOLLS IN THE LIST")
    print()
    for doll in dolls:
        print(" " + str(doll))


def exit_app():
    if unsaved_changes:
        print()
        save = ask_yes_no(" You have unsaved changes. Save them before leaving? (y/n): ")
        if save:
            save_dolls()
    print_section("GOODBYE")
    print(" Thanks for using the Doll App!")
    print("=" * WIDTH)


# =========================== TABLE ===========================

def print_doll_table(doll_list, title):
    print_section(title)

    if len(doll_list) == 0:
        print(" There are no dolls")
        return

    print(f" {'ID':<5}| {'NAME':<16}| {'BRAND':<16}| PRICE")
    print(" -----+-----------------+-----------------+-----------")
    for doll in doll_list:
        print(f" {doll.id:<5}| {doll.name[:15]:<16}| {doll.brand[:15]:<16}| ${doll.price:.2f}")

    print(" Total: " + str(len(doll_list)) + " dolls")


# =========================== KEYBOARD ===========================

def read_doll():
    id = read_int("   id .................. ")
    while exists(id):
        print("   [X] There is already a doll with id " + str(id))
        id = read_int("   id .................. ")
    name = read_required_text("   name ................ ")
    brand = read_required_text("   brand ............... ")
    price = read_price()

    return Doll(id, name, brand, price)


def exists(id):
    for doll in dolls:
        if doll.id == id:
            return True
    return False


def read_price():
    while True:
        price = read_float("   price ($) ........... ")
        if price < 0:
            print("   [X] The price can not be negative")
        else:
            return price


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


def read_float(message):
    while True:
        text = read_text(message).replace(",", ".")
        try:
            return float(text)
        except ValueError:
            print("   [X] Please enter a number (example: 19.99)")


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
    print("|" + "ANDRES PACHACAMA'S DOLL APP".center(inside) + "|")
    print("|" + "ArrayList + JSON file".center(inside) + "|")
    print("+" + "=" * inside + "+")


def print_section(title):
    print()
    print((" " + title + " ").center(WIDTH, "-"))
