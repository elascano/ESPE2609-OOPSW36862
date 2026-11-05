import json
import os


class Truck:
    def __init__(self, id, brand, capacity_tons, year):
        self.id = id
        self.brand = brand
        self.capacity_tons = capacity_tons
        self.year = year

    def __str__(self):
        return f"Truck{{id={self.id}, brand={self.brand}, capacity_tons={self.capacity_tons}, year={self.year}}}"

    def saveInformation(self, trucks, file_path):
        lista_diccionarios = []
        for t in trucks:
            lista_diccionarios.append({
                "id": t.id,
                "brand": t.brand,
                "capacity_tons": t.capacity_tons,
                "year": t.year
            })
        
        with open(file_path, "w", encoding="utf-8") as archivo:
            json.dump(lista_diccionarios, archivo, indent=4, ensure_ascii=False)
        print(f"Data saved to {file_path}")

    def loadInformation(self, file_path):
        lista_objetos = []
        if os.path.exists(file_path):
            with open(file_path, "r", encoding="utf-8") as archivo:
                datos = json.load(archivo)
                for d in datos:
                    lista_objetos.append(Truck(d["id"], d["brand"], d["capacity_tons"], d["year"]))
            print(f"Data loaded from {file_path}")
        return lista_objetos


class TruckManagement:
    def __init__(self):
        self.id = "Admin"
        self.trucks = []
        self.file_path = "trucks.json"

    def menu(self):
        truck_aux = Truck("", "", 0, 0)
        self.trucks = truck_aux.loadInformation(self.file_path)

        while True:
            print("\n=== TRUCK MANAGEMENT ===")
            print("1. Create Truck")
            print("2. Show All")
            print("3. Save to JSON")
            print("4. Exit")

            opcion = input("Select an option: ")

            if opcion == "1":
                id_camion = input("Enter ID: ").strip()
                if not id_camion:
                    print("Error: ID is mandatory.")
                    continue
                brand = input("Enter brand: ")
                try:
                    capacity = int(input("Enter capacity in tons: "))
                    year = int(input("Enter year of creation: "))
                    
                    nuevo_camion = Truck(id_camion, brand, capacity, year)
                    self.trucks.append(nuevo_camion)
                    print("Truck added ")
                except ValueError:
                    print("Error: invalid capacity or year.")

            elif opcion == "2":
                if not self.trucks:
                    print("Empty list.")
                else:
                    for t in self.trucks:
                        print(t)

            elif opcion == "3":
                truck_aux.saveInformation(self.trucks, self.file_path)

            elif opcion == "4":
                truck_aux.saveInformation(self.trucks, self.file_path)
                print("Exiting...")
                break
            else:
                print("Invalid option.")


if __name__ == "__main__":
    app = TruckManagement()
    app.menu()