"""
@author Enrique Ayala, LogicLords,@ESPE
"""

from datetime import date
import Chicken
import ChickenCoop
from ChickenJsonStorage import ChickenJsonStorage

FILE_PATH = "chickens.json"

def read_date() -> date:
    year = int(input("Birth year (e.g. 2022): "))
    month = int(input("Birth month (1-12): "))
    day = int(input("Birth day (1-31): "))
    return date(year, month, day)

def read_molting() -> bool:
    val = input("Is it molting? (y/n): ").strip().lower()
    return val == 'y'

def add_chicken(coop: ChickenCoop.ChickenCoop, storage: ChickenJsonStorage):
    chicken_id = int(input("Id: "))
    name = input("Name: ")
    color = input("Color: ")
    born_on_date = read_date()
    molting = read_molting()

    chicken = Chicken.Chicken(chicken_id, name, color, born_on_date, molting)
    if coop.add(chicken):
        storage.save(coop)
        print("Chicken added.")

def remove_chicken(coop: ChickenCoop.ChickenCoop, storage: ChickenJsonStorage):
    chicken_id = int(input("Id of the chicken to remove: "))
    if coop.remove(chicken_id):
        storage.save(coop)
        print("Chicken removed.")

def update_chicken(coop: ChickenCoop.ChickenCoop, storage: ChickenJsonStorage):
    chicken_id = int(input("Id of the chicken to update: "))
    name = input("New name: ")
    color = input("New color: ")
    born_on_date = read_date()
    molting = read_molting()

    if coop.update(chicken_id, name, color, born_on_date, molting):
        storage.save(coop)
        print("Chicken updated.")

def main():
    print("Enrique Ayala Chicken Farm App")
    print("==============================")

    coop = ChickenCoop.ChickenCoop(1)
    storage = ChickenJsonStorage(FILE_PATH)
    storage.load(coop)

    option = -1
    while option != 0:
        print()
        print("1. Add chicken")
        print("2. Remove chicken")
        print("3. Update chicken")
        print("4. Next chicken")
        print("5. Reset iteration")
        print("6. Show coop")
        print("0. Exit")
        
        try:
            option = int(input("Choose an option: "))
        except ValueError:
            print("Invalid option.")
            continue

        if option == 1:
            add_chicken(coop, storage)
        elif option == 2:
            remove_chicken(coop, storage)
        elif option == 3:
            update_chicken(coop, storage)
        elif option == 4:
            next_chicken = coop.next()
            if next_chicken is None:
                print("No more chickens. Use option 5 to start again.")
            else:
                print(next_chicken)
                next_chicken.do_stuff()
        elif option == 5:
            coop.reset_iteration()
            print("Iteration reset.")
        elif option == 6:
            print(coop)
        elif option == 0:
            print("Bye!")
        else:
            print("Invalid option.")

if __name__ == "__main__":
    main()