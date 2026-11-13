import sys
import os

sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), "..")))

from controller.notebook_controller import NotebookController

def display_menu():
    print("\n=== NOTEBOOK MANAGEMENT SYSTEM ===")
    print("1. Add Notebook")
    print("2. Display All Notebooks")
    print("3. Exit")
    return input("Select an option: ")

def get_notebook_data():
    id = int(input("Enter ID: "))
    brand = input("Enter Brand: ")
    ram_gb = int(input("Enter RAM (GB): "))
    price = float(input("Enter Price: "))
    return id, brand, ram_gb, price

def display_notebooks(notebooks):
    if not notebooks:
        print("No notebooks found.")
        return
    print("\n--- Notebook List ---")
    for notebook in notebooks:
        print(f"ID: {notebook.id} | Brand: {notebook.brand} | RAM: {notebook.ram_gb}GB | Price: ${notebook.price:.2f}")

def run_app():
    controller = NotebookController("notebooks.json")
    while True:
        choice = display_menu()
        if choice == "1":
            try:
                id, brand, ram_gb, price = get_notebook_data()
                controller.add_notebook(id, brand, ram_gb, price)
                print("Notebook created and saved to JSON successfully!")
            except ValueError:
                print("Invalid input. Operation canceled.")
        elif choice == "2":
            notebooks = controller.get_all_notebooks()
            display_notebooks(notebooks)
        elif choice == "3":
            print("Exiting system.")
            break
        else:
            print("Invalid option. Try again.")

run_app()