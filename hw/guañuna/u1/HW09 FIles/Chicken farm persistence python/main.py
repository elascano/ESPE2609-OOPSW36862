from chickencoop import ChickenCoop
from chicken import Chicken
from persistencia import guardar, cargar
from egg import Egg
from poop import Poop
from datetime import datetime

def main():
    print("Chicken Farm App Cristopher Guanuna")
    print("===================================")

    chicken = Chicken(1, "Lucy", "brown and white", datetime(2020, 8, 10), True)
    egg = Egg(1, 'H')
    poop = Poop(1, "brown", 'S')
    chicken1 = Chicken(2, "Renata", "White", datetime(2025, 12, 20), False)

    chicken1.name = "Edith"

    local_age = chicken.compute_age_in_years()
    local_age1 = chicken1.compute_age_in_years()

    chicken.age_in_years = local_age
    chicken1.age_in_years = local_age1

    print(f"chicken --> {chicken}")
    print(f"egg --> {egg}")
    print(f"poop --> {poop}")
    print(f"chicken 2 --> {chicken1}")

    chicken.do_stuff()
    print(f"I am printing the name of my chicken --> {chicken.name}")

    # Simulación de arreglo de 10 elementos
    chickens_array = [None] * 10
    chickens_array[0] = Chicken(3, "Pepita", "black", datetime(2020, 3, 2), True)
    chickens_array[1] = chicken

    for c in chickens_array:
        print(f"chickens --> {c}")

    for i in range(10):
        chickens_array[i] = Chicken(i, f"chicken{i+1}", "white", datetime(2020 + i, 4, 3), True)
        age_for_the_loop = chickens_array[i].compute_age_in_years()
        chickens_array[i].age_in_years = age_for_the_loop

    for c in chickens_array:
        print(f"chickens --> {c}")

    print("Cargando datos del corral...")
    chicken_coop = cargar()
    
    if chicken_coop is None:
        chicken_coop = ChickenCoop()

    print("\n=== INITIALIZED CHICKENS ===")
    chicken_coop.reset_iteration()
    while True:
        item = chicken_coop.next_chicken()
        if item is None:
            break
        print(item)
    print("============================")

    # Casteo explícito e implícito (Simulado)
    addend1 = 40000
    addend2 = float(addend1)
    addend1 = int(addend2)
    print(f"addend1 --> {addend1}")
    print(f"addend2 --> {addend2}")

    option = 0
    while option != 5:
        print("\n=== CHICKEN COOP MENU ===")
        print("1. Add chicken")
        print("2. Remove chicken")
        print("3. Show all chickens")
        print("4. Save data")
        print("5. Exit")
        
        entrada = input("Choose an option: ")
        try:
            option = int(entrada)
        except ValueError:
            print("Invalid option. Enter a number:")
            continue

        if option == 1:
            chicken_coop.add()
        elif option == 2:
            chicken_coop.remove()
        elif option == 3:
            print("\n=== CHICKENS IN THE COOP ===")
            chicken_coop.reset_iteration()
            while True:
                item = chicken_coop.next_chicken()
                if item is None:
                    break
                print(item)
            print("============================")
        elif option == 4:
            guardar(chicken_coop)
        elif option == 5:
            print("Saving data and exiting menu...")
            guardar(chicken_coop)
        else:
            print("Invalid option.")

if __name__ == "__main__":
    main()