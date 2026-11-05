from model.notebook import notebook
from view import json_manage


def ask_integer(message):

    while True:
        try:
            return int(input(message))
        except ValueError:
            print("Please enter a valid number.")


def ask_float(message):

    while True:
        try:
            return float(input(message))
        except ValueError:
            print("Please enter a valid number.")


def enter_data(notebook_list):
    amount = ask_integer("How many notebooks do you want to enter? ")
    for _ in range(amount):
        notebook_id = ask_integer("ID: ")
        if any(n.id == notebook_id for n in notebook_list):
            print("That ID already exists, record skipped.")
            continue
        brand = input("Brand: ")
        ram_gb = ask_integer("RAM (GB): ")
        price = ask_float("Price: ")
        notebook_list.append(Notebook(notebook_id, brand, ram_gb, price))
    json_manager.save(notebook_list)
    print("Data saved to the JSON file.")


def show_notebooks(notebook_list):
    if not notebook_list:
        print("There are no records.")
    for notebook in notebook_list:
        print(notebook)


def main():
    notebook_list = json_manager.load() 
    while True:
        print("\n1. Create records")
        print("2. Read from JSON file")
        print("3. Exit")
        option = input("Option: ")
        if option == "1":
            enter_data(notebook_list)
        elif option == "2":
            show_notebooks(json_manager.load())
        elif option == "3":
            print("Goodbye!")
            break
        else:
            print("Invalid option.")


if __name__ == "__main__":
    main()