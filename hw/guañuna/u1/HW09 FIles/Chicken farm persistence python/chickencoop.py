from chicken import Chicken
from datetime import datetime

class ChickenCoop:
    def __init__(self):
        self.chicken_coop_id = 1
        self.chickens = []
        self.iteration_index = 0

        # Gallinas por defecto
        self.chickens.append(Chicken(1, "Lucy", "black", datetime.now(), False))
        self.chickens.append(Chicken(2, "Alejandra", "white", datetime.now(), False))

    def add(self):
        if len(self.chickens) >= 10:
            print("The chicken coop is full.")
            return

        print("\n--- Add Chicken ---")
        
        # 1. Validar ID (Límite de 1 a 10 y que no esté duplicado)
        id_valido = False
        id_ingresado = -1
        while not id_valido:
            entrada = input("Enter chicken ID: ")
            try:
                id_ingresado = int(entrada)
                
                # --- NUEVA VALIDACIÓN: Límite de 1 a 10 ---
                if id_ingresado < 1 or id_ingresado > 10:
                    print(">> Error: ID must be between 1 and 10 (coop capacity).")
                    continue # Vuelve a pedir el ID
                # ------------------------------------------

                existe = any(pollo.id == id_ingresado for pollo in self.chickens)
                
                if existe:
                    print(f">> Error: A chicken with this ID ({id_ingresado}) already exists.")
                    print("--- Registered Chickens ---")
                    for pollo in self.chickens:
                        print(f"ID: {pollo.id} | Name: {pollo.name}")
                    print("---------------------------")
                    print("Please try another ID.")
                else:
                    id_valido = True
            except ValueError:
                print(">> Error: ID must be a valid number.")

        # 2. Validar Nombre
        name = ""
        while not name.strip():
            name = input("Enter chicken name: ")
            if not name.strip():
                print(">> Error: Name cannot be empty.")
        name = name.strip()

        # 3. Validar Color
        color = ""
        while not color.strip():
            color = input("Enter chicken color: ")
            if not color.strip():
                print(">> Error: Color cannot be empty.")
        color = color.strip()

        # 4. Validar Molting
        molting = False
        molting_valido = False
        while not molting_valido:
            molting_str = input("Is the chicken molting? (true/false): ").strip().lower()
            if molting_str in ["true", "false"]:
                molting = (molting_str == "true")
                molting_valido = True
            else:
                print(">> Error: Please enter 'true' or 'false'.")

        # 5. Validar Fecha
        print("\nEnter the birth date of the chicken:")
        
        year = 0
        while True:
            entrada = input("Year: ")
            try:
                year = int(entrada)
                if 2000 < year <= 2026: 
                    break
                else:
                    print(">> Error: Please enter a realistic year (e.g., > 2000).")
            except ValueError:
                print(">> Error: Year must be a valid number.")

        month = 0
        while True:
            entrada = input("Month (1-12): ")
            try:
                month = int(entrada)
                if 1 <= month <= 12:
                    break
                else:
                    print(">> Error: Month must be between 1 and 12.")
            except ValueError:
                print(">> Error: Month must be a valid number.")

        day = 0
        while True:
            entrada = input("Day (1-31): ")
            try:
                day = int(entrada)
                if 1 <= day <= 31:
                    break
                else:
                    print(">> Error: Day must be between 1 and 31.")
            except ValueError:
                print(">> Error: Day must be a valid number.")

        # En Python datetime maneja los meses del 1 al 12 directamente
        born_on_date = datetime(year, month, day)

        chicken = Chicken(id_ingresado, name, color, born_on_date, molting)
        self.chickens.append(chicken)
        print("Chicken added successfully.")

    def remove(self):
        if not self.chickens:
            print("There are no chickens to remove.")
            return

        print("\n=== CHICKENS IN THE COOP ===")
        for pollo in self.chickens:
            print(pollo)
        print("============================")

        id_a_buscar = -1
        id_valido = False
        while not id_valido:
            entrada = input("Enter the ID of the chicken to remove: ").strip()
            try:
                id_a_buscar = int(entrada)
                id_valido = True
            except ValueError:
                print(">> Error: The ID must be a valid number.")

        # Buscar la gallina y su posición
        position = -1
        for i, pollo in enumerate(self.chickens):
            if pollo.id == id_a_buscar:
                position = i
                break

        if position == -1:
            print(f">> Error: No chicken found with ID {id_a_buscar}.")
            return

        # En Python no hay que "mover" los elementos, pop() lo hace solo
        self.chickens.pop(position)
        self.iteration_index = 0
        print(f"Chicken with ID {id_a_buscar} removed successfully.")

    def reset_iteration(self):
        self.iteration_index = 0

    def next_chicken(self):
        if self.iteration_index < len(self.chickens):
            pollo = self.chickens[self.iteration_index]
            self.iteration_index += 1
            return pollo
        return None

    def to_dict(self):
        return {
            "chickenCoopId": self.chicken_coop_id,
            "chickens": [pollo.to_dict() for pollo in self.chickens],
            "currentIndex": len(self.chickens),
            "iterationIndex": self.iteration_index
        }

    @classmethod
    def from_dict(cls, data):
        coop = cls()
        coop.chicken_coop_id = data.get("chickenCoopId", 1)
        coop.chickens = [Chicken.from_dict(c) for c in data.get("chickens", []) if c is not None]
        coop.iteration_index = data.get("iterationIndex", 0)
        return coop