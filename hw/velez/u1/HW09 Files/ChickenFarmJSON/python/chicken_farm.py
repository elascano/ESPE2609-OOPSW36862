import json
import os
from datetime import datetime

FILE_NAME = "chickens.json"

class Egg:
    def __init__(self, id: int, size: str):
        self.id = id
        self.size = size

    def __str__(self):
        return f"Egg{{id={self.id}, size='{self.size}'}}"

class Poop:
    def __init__(self, id: int, color: str, consistency: str):
        self.id = id
        self.color = color
        self.consistency = consistency

    def __str__(self):
        return f"Poop{{id={self.id}, color='{self.color}', consistency='{self.consistency}'}}"

class Chicken:
    def __init__(self, ide: int, name: str, color: str, born_on_date: datetime, molting: bool):
        self.ide = ide
        self.name = name
        self.color = color
        self.born_on_date = born_on_date
        self.molting = molting
        self.age_in_years = self.compute_age_in_years()

    def do_stuff(self):
        self.__cluck()
        self.__wander()
        self.__eat()
        self.__drink(1.3)
        print(f"poop --> {self.__poop()}")
        print(f"egg --> {self.__lay_an_egg()}")

    def __cluck(self):
        print(f"The chicken {self.name} is clucking")

    def __wander(self):
        print(f"The chicken {self.name} is wandering")

    def __eat(self):
        print(f"The chicken {self.name} is eating")

    def __drink(self, amount_of_water: float):
        print(f"The chicken {self.name} is drinking ---> {amount_of_water} ml of water")

    def __poop(self):
        return Poop(2, "green", 'M')

    def __lay_an_egg(self):
        return Egg(2, 'M')

    def compute_age_in_years(self) -> int:
        today = datetime.now()
        age = today.year - self.born_on_date.year
        if (today.month, today.day) < (self.born_on_date.month, self.born_on_date.day):
            age -= 1
        return max(0, age)

    def to_dict(self) -> dict:
        return {
            "ide": self.ide,
            "name": self.name,
            "color": self.color,
            "bornOnDate": self.born_on_date.strftime("%Y-%m-%d"),
            "ageInYears": self.age_in_years,
            "molting": self.molting
        }

    def __str__(self):
        date_str = self.born_on_date.strftime('%Y-%m-%d')
        return f"Chicken{{ide={self.ide}, name='{self.name}', color='{self.color}', bornOnDate={date_str}, ageInYears={self.age_in_years}, molting={self.molting}}}"

def save_chickens_to_json(chickens: list):
    try:
        data = [c.to_dict() for c in chickens]
        with open(FILE_NAME, "w", encoding="utf-8") as f:
            json.dump(data, f, indent=4)
    except Exception as e:
        print(f"Error saving to JSON: {e}")

def load_chickens_from_json() -> list:
    if not os.path.exists(FILE_NAME):
        return []
    try:
        with open(FILE_NAME, "r", encoding="utf-8") as f:
            data = json.load(f)
            chickens = []
            for item in data:
                born_date = datetime.strptime(item["bornOnDate"], "%Y-%m-%d")
                chickens.append(Chicken(
                    item["ide"],
                    item["name"],
                    item["color"],
                    born_date,
                    item["molting"]
                ))
            return chickens
    except Exception as e:
        print(f"Error loading JSON file: {e}")
        return []

def read_int(prompt: str, min_val: int, max_val: int) -> int:
    while True:
        try:
            value = int(input(prompt).strip())
            if min_val <= value <= max_val:
                return value
            print(f"Error: Please enter a number between {min_val} and {max_val}.")
        except ValueError:
            print("Error: Invalid input. You must enter an integer number.")

def main():
    chickens = load_chickens_from_json()
    next_id = max((c.ide for c in chickens), default=0) + 1

    print("==================================")
    print("    Yandry Velez Chicken Farm     ")
    print("==================================")
    print(f"[Info] Loaded {len(chickens)} chicken(s) from {FILE_NAME}")

    while True:
        print("\n--- MAIN MENU ---")
        print("1. Add a new chicken")
        print("2. View all chickens (description and actions)")
        print("3. Delete a chicken")
        print("4. Exit")
        
        option = read_int("Select an option (1-4): ", 1, 4)

        if option == 1:
            while True:
                print(f"\n--- Enter chicken details (Automatic ID assigned: {next_id}) ---")
                
                name = input("Name: ").strip()
                while not name:
                    name = input("Name cannot be empty. Enter the name: ").strip()

                color = input("Color: ").strip()
                while not color:
                    color = input("Color cannot be empty. Enter the color: ").strip()

                current_year = datetime.now().year
                year = read_int(f"Birth year (2000-{current_year}): ", 2000, current_year)
                month = read_int("Birth month (1-12): ", 1, 12)
                day = read_int("Birth day (1-31): ", 1, 31)

                try:
                    born_on_date = datetime(year, month, day)
                except ValueError:
                    print("Invalid calendar date. Setting default to today.")
                    born_on_date = datetime.now()

                molting_input = input("Is it molting? (y/n): ").strip().lower()
                while molting_input not in ['y', 'yes', 'n', 'no']:
                    molting_input = input("Invalid input. Type 'y' for yes or 'n' for no: ").strip().lower()
                
                molting = molting_input in ['y', 'yes']

                new_chicken = Chicken(next_id, name, color, born_on_date, molting)
                chickens.append(new_chicken)
                save_chickens_to_json(chickens)

                print(f"--> Chicken '{name}' registered with ID #{next_id} and saved to {FILE_NAME}!")
                next_id += 1

                add_another = input("\nDo you want to add another chicken right now? (y/n): ").strip().lower()
                if add_another not in ['y', 'yes']:
                    break

        elif option == 2:
            print("\n================ CHICKEN LIST ================")
            if not chickens:
                print("There are no chickens registered on the farm yet.")
            else:
                for c in chickens:
                    print(f"\nChicken ID #{c.ide}:")
                    print(f"  -> Object (toString): {c}")
                    molting_str = "Yes" if c.molting else "No"
                    print(f"  -> Summary: Name: {c.name} | Color: {c.color} | Age: {c.age_in_years} years | Molting: {molting_str}")
                    print("  -> What is it doing?")
                    c.do_stuff()
                    print("-" * 49)

        elif option == 3:
            print("\n================ DELETE A CHICKEN ================")
            if not chickens:
                print("There are no chickens available to delete.")
            else:
                print("Current chickens:")
                for c in chickens:
                    print(f"  - ID #{c.ide}: {c.name} ({c.color})")
                
                id_to_delete = read_int("Enter the ID of the chicken you want to delete: ", 1, 999999)
                chicken_to_remove = next((c for c in chickens if c.ide == id_to_delete), None)

                if chicken_to_remove:
                    chickens.remove(chicken_to_remove)
                    save_chickens_to_json(chickens)
                    print(f"--> Chicken '{chicken_to_remove.name}' (ID #{id_to_delete}) was deleted from {FILE_NAME}!")
                else:
                    print(f"--> Error: No chicken found with ID #{id_to_delete}.")

        elif option == 4:
            save_chickens_to_json(chickens)
            print(f"All data saved to {FILE_NAME}. Exiting the farm simulator... Goodbye!")
            break

if __name__ == "__main__":
    main()