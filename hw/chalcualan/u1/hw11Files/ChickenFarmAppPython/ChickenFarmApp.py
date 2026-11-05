from Chicken import Chicken
from ChickenCoop import ChickenCoop

def ChickenFarmApp():
    chickens = [None] * 100
    coop = ChickenCoop(1, chickens)
    option = 0
    
    while option != 5:
        print("Hello, Isaac Acurio Chicken Farm App")
        print("------------------------------------------")
        print("-----------------Welcome -----------------")
        print("----------What you will do today----------")
        print("1. Add chicken")
        print("2. Save Chicken Coop")
        print("3. Remove chicken")
        print("4. Show Chicken Coop")
        print("5. Exit")
        print("Press any Option----->")
        try:
            option = int(input())
            match option:
                case 1:
                    coop.add()
                    
                case 2:
                    coop.saveChickenCoop()
                    
                case 3:
                    coop.remove()
                    
                case 4:
                    coop.showChickenCoop()

                case 5:
                    print("Exiting Chicken Farm App...")

                case _:
                    print("Invalid option. Try again.")

        except ValueError:
            print("Please enter a valid number.")
if __name__ == "__main__":
    ChickenFarmApp()