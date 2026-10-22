from typing import List
from chicken import Chicken

class ChickenCoop:
    def __init__(self, coop_id: int):
        self.coop_id: int = coop_id
        self.chickens: List[Chicken] = []  

    def add_chicken(self, chicken: Chicken) -> None:
        """Agrega un objeto Chicken a la lista del gallinero."""
        self.chickens.append(chicken)
        print(f"🐔 Pollo '{chicken.name}' agregado al gallinero {self.coop_id}.")

    def remove_chicken(self, chicken_id: int) -> bool:
        """Remueve una gallina del gallinero buscando por su ID."""
        for chicken in self.chickens:
            if chicken.id == chicken_id:
                self.chickens.remove(chicken)
                print(f"❌ Pollo con ID {chicken_id} removido del gallinero.")
                return True
        print(f"⚠️ No se encontró el pollo con ID {chicken_id}.")
        return False

    def count_chickens(self) -> int:
        """Devuelve la cantidad total de pollos en el gallinero."""
        return len(self.chickens)

    def show_chickens(self) -> None:
        """Muestra la lista completa de pollos en el gallinero."""
        print(f"\n--- Lista de Pollos en el Gallinero {self.coop_id} ---")
        if not self.chickens:
            print("El gallinero está vacío.")
        else:
            for chicken in self.chickens:
                print(chicken)  