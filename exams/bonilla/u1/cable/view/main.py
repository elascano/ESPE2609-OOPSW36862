import sys
import os

sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), "../../../../..")))

from ec.edu.espe.cable.model.cable import Cable
from ec.edu.espe.cable.utils.json_manager import CableManager

def main():
    manager = CableManager("cables.json")
    cables_list: list[Cable] = manager.load_cables()

    while True:
        print("\n=== CABLE MANAGEMENT SYSTEM ===")
        print("1. Create and save a new Cable")
        print("2. Read and display Cables from JSON")
        print("3. Exit")
        option = input("Select an option: ")

        if option == "1":
            try:
                cable_id = int(input("Enter Cable ID: "))
                cable_type = input("Enter Cable type (e.g., HDMI, UTP, USB-C): ")
                length = float(input("Enter length in meters (e.g., 1.5): "))
                price = float(input("Enter price (e.g., 12.50): "))

                new_cable = Cable(cable_id, cable_type, length, price)
                cables_list.append(new_cable)
                manager.save_cables(cables_list)
                print("Cable successfully saved to cables.json.")
            except ValueError:
                print("Error: Please enter valid numeric values for ID, length, and price.")

        elif option == "2":
            cables_list = manager.load_cables()
            print("\n--- CABLE LIST FROM JSON ---")
            if not cables_list:
                print("No cables registered.")
            else:
                for cable in cables_list:
                    print(cable)

        elif option == "3":
            print("Exiting program...")
            break
        else:
            print("Invalid option.")

if __name__ == "__main__":
    main()