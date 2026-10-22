import sys
from datetime import date
from model.chicken import Chicken
from model.chicken_coop import ChickenCoop
from model.egg import Egg
from model.poop import Poop

class ChickenFarmApp:
    
    @staticmethod
    def main(args=None):
        print("Demian Armas's Chicken Farm App")
        print("===============================")

        coop = ChickenCoop(1)

        while True:
            ChickenFarmApp.print_menu()
            option = ChickenFarmApp.read_int("Choose an option: \n", 0, 6)

            match option:
                case 1:
                    ChickenFarmApp.add_chicken(coop)
                case 2:
                    ChickenFarmApp.remove_chicken(coop)
                case 3:
                    chicken = coop.next()
                    print(chicken if chicken else "The coop is empty.")
                case 4:
                    coop.reset_iteration()
                case 5:
                    print(coop)
                case 6:
                    ChickenFarmApp.run_demo()
                case 0:
                    break
                case _:
                    print("Invalid option.")

    @staticmethod
    def print_menu():
        print("---------- MENU ----------")
        print("1. Add chicken")
        print("2. Remove chicken")
        print("3. Next chicken")
        print("4. Reset iteration")
        print("5. Show coop")
        print("6. Run demo")
        print("0. Exit")

    @staticmethod
    def read_int(prompt: str, min_val: int, max_val: int) -> int:
        while True:
            try:
                val = int(input(prompt).strip())
                if min_val <= val <= max_val:
                    return val
                print(f"Enter a number between {min_val} and {max_val}.")
            except ValueError:
                print("Invalid number. Try again.")

    @staticmethod
    def add_chicken(coop: ChickenCoop):
        id_c = int(input("Id: ").strip())
        name = input("Name: \n").strip()
        color = input("Color: ").strip()
        print("Birth date:")
        day = ChickenFarmApp.read_int("  Day (1-31): ", 1, 31)
        month = ChickenFarmApp.read_int("  Month (1-12): ", 1, 12)
        year = ChickenFarmApp.read_int("  Year (e.g. 2022): ", 1900, 2100)

        born_on_date = date(year, month, day)
        chicken = Chicken(id_c, name, color, born_on_date)
        chicken.set_age_in_years(chicken.compute_age_in_years())
        coop.add(chicken)

    @staticmethod
    def remove_chicken(coop: ChickenCoop):
        id_c = int(input("Id of the chicken to remove: \n").strip())
        coop.remove(id_c)

    @staticmethod
    def run_demo():
        chicken1 = Chicken(1, "Lucy", "brown and withe", date(2023, 1, 1))
        egg = Chicken.lay_an_egg()
        poop = Chicken.poop()

        print(f"chicken --> {chicken1}")
        print(f"egg --> {egg}")
        print(f"poop --> {poop}")

        chicken2 = Chicken(2, "Renata", "white", date(2021, 5, 10))
        print(f"chicken 2--> {chicken2}")
        print(f"The age of the chicken {chicken2.get_name()} is {chicken2.compute_age_in_years()}")

        chicken1.do_stuff(200.0)

        chickens = [
            Chicken(3, "Pepita", "black", date(2022, 3, 15)),
            Chicken(4, "Lucy", "white", date(2023, 8, 20))
        ]

        print("chickens -->")
        for chicken in chickens:
            print(f"chicken {chicken}")

        print("chickens -->")
        for chicken in chickens:
            print(f"The age of the chicken {chicken.get_name()} is {chicken.compute_age_in_years()}")

if __name__ == "__main__":
    ChickenFarmApp.main(sys.argv[1:])