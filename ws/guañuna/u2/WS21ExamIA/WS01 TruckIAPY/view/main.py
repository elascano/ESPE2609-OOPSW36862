import sys
import os

sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

from model.truck import Truck
from model.truck_management import TruckManagement

def main():
    manager = TruckManagement()

    while True:
        print("\n--- GESTIÓN DE CAMIONES ---")
        print("1. Añadir Camión")
        print("2. Ver todos los camiones")
        print("3. Salir")
        
        option = input("Seleccione una opción: ")

        if option == '1':
            print("\n--- NUEVO CAMIÓN ---")
            id_input = input("Ingrese ID (ej. C001): ")
            brand = input("Ingrese Marca: ")
            
            try:
                capacity = int(input("Ingrese Capacidad (toneladas): "))
                year = int(input("Ingrese Año: "))
            except ValueError:
                print("Error: La capacidad y el año deben ser números enteros.")
                continue

            new_truck = Truck(id_input, brand, capacity, year)
            manager.add_truck(new_truck)
            print("¡Camión añadido y guardado correctamente!")

        elif option == '2':
            print("\n--- LISTA DE CAMIONES ---")
            trucks = manager.get_all_trucks()
            if not trucks:
                print("No hay camiones registrados.")
            else:
                for truck in trucks:
                    print(truck)

        elif option == '3':
            print("Saliendo del programa...")
            break
        else:
            print("Opción no válida. Intente de nuevo.")

if __name__ == "__main__":
    main()