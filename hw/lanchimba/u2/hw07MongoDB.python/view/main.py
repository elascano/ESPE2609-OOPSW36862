"""Interactive console menu for FarmAnimal CRUD operations with MongoDB Atlas."""

from datetime import date
from pathlib import Path
import sys

# Allow executing directly as `python view/main.py` or as `python -m view.main`
PROJECT_ROOT = Path(__file__).resolve().parent.parent
if str(PROJECT_ROOT) not in sys.path:
    sys.path.insert(0, str(PROJECT_ROOT))

from pymongo.errors import ConnectionFailure, PyMongoError

from config.database import DatabaseConnection
from controller.farm_animal_repository import FarmAnimalRepository
from model.farm_animal import FarmAnimal


def prompt_int(label: str) -> int:
    while True:
        raw = input(label).strip()
        try:
            return int(raw)
        except ValueError:
            print("  [Error] Please enter a valid integer.")


def prompt_float(label: str, allow_empty: bool = False) -> float | None:
    while True:
        raw = input(label).strip()
        if allow_empty and raw == "":
            return None
        try:
            value = float(raw)
            if value < 0:
                print("  [Error] Value must be non-negative.")
                continue
            return value
        except ValueError:
            print("  [Error] Please enter a valid decimal number.")


def prompt_date(label: str, allow_empty: bool = False) -> date | None:
    while True:
        raw = input(label).strip()
        if allow_empty and raw == "":
            return None
        try:
            return date.fromisoformat(raw)
        except ValueError:
            print("  [Error] Use ISO format YYYY-MM-DD (e.g., 2024-05-15).")


def prompt_bool(label: str, allow_empty: bool = False) -> bool | None:
    while True:
        raw = input(label).strip().lower()
        if allow_empty and raw == "":
            return None
        if raw in ("s", "si", "sí", "y", "yes", "true", "1"):
            return True
        if raw in ("n", "no", "false", "0"):
            return False
        print("  [Error] Please answer with 's' (yes) or 'n' (no).")


def handle_insert(repo: FarmAnimalRepository) -> None:
    print("\n--- Insertar Nuevo Animal ---")
    animal_id = prompt_int("ID (entero único): ")
    animal_type = input("Tipo (Chicken, Cow, Pig, Sheep, etc.): ").strip() or "Chicken"
    breed = input("Raza: ").strip() or "Criollo"
    born_on = prompt_date("Fecha de nacimiento (YYYY-MM-DD): ")
    assert born_on is not None
    weight_kg = prompt_float("Peso en kg: ")
    assert weight_kg is not None
    is_active = prompt_bool("¿Está activo/en producción? (s/n): ")
    assert is_active is not None

    animal = FarmAnimal(
        animal_id=animal_id,
        animal_type=animal_type,
        breed=breed,
        born_on=born_on,
        weight_kg=weight_kg,
        is_active=is_active,
    )
    try:
        inserted_oid = repo.insert_one(animal)
        print(f"  [OK] Animal insertado exitosamente. MongoDB _id: {inserted_oid}")
    except ValueError as exc:
        print(f"  [Aviso] {exc}")


def handle_list_all(repo: FarmAnimalRepository) -> None:
    print("\n--- Lista de Animales Registrados ---")
    animals = repo.find_all()
    if not animals:
        print("  No hay animales registrados en la colección.")
        return
    for animal in animals:
        print(f"  * {animal}")
    print(f"Total: {len(animals)} registro(s).")


def handle_find_by_id(repo: FarmAnimalRepository) -> None:
    print("\n--- Buscar Animal por ID ---")
    animal_id = prompt_int("Ingrese el ID a buscar: ")
    animal = repo.find_by_id(animal_id)
    if animal is None:
        print(f"  [Aviso] No se encontró ningún animal con id={animal_id}.")
    else:
        print(f"  [Encontrado] {animal}")


def handle_update(repo: FarmAnimalRepository) -> None:
    print("\n--- Actualizar Animal por ID ---")
    animal_id = prompt_int("Ingrese el ID del animal a actualizar: ")
    existing = repo.find_by_id(animal_id)
    if existing is None:
        print(f"  [Aviso] No existe un animal con id={animal_id}.")
        return

    print(f"  Actual: {existing}")
    print("  (Presione Enter para mantener el valor actual)")

    updates: dict[str, object] = {}
    new_type = input(f"Nuevo tipo [{existing.get_animal_type()}]: ").strip()
    if new_type:
        updates["animal_type"] = new_type

    new_breed = input(f"Nueva raza [{existing.get_breed()}]: ").strip()
    if new_breed:
        updates["breed"] = new_breed

    new_born = prompt_date(
        f"Nueva fecha de nacimiento [{existing.get_born_on().isoformat()}]: ",
        allow_empty=True,
    )
    if new_born is not None:
        updates["born_on"] = new_born

    new_weight = prompt_float(
        f"Nuevo peso en kg [{existing.get_weight_kg()}]: ",
        allow_empty=True,
    )
    if new_weight is not None:
        updates["weight_kg"] = new_weight

    new_active = prompt_bool(
        f"¿Activo? (s/n) [{'s' if existing.get_is_active() else 'n'}]: ",
        allow_empty=True,
    )
    if new_active is not None:
        updates["is_active"] = new_active

    if not updates:
        print("  [Aviso] No se realizaron cambios.")
        return

    if repo.update_by_id(animal_id, updates):
        print("  [OK] Animal actualizado correctamente.")
        print(f"  Nuevo estado: {repo.find_by_id(animal_id)}")
    else:
        print("  [Aviso] No se pudo actualizar el registro.")


def handle_delete(repo: FarmAnimalRepository) -> None:
    print("\n--- Eliminar Animal por ID ---")
    animal_id = prompt_int("Ingrese el ID del animal a eliminar: ")
    if repo.delete_by_id(animal_id):
        print(f"  [OK] Animal con id={animal_id} eliminado correctamente.")
    else:
        print(f"  [Aviso] No se encontró ningún animal con id={animal_id}.")


def print_menu() -> None:
    print("\n============================================================")
    print("   ESPE Animal Farm App - MongoDB Atlas CRUD (Python)")
    print("============================================================")
    print("  1. Insertar animal")
    print("  2. Listar todos los animales")
    print("  3. Buscar animal por ID")
    print("  4. Actualizar animal por ID")
    print("  5. Eliminar animal por ID")
    print("  0. Salir")
    print("------------------------------------------------------------")


def main() -> None:
    try:
        with DatabaseConnection() as db_conn:
            collection = db_conn.get_collection()
            repo = FarmAnimalRepository(collection)
            print(
                f"[OK] Conectado a MongoDB Atlas -> DB: '{db_conn.get_db_name()}', "
                f"Colección: '{db_conn.get_collection_name()}'"
            )

            while True:
                print_menu()
                option = input("Seleccione una opción: ").strip()

                if option == "1":
                    handle_insert(repo)
                elif option == "2":
                    handle_list_all(repo)
                elif option == "3":
                    handle_find_by_id(repo)
                elif option == "4":
                    handle_update(repo)
                elif option == "5":
                    handle_delete(repo)
                elif option == "0":
                    print("Cerrando conexión con MongoDB Atlas. ¡Hasta luego!")
                    break
                else:
                    print("  [Error] Opción inválida. Intente nuevamente.")
    except (ConnectionFailure, ValueError, PyMongoError) as exc:
        print(f"\n[ERROR DE CONEXIÓN / BASE DE DATOS] {exc}")
        sys.exit(1)
    except KeyboardInterrupt:
        print("\nEjecución interrumpida por el usuario.")


if __name__ == "__main__":
    main()
