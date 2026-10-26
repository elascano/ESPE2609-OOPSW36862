from datetime import datetime
from model.chicken import Chicken
from model.chicken_coop import ChickenCoop
from model.chicken_farmer import ChickenFarmer
from controller.json_manager import JsonManager

class ChickenFarmApp:
    @staticmethod
    def calculate_age(birth_date):
        today = datetime.today()
        age = today.year - birth_date.year
        if (today.month, today.day) < (birth_date.month, birth_date.day):
            age -= 1
        return max(0, age)

    @staticmethod
    def start():
        print("Josselyn Ulcuango's Chicken Farm App")
        print("=====================================")
        
        farmer = ChickenFarmer("Josselyn")

        loaded_coops = JsonManager.load_coops()
        if loaded_coops:
            farmer.set_coops(loaded_coops)
        else:
            default_coop = ChickenCoop(1)
            farmer.add_coop(default_coop)

        option = 0
        while option != 4:
            print("\n--- CHICKEN FARM MENU ---")
            print("1. Add chickens")
            print("2. Show chickens")
            print("3. Remove chicken")
            print("4. Exit")
            
            try:
                option = int(input("Select an option: "))
            except ValueError:
                print("Please enter a valid number.")
                continue

            if option == 1:
                try:
                    amount = int(input("How many chickens do you want to add?: "))
                except ValueError:
                    print("Please enter a valid number.")
                    continue

                current_coop = farmer.get_coops()[0]

                for i in range(amount):
                    print(f"\n--- Chicken #{i + 1} Data ---")
                    
                    generated_id = 1
                    for coop in farmer.get_coops():
                        for c in coop.get_chickens():
                            if c.ide >= generated_id:
                                generated_id = c.ide + 1
                    print(f"Assigned ID: {generated_id}")
                    
                    name = input("Enter Name: ")
                    color = input("Enter Color: ")
                    
                    born_on_date = None
                    while not born_on_date:
                        date_str = input("Enter Birth Date (yyyy-MM-dd): ")
                        try:
                            born_on_date = datetime.strptime(date_str, "%Y-%m-%d")
                        except ValueError:
                            print("Invalid format. Please use yyyy-MM-dd.")
                    
                    age = ChickenFarmApp.calculate_age(born_on_date)
                    
                    molting_input = input("Is molting? (true/false): ").strip().lower()
                    is_molting = molting_input == "true"

                    chicken = Chicken(generated_id, name, color, born_on_date, age, is_molting)
                    current_coop.add_chicken(chicken)
                    
                    print(f"Chicken '{name}' added successfully!")

                JsonManager.save_coops(farmer.get_coops())
                print("\nAll chickens have been registered successfully!")

            elif option == 2:
                print("\n--- List of Chickens in the Farm ---")
                has_chickens = False
                for coop in farmer.get_coops():
                    for chicken in coop.get_chickens():
                        print(chicken)
                        has_chickens = True
                if not has_chickens:
                    print("No chickens registered yet.")

            elif option == 3:
                try:
                    target_id = int(input("Enter ID of the chicken you want to remove: "))
                except ValueError:
                    print("Please enter a valid ID number.")
                    continue

                removed = False
                for coop in farmer.get_coops():
                    chickens = coop.get_chickens()
                    for j, chicken in enumerate(chickens):
                        if chicken.ide == target_id:
                            chickens.pop(j)
                            removed = True
                            break
                    if removed:
                        break

                if removed:
                    JsonManager.save_coops(farmer.get_coops())
                    print(f"Chicken with ID {target_id} has been successfully removed.")
                else:
                    print(f"Chicken with ID {target_id} was not found.")

            elif option == 4:
                print("\nExiting Chicken Farm App. Goodbye, Josselyn!")
            else:
                print("Invalid option. Please choose between 1 and 4.")