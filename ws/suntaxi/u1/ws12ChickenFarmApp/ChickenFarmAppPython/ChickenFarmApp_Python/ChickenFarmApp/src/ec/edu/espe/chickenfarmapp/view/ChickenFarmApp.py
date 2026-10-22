from datetime import date
from ec.edu.espe.chickenfarmapp.model.Chicken import Chicken
from ec.edu.espe.chickenfarmapp.model.ChickenCoop import ChickenCoop
from ec.edu.espe.chickenfarmapp.model.ChickenFarmer import ChickenFarmer
from ec.edu.espe.chickenfarmapp.model.Egg import Egg
from ec.edu.espe.chickenfarmapp.model.Poop import Poop

def main():
    print("=== BIENVENIDO A CHICKEN FARM APP (PYTHON VERSION) ===")
    
    farmer = ChickenFarmer(id_farmer=1, name="Juan Perez", level="Senior")
    print(farmer)

    chicken1 = Chicken(1, "Lucas", "Blanco", 1, False, 101, date(2025, 5, 10))
    chicken2 = Chicken(2, "Maruja", "Rojo", 2, True, 101, date(2024, 3, 15))

    coop = ChickenCoop(id_coop=101)
    coop.add_chicken(chicken1)
    coop.add_chicken(chicken2)

    coop.eggs.append(Egg(1, "Blanco"))
    coop.poops.append(Poop(1, 45.5))

    print("\n--- Estado del Gallinero ---")
    print(coop)
    for c in coop.chickens:
        print(f" - {c}")

if __name__ == "__main__":
    main()
