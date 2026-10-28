
from datetime import date


class Egg:
    def __init__(self, egg_id, size):
        self.id = egg_id
        self.size = size

    def __str__(self):
        return f"Egg{{id={self.id}, size={self.size}}}"


class Poop:
    def __init__(self, poop_id, color, consistency):
        self.id = poop_id
        self.color = color
        self.consistency = consistency

    def __str__(self):
        return (
            f"Poop{{id={self.id}, color={self.color}, "
            f"consistency={self.consistency}}}"
        )


class Chicken:
    def __init__(self, chicken_id, name, color, born_on_date, molting):
        self.id = chicken_id
        self.name = name
        self.color = color
        self.born_on_date = born_on_date
        self.age_in_years = 0
        self.molting = molting

    def compute_age_in_years(self):
        today = date.today()
        age = today.year - self.born_on_date.year

        if (today.month, today.day) < (
            self.born_on_date.month, self.born_on_date.day
        ):
            age -= 1

        return age

    def cluck(self):
        print(f"The chicken {self.name} is clucking...")

    def wander(self):
        print(f"The chicken {self.name} is wandering...")

    def drink(self, amount_of_water):
        print(
            f"The chicken {self.name} is drinking "
            f"{amount_of_water} liters of water..."
        )

    def poop(self):
        return Poop(2, "green", "M")

    def lay_an_egg(self):
        return Egg(2, "S")

    def do_stuff(self):
        self.cluck()
        self.wander()
        self.drink(1.3)
        print(f"poop --> {self.poop()}")
        print(f"egg --> {self.lay_an_egg()}")

    def __str__(self):
        return (
            f"Chicken{{id={self.id}, name={self.name}, "
            f"color={self.color}, bornOnDate={self.born_on_date}, "
            f"age={self.compute_age_in_years()}, "
            f"molting={self.molting}}}"
        )


class ChickenCoop:
    def __init__(self, chicken_coop_id, capacity=10):
        self.chicken_coop_id = chicken_coop_id
        self.chickens = [None] * capacity
        self.current_index = 0
        self.iteration_index = 0

    def add(self):
        if self.current_index >= len(self.chickens):
            print("The chicken coop is full.")
            return

        chicken_id = self.read_integer(
            "Enter chicken ID (1-10): ", 1, 10
        )

        for chicken in self.chickens[:self.current_index]:
            if chicken.id == chicken_id:
                print("This ID is already occupied.")
                print("Remove this chicken first if you want to replace it.")
                return

        name = input("Enter chicken name: ")
        color = input("Enter chicken color: ")

        print("Enter chicken birth date:")
        year = self.read_integer("Year: ", 1, 9999)
        month = self.read_integer("Month: ", 1, 12)

        while True:
            day = self.read_integer("Day: ", 1, 31)

            try:
                born_on_date = date(year, month, day)
                break
            except ValueError:
                print("Invalid date. Enter the day again.")

        while True:
            answer = input(
                "Is the chicken molting? (true/false): "
            ).strip().lower()

            if answer in ("true", "false"):
                molting = answer == "true"
                break

            print("Invalid input. Enter true or false.")

        chicken = Chicken(
            chicken_id, name, color, born_on_date, molting
        )

        chicken.age_in_years = chicken.compute_age_in_years()
        self.chickens[self.current_index] = chicken
        self.current_index += 1

        print("Chicken added successfully.")

    def remove(self):
        chicken_id = self.read_integer(
            "Enter the ID of the chicken to remove: ", 1, 10
        )

        for i in range(self.current_index):
            chicken = self.chickens[i]

            if chicken.id == chicken_id:
                print("Chicken found:")
                print(chicken)

                answer = input(
                    "Are you sure you want to remove this chicken? "
                    "(yes/no): "
                ).strip().lower()

                if answer == "yes":
                    for j in range(i, self.current_index - 1):
                        self.chickens[j] = self.chickens[j + 1]

                    self.current_index -= 1
                    self.chickens[self.current_index] = None

                    print("Chicken removed successfully.")
                else:
                    print("Chicken removal cancelled.")

                return

        print(f"Chicken with ID {chicken_id} not found.")

    def reset_iteration(self):
        self.iteration_index = 0

    def next(self):
        if self.iteration_index < self.current_index:
            chicken = self.chickens[self.iteration_index]
            self.iteration_index += 1
            return chicken

        return None

    @staticmethod
    def read_integer(prompt, minimum, maximum):
        while True:
            try:
                value = int(input(prompt))

                if minimum <= value <= maximum:
                    return value

                print(
                    f"Enter a number between {minimum} and {maximum}."
                )
            except ValueError:
                print("Invalid input. Enter a number.")


def main():
    print("Chicken Farm App Cristopher Guanuna")
    print("===================================")

    chicken = Chicken(
        1, "Lucy", "brown and white", date(2020, 7, 10), True
    )
    egg = Egg(1, "H")
    poop = Poop(1, "brown", "S")
    chicken1 = Chicken(
        2, "Renata", "White", date(2025, 12, 20), False
    )

    chicken.age_in_years = chicken.compute_age_in_years()
    chicken1.age_in_years = chicken1.compute_age_in_years()

    print(f"chicken --> {chicken}")
    print(f"egg --> {egg}")
    print(f"poop --> {poop}")
    print(f"chicken 2 --> {chicken1}")

    chicken.do_stuff()

    chickens = [None] * 10
    chickens[0] = Chicken(
        3, "Pepita", "black", date(2020, 2, 2), True
    )
    chickens[1] = chicken

    for item in chickens:
        print(f"chickens --> {item}")

    for i in range(len(chickens)):
        chickens[i] = Chicken(
            i, f"chicken{i + 1}", "white",
            date(2020 + i, 3, 3), True
        )
        chickens[i].age_in_years = (
            chickens[i].compute_age_in_years()
        )

    for item in chickens:
        print(f"chickens --> {item}")

    chicken_coop = ChickenCoop(1)

    while True:
        print("\n=== CHICKEN COOP MENU ===")
        print("1. Add chicken")
        print("2. Remove chicken")
        print("3. Show all chickens")
        print("4. Exit")

        try:
            option = int(input("Choose an option: "))
        except ValueError:
            print("Invalid option. Enter a number.")
            continue

        if option == 1:
            chicken_coop.add()

        elif option == 2:
            chicken_coop.remove()

        elif option == 3:
            chicken_coop.reset_iteration()

            while True:
                chicken_item = chicken_coop.next()

                if chicken_item is None:
                    break

                print(chicken_item)

        elif option == 4:
            print("Exiting menu...")
            break

        else:
            print("Invalid option.")


if __name__ == "__main__":
    main()
