from typing import List
from chicken_coop import ChickenCoop

class ChickenFarmer:
    def __init__(self, name: str):
        self.name: str = name
        self.coops: List[ChickenCoop] = []  

    def add_coop(self, coop: ChickenCoop) -> None:
        """Asigna un gallinero al granjero."""
        self.coops.append(coop)
        print(f"🏠 Gallinero {coop.coop_id} asignado al granjero {self.name}.")

    def get_total_chickens(self) -> int:
        """Suma el total de pollos en todos los gallineros que maneja el granjero."""
        total = 0
        for coop in self.coops:
            total += coop.count_chickens()  
        return total

    def summary(self) -> None:
        """Muestra un resumen de la granja administrada."""
        print(f"\n==========================================")
        print(f"👨‍🌾 Granjero: {self.name}")
        print(f"🏠 Total Gallineros: {len(self.coops)}")
        print(f"🐔 Total Pollos en la Granja: {self.get_total_chickens()}")
        print(f"==========================================")