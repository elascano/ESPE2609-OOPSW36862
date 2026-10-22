from datetime import date

from chicken import Chicken
from chicken_coop import ChickenCoop
from egg import Egg
from poop import Poop


def print_menu() -> None:
    print("---------- MENU ----------")
    print("1. Add chicken")
    print("2. Remove chicken")
    print("3. Next chicken")
    print("4. Reset iteration")
    print("5. Show coop")
    print("6. Run demo")
    print("0. Exit")


def read_int(message: str, minimum: int, maximum: int) -> int:
    while True:
        try:
            value = int(input(message).strip())
        except ValueError:
            print("Invalid number. Try again.")
            continue
        if minimum <= value <= maximum:
            return value
        print(f"Enter a number between {minimum} and {maximum}.")


def add_chicken(coop: ChickenCoop) -> None:
    id = read_int("Id: ", 0, 2**31 - 1)
    name = input("Name: ").strip()
    color = input("Color: ").strip()

    print("Birth date:")
    while True:
        day = read_int("  Day (1-31): ", 1, 31)
        month = read_int("  Month (1-12): ", 1, 12)
        year = read_int("  Year (e.g. 2022): ", 1900, 2100)
        try:
            born_on_date = date(year, month, day)
            break
        except ValueError:
            print("That date does not exist. Try again.")

    chicken = Chicken(id, name, color, born_on_date)
    chicken.set_age_in_years()
    coop.add(chicken)


def remove_chicken(coop: ChickenCoop) -> None:
    id = read_int("Id of the chicken to remove: ", 0, 2**31 - 1)
    coop.remove(id)


def run_demo() -> None:
    chicken = Chicken(1, "Lucy", "brown and white", date(1970, 12, 17))
    chicken.set_age_in_years()
    egg = Egg(1, "M")
    poop = Poop(1, "brown", "S")
    chicken1 = Chicken(2, "Renata", "white", date(1970, 11, 17))
    chicken1.set_age_in_years()

    print("chicken -->", chicken)
    print("egg -->", egg)
    print("poop -->", poop)
    print("chicken 2-->", chicken1)
    print(f"The age of the chicken --> {chicken.name} is {chicken.compute_age_in_years()}")

    chicken.do_stuff()
    chicken1.do_stuff()

    chickens = [None] * 10
    chickens[0] = Chicken(3, "Pepita", "black", date(2020, 2, 2))
    chickens[1] = chicken
    chickens[2] = chicken1

    for i in range(10):
        print("chickens -->", chickens[i])

    for i in range(len(chickens)):
        age_for_the_loop = 2010 + i
        chickens[i] = Chicken(i, f"chicken {i + 1}", "white ", date(age_for_the_loop, 1, 1))
        chickens[i].set_age_in_years()

    for i in range(10):
        print("chickens -->", chickens[i])


def main() -> None:
    print("Carlos Parra's Chicken Farm App")
    print("===============================")

    coop = ChickenCoop(1)
    option = -1

    while option != 0:
        print_menu()
        option = read_int("Choose an option: ", 0, 6)
        print()

        if option == 1:
            add_chicken(coop)
        elif option == 2:
            remove_chicken(coop)
        elif option == 3:
            coop.next()
        elif option == 4:
            coop.reset_iteration()
        elif option == 5:
            print(coop)
        elif option == 6:
            run_demo()
        elif option == 0:
            print("Bye!")
        print()


if __name__ == "__main__":
    main()
