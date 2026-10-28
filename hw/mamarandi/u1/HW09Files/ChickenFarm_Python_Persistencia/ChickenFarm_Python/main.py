from datetime import date
from random import randint
from chicken import Chicken
from chicken_coop import ChickenCoop
from egg import Egg
from poop import Poop
from storage import load_chickens, save_chickens


def main():
    print("Marcos Mamarandi Chicken Farm App")
    print("=================================")

    chickens = [None] * 10
    chicken = Chicken(2, "Maruja", "white", date(1970, 12, 17), False)
    chicken.setAgeInYears()
    egg = Egg(1, "M")
    print("my chicken is -->", chicken)
    print("egg -->", egg)
    print("egg size -->", egg.getSize())

    poop = Poop(1, "brown", "S")
    print("poop -->", poop)
    chicken1 = Chicken(1, "Lucy", "brown and white", date(1971, 1, 17), True)
    print("chicken 1 -->", chicken1)
    chicken1.setAgeInYears()
    print("the age of the chicken -->", chicken.getName(), ", is -->", chicken.computeAgeInYears())
    chicken.doStuff()

    chickens[0] = Chicken(3, "Pepita", "black", date(2020, 3, 2), True)
    chickens[1] = chicken
    for c in chickens:
        print("chickens -->", c)

    for i in range(10):
        chickens[i] = Chicken(i, f"chiken{i + 1}", "white", date(2010 + i, 4, 3), True)
        chickens[i].setAgeInYears()
    for c in chickens:
        print("chikens -->", c)

    saved = load_chickens()
    coopChickens = saved + [None] * (10 - len(saved))
    print(f"Loaded {len(saved)} chickens from file")
    chickenCoop = ChickenCoop(1, coopChickens)
    while True:
        print("\n===== CHICKEN COOP MENU =====")
        print("1. Add a chicken")
        print("2. Remove a chicken")
        print("3. Show chickens")
        print("4. Exit")
        option = input("Choose an option: ").strip()

        if option == "1":
            usedIds = {c.getId() for c in coopChickens if c is not None}
            id = randint(1000, 9999)
            while id in usedIds:
                id = randint(1000, 9999)
            name = input("Enter chicken name: ")
            color = input("Enter chicken color: ")
            try:
                year = int(input("Enter birth year: "))
                month = int(input("Enter birth month (1-12): "))
                day = int(input("Enter birth day: "))
                birthDate = date(year, month, day)
                moltingText = input("Is the chicken molting? (true/false): ").strip().lower()
                if moltingText not in ("true", "false"):
                    print("Please enter true or false")
                    continue
                if birthDate > date.today():
                    print("Birth date cannot be in the future")
                    continue
            except ValueError:
                print("Invalid date or number")
                continue
            newChicken = Chicken(id, name, color, birthDate, moltingText == "true")
            newChicken.setAgeInYears()
            chickenCoop.add(newChicken)
            if any(c is newChicken for c in coopChickens):
                save_chickens(coopChickens)
                print("Generated chicken ID:", id)
        elif option == "2":
            try:
                chickenCoop.remove(int(input("Enter the ID of the chicken to remove: ")))
                save_chickens(coopChickens)
            except ValueError:
                print("Invalid ID")
        elif option == "3":
            chickenCoop.resetIteration()
            while True:
                nextChicken = chickenCoop.next()
                if nextChicken is None:
                    break
                print(nextChicken)
        elif option == "4":
            print("Exiting Chicken Coop...")
            break
        else:
            print("Invalid option")


if __name__ == "__main__":
    main()
