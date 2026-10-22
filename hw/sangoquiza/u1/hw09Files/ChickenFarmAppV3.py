__author__ = "Daniel Sangoquiza"

import os
from datetime import date, datetime

class Egg:
    def __init__(self, id_val, size):
        self.id = id_val
        self.size = size

    def __str__(self):
        return f"Egg{{id={self.id}, size={self.size}}}"

    def get_id(self):
        return self.id

    def set_id(self, id_val):
        self.id = id_val

    def get_size(self):
        return self.size

    def set_size(self, size):
        self.size = size


class Poop:
    def __init__(self, id_val, color, consistency):
        self.id = id_val
        self.color = color
        self.consistency = consistency

    def __str__(self):
        return f"Poop{{id={self.id}, color={self.color}, consistency={self.consistency}}}"

    def get_id(self):
        return self.id

    def set_id(self, id_val):
        self.id = id_val

    def get_color(self):
        return self.color

    def set_color(self, color):
        self.color = color

    def get_consistency(self):
        return self.consistency

    def set_consistency(self, consistency):
        self.consistency = consistency


class Chicken:
    def __init__(self, id_val, name, color, born_on_date, age_in_years, molting):
        self.id = id_val
        self.name = name
        self.color = color
        self.born_on_date = born_on_date
        self.age_in_years = age_in_years
        self.molting = molting

    def do_stuff(self):
        self.cluck()
        self.wander()
        self.drink(1.3)
        print(f"poop ---> {self.poop()}")
        print(f"egg ---> {self.lay_an_egg()}")

    def cluck(self):
        print(f"The chicken {self.name} is clucking ...")

    def wander(self):
        print(f"The chicken {self.name} is wandering ...")

    def eat(self):
        print(f"The chicken {self.name} is eating ...")

    def drink(self, amount_of_water):
        print(f"The chicken {self.name} is drinking --> {amount_of_water}ml of water")

    def poop(self):
        return Poop(2, "green", 'M')

    def lay_an_egg(self):
        return Egg(2, 'S')

    def compute_age_in_years(self):
        today = date.today()
        age = today.year - self.born_on_date.year
        if today.month < self.born_on_date.month:
            age -= 1
        elif today.month == self.born_on_date.month and today.day < self.born_on_date.day:
            age -= 1
        return age

    def __str__(self):
        return f"\n Chicken{{id={self.id}, name={self.name}, color={self.color}, bornOnDate={self.born_on_date}, age={self.age_in_years}, molting={self.molting}}}"

    def get_id(self):
        return self.id

    def set_id(self, id_val):
        self.id = id_val

    def get_name(self):
        return self.name

    def set_name(self, name):
        self.name = name

    def get_color(self):
        return self.color

    def set_color(self, color):
        self.color = color

    def get_born_on_date(self):
        return self.born_on_date

    def set_born_on_date(self, born_on_date):
        self.born_on_date = born_on_date

    def get_age_in_years(self):
        return self.age_in_years

    def set_age_in_years(self):
        self.age_in_years = self.compute_age_in_years()

    def is_molting(self):
        return self.molting

    def set_molting(self, molting):
        self.molting = molting


class ChickenCoop:
    CAPACITY = 10

    def __init__(self, chicken_coop_id=1, chickens=None):
        self.chicken_coop_id = chicken_coop_id
        self.chickens = []
        self.index = 0

        if chickens is not None:
            for chicken in chickens:
                if chicken is not None:
                    self.add(chicken)
        else:
            self.add(Chicken(1, "Lucy", "black", date.today(), 0, False))
            self.add(Chicken(2, "Alejandra", "white", date.today(), 0, False))

    def add(self, chicken):
        if len(self.chickens) < self.CAPACITY:
            self.chickens.append(chicken)

    def remove(self, chicken_id):
        for i in range(len(self.chickens)):
            if self.chickens[i].get_id() == chicken_id:
                del self.chickens[i]
                return

    def reset_iteration(self):
        self.index = 0

    def next(self):
        if self.index < len(self.chickens):
            chicken = self.chickens[self.index]
            self.index += 1
            return chicken
        return None

    def exists(self, chicken_id):
        for chicken in self.chickens:
            if chicken.get_id() == chicken_id:
                return True
        return False

    def is_full(self):
        return len(self.chickens) == self.CAPACITY

    def is_empty(self):
        return len(self.chickens) == 0

    def __str__(self):
        local_chickens = ""
        for chicken in self.chickens:
            if chicken is not None:
                local_chickens += str(chicken) + "\t"
        return f"ChickenCoop{{chickenCoopId={self.chicken_coop_id}, chickens=\n{local_chickens}}}"

    def get_chicken_coop_id(self):
        return self.chicken_coop_id

    def set_chicken_coop_id(self, chicken_coop_id):
        self.chicken_coop_id = chicken_coop_id

    def get_count(self):
        return len(self.chickens)

    def get_capacity(self):
        return self.CAPACITY


class FileManager:
    SEPARATOR = ";"
    HEADER = "id;name;color;bornOnDate;molting"
    DATE_FORMAT = "%Y-%m-%d"

    @staticmethod
    def save(coop, file_name):
        with open(file_name, 'w') as file:
            file.write(FileManager.HEADER + "\n")
            coop.reset_iteration()
            chicken = coop.next()
            while chicken is not None:
                date_str = chicken.get_born_on_date().strftime(FileManager.DATE_FORMAT)
                molting_str = str(chicken.is_molting()).lower()
                
                line = (f"{chicken.get_id()}{FileManager.SEPARATOR}"
                        f"{FileManager.clean(chicken.get_name())}{FileManager.SEPARATOR}"
                        f"{FileManager.clean(chicken.get_color())}{FileManager.SEPARATOR}"
                        f"{date_str}{FileManager.SEPARATOR}"
                        f"{molting_str}\n")
                file.write(line)
                chicken = coop.next()

    @staticmethod
    def load(file_name, chicken_coop_id):
        coop = ChickenCoop(chicken_coop_id, [])

        if not FileManager.exists(file_name):
            return coop

        with open(file_name, 'r') as file:
            lines = file.readlines()
            if len(lines) > 1:
                for line in lines[1:]:
                    if coop.is_full():
                        break
                    chicken = FileManager.to_chicken(line)
                    if chicken is not None:
                        coop.add(chicken)
        return coop

    @staticmethod
    def exists(file_name):
        return os.path.exists(file_name)

    @staticmethod
    def full_path(file_name):
        return os.path.abspath(file_name)

    @staticmethod
    def to_chicken(line):
        parts = line.split(FileManager.SEPARATOR)
        if len(parts) != 5:
            return None

        try:
            id_val = int(parts[0].strip())
            name = parts[1].strip()
            color = parts[2].strip()
            born_on_date = datetime.strptime(parts[3].strip(), FileManager.DATE_FORMAT).date()
            molting = parts[4].strip().lower() == 'true'

            chicken = Chicken(id_val, name, color, born_on_date, 0, molting)
            chicken.set_age_in_years()
            return chicken
        except Exception:
            return None

    @staticmethod
    def clean(text):
        return text.replace(FileManager.SEPARATOR, ",")


class ChickenFarmApp:
    WIDTH = 60
    FILE_NAME = "chickens.csv"
    coop = None
    unsaved_changes = False

    @classmethod
    def main(cls):
        cls.load_at_start()
        option = -1

        while option != 0:
            cls.print_menu()
            option = cls.read_int(" Choose an option: ")

            if option == 1:
                cls.insert_chickens()
            elif option == 2:
                cls.save_chickens()
            elif option == 3:
                cls.read_saved_chickens()
            elif option == 4:
                cls.show_coop()
            elif option == 5:
                cls.remove_chicken()
            elif option == 6:
                cls.walk_through_coop()
            elif option == 0:
                cls.exit_app()
            else:
                print(" [X] Invalid option, choose a number from the menu")

            if option != 0:
                cls.pause()

    @classmethod
    def print_menu(cls):
        print()
        cls.print_header()

        if cls.unsaved_changes:
            status = "unsaved changes (*)"
        else:
            status = "everything saved"

        print(f" Coop #{cls.coop.get_chicken_coop_id()}   |   chickens: {cls.coop.get_count()}/{cls.coop.get_capacity()}   |   {status}")
        cls.print_line('-')
        print("   [1]  Insert chickens")
        print("   [2]  Save chickens to file")
        print("   [3]  Read saved chickens")
        print("   [4]  Show chickens in the coop")
        print("   [5]  Remove a chicken")
        print("   [6]  Walk through the coop (next / reset)")
        print("   [0]  Exit")
        cls.print_line('-')

    @classmethod
    def pause(cls):
        print()
        input(" Press Enter to go back to the menu...")

    @classmethod
    def load_at_start(cls):
        try:
            cls.coop = FileManager.load(cls.FILE_NAME, 1)
            if cls.coop.is_empty():
                print(" No saved chickens yet, starting with an empty coop.")
            else:
                print(f" [OK] {cls.coop.get_count()} chickens loaded from {cls.FILE_NAME}")
        except Exception as e:
            cls.coop = ChickenCoop(1, [])
            print(f" [X] The file could not be read: {e}")

    @classmethod
    def insert_chickens(cls):
        cls.print_section("INSERT CHICKENS")
        add_another = True

        while add_another:
            if cls.coop.is_full():
                print(f" [X] The coop is full ({cls.coop.get_capacity()} chickens)")
                return

            print()
            print(f" Chicken #{cls.coop.get_count() + 1}")
            chicken = cls.read_chicken()
            cls.coop.add(chicken)
            cls.unsaved_changes = True
            print(f" [OK] {chicken.get_name()} was added to the coop ({cls.coop.get_count()}/{cls.coop.get_capacity()})")

            add_another = cls.ask_yes_no(" Add another chicken? (y/n): ")
        
        print(" Remember to save them with option [2]")

    @classmethod
    def save_chickens(cls):
        cls.print_section("SAVE CHICKENS TO FILE")
        try:
            FileManager.save(cls.coop, cls.FILE_NAME)
            cls.unsaved_changes = False
            print(f" [OK] {cls.coop.get_count()} chickens saved in:")
            print(f"      {FileManager.full_path(cls.FILE_NAME)}")
        except Exception as e:
            print(f" [X] The chickens could not be saved: {e}")

    @classmethod
    def read_saved_chickens(cls):
        if not FileManager.exists(cls.FILE_NAME):
            cls.print_section(f"SAVED CHICKENS ({cls.FILE_NAME})")
            print(" There is no saved file yet, use option [2] first")
            return
        
        try:
            saved_coop = FileManager.load(cls.FILE_NAME, cls.coop.get_chicken_coop_id())
            cls.print_chicken_table(saved_coop, f"SAVED CHICKENS ({cls.FILE_NAME})")
            if cls.unsaved_changes:
                print(" (*) The coop has changes that are not saved in the file yet")
        except Exception as e:
            print(f" [X] The file could not be read: {e}")

    @classmethod
    def show_coop(cls):
        cls.print_chicken_table(cls.coop, "CHICKENS IN THE COOP")
        print()
        print(f" coop --> {cls.coop}")

    @classmethod
    def remove_chicken(cls):
        cls.print_chicken_table(cls.coop, "REMOVE A CHICKEN")
        if cls.coop.is_empty():
            return

        id_val = cls.read_int(" Enter the id of the chicken to remove: ")
        before = cls.coop.get_count()
        cls.coop.remove(id_val)

        if cls.coop.get_count() < before:
            cls.unsaved_changes = True
            print(f" [OK] The chicken with id {id_val} was removed")
            print(" Remember to save the change with option [2]")
        else:
            print(f" [X] There is no chicken with id {id_val}")

    @classmethod
    def walk_through_coop(cls):
        cls.print_section("WALK THROUGH THE COOP")
        if cls.coop.is_empty():
            print(" The coop is empty")
            return

        cls.coop.reset_iteration()
        print(" resetIteration()  ->  we start at the first chicken")
        print(" [n] next()   [r] resetIteration()   [b] back to the menu")

        option = ""
        while option != "b":
            option = cls.read_text(" > ").lower()
            if option == "n":
                chicken = cls.coop.next()
                if chicken is not None:
                    print(f"   next()            ->  {chicken.get_name()} (id {chicken.get_id()})")
                else:
                    print("   next()            ->  null (no more chickens, press r)")
            elif option == "r":
                cls.coop.reset_iteration()
                print("   resetIteration()  ->  back to the first chicken")
            elif option == "b":
                break
            else:
                print("   [X] Use n, r or b")

    @classmethod
    def exit_app(cls):
        if cls.unsaved_changes:
            print()
            save = cls.ask_yes_no(" You have unsaved changes. Save them before leaving? (y/n): ")
            if save:
                cls.save_chickens()
        
        cls.print_section("GOODBYE")
        print(" Thanks for using the Chicken Farm App!")
        cls.print_line('=')

    @classmethod
    def print_chicken_table(cls, chicken_coop, title):
        cls.print_section(title)

        if chicken_coop.is_empty():
            print(" The coop is empty")
            return

        print(f" {'ID':<4}| {'NAME':<11}| {'COLOR':<11}| {'BORN':<11}| {'AGE':<4}| MOLT")
        print(" ----+------------+------------+------------+-----+-----")

        chicken_coop.reset_iteration()
        chicken = chicken_coop.next()
        while chicken is not None:
            molting = "yes" if chicken.is_molting() else "no"
            born_str = chicken.get_born_on_date().strftime("%Y-%m-%d")
            
            print(f" {chicken.get_id():<4}| {chicken.get_name()[:10]:<11}| {chicken.get_color()[:10]:<11}| {born_str:<11}| {chicken.get_age_in_years():<4}| {molting}")
            chicken = chicken_coop.next()

        print(f" Total: {chicken_coop.get_count()}/{chicken_coop.get_capacity()} chickens")

    @classmethod
    def read_chicken(cls):
        id_val = cls.read_int("   id .................. ")
        while cls.coop.exists(id_val):
            print(f"   [X] There is already a chicken with id {id_val}")
            id_val = cls.read_int("   id .................. ")
            
        name = cls.read_required_text("   name ................ ")
        color = cls.read_required_text("   color ............... ")
        born_on_date = cls.read_date()
        molting = cls.ask_yes_no("   molting? (y/n) ...... ")

        chicken = Chicken(id_val, name, color, born_on_date, 0, molting)
        chicken.set_age_in_years()
        return chicken

    @classmethod
    def read_date(cls):
        while True:
            year = cls.read_int("   birth year .......... ")
            month = cls.read_int("   birth month (1-12) .. ")
            day = cls.read_int("   birth day ........... ")

            try:
                d = date(year, month, day)
                if d > date.today():
                    print("   [X] The chicken can not be born in the future, try again")
                else:
                    return d
            except ValueError:
                print("   [X] That date does not exist, try again")

    @classmethod
    def read_text(cls, message):
        return input(message).strip()

    @classmethod
    def read_required_text(cls, message):
        text = cls.read_text(message)
        while not text:
            print("   [X] This field can not be empty")
            text = cls.read_text(message)
        return text

    @classmethod
    def read_int(cls, message):
        while True:
            text = cls.read_text(message)
            try:
                return int(text)
            except ValueError:
                print("   [X] Please enter a whole number")

    @classmethod
    def ask_yes_no(cls, message):
        while True:
            answer = cls.read_text(message).lower()
            if answer == 'y':
                return True
            if answer == 'n':
                return False
            print("   [X] Please answer y or n")

    @classmethod
    def print_header(cls):
        cls.print_box_line('=')
        cls.print_box_text("Daniel Sangoquiza CHICKEN FARM APP")
        cls.print_box_text("version 3.0")
        cls.print_box_line('=')

    @classmethod
    def print_box_line(cls, character):
        print("+" + character * (cls.WIDTH - 2) + "+")

    @classmethod
    def print_box_text(cls, text):
        inside = cls.WIDTH - 2
        left = (inside - len(text)) // 2
        right = inside - len(text) - left
        print("|" + ' ' * left + text + ' ' * right + "|")

    @classmethod
    def print_section(cls, title):
        text = f" {title} "
        left = (cls.WIDTH - len(text)) // 2
        right = cls.WIDTH - len(text) - left
        print()
        print('-' * left + text + '-' * right)

    @classmethod
    def print_line(cls, character):
        print(character * cls.WIDTH)

if __name__ == "__main__":
    ChickenFarmApp.main()