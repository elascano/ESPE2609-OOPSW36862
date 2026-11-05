from pathlib import Path

from model.vehicle_part import VehiclePart
from model.vehicle_part_catalog import VehiclePartCatalog
from persistence.vehicle_part_file_manager import VehiclePartFileManager

OPTION_REGISTER_PART = "1"
OPTION_LIST_PARTS = "2"
OPTION_FIND_PART = "3"
OPTION_SAVE_JSON = "4"
OPTION_LOAD_JSON = "5"
OPTION_EXIT = "0"

DATA_FILE = Path(__file__).resolve().parent.parent / "data" / "vehicle_parts.json"


class Menu:

    def __init__(self):
        self._catalog = VehiclePartCatalog()
        self._file_manager = VehiclePartFileManager()
        self._actions = {
            OPTION_REGISTER_PART: self._register_part,
            OPTION_LIST_PARTS: self._list_parts,
            OPTION_FIND_PART: self._find_part,
            OPTION_SAVE_JSON: self._save_parts,
            OPTION_LOAD_JSON: self._load_parts,
            OPTION_EXIT: self._say_goodbye,
        }

    def run(self) -> None:
        option = ""
        while option != OPTION_EXIT:
            self._show_options()
            option = input("Choose an option: ").strip()
            self._execute(option)

    def _show_options(self) -> None:
        print("\n=== VEHICLE PARTS ===")
        print(f"{OPTION_REGISTER_PART}. Register part")
        print(f"{OPTION_LIST_PARTS}. List parts")
        print(f"{OPTION_FIND_PART}. Find part by id")
        print(f"{OPTION_SAVE_JSON}. Save parts to JSON")
        print(f"{OPTION_LOAD_JSON}. Load parts from JSON")
        print(f"{OPTION_EXIT}. Exit")

    def _execute(self, option: str) -> None:
        action = self._actions.get(option)
        if action is None:
            print("Invalid option.")
            return
        try:
            action()
        except (ValueError, OSError) as error:
            print(f"Error: {error}")

    def _register_part(self) -> None:
        part_id = input("Id: ")
        name = input("Name: ")
        brand = input("Brand: ")
        price = self._read_price()
        self._catalog.add(VehiclePart(part_id, name, brand, price))
        print("Part registered.")

    def _read_price(self) -> float:
        text = input("Price: ")
        try:
            return float(text)
        except ValueError as error:
            raise ValueError("Price must be a number") from error

    def _list_parts(self) -> None:
        parts = self._catalog.find_all()
        if not parts:
            print("There are no parts.")
            return
        for part in parts:
            print(part)

    def _find_part(self) -> None:
        part_id = input("Id: ").strip()
        print(self._catalog.find_by_id(part_id))

    def _save_parts(self) -> None:
        self._file_manager.save(self._catalog.find_all(), DATA_FILE)
        print(f"Saved to {DATA_FILE.name}")

    def _load_parts(self) -> None:
        parts = self._file_manager.load(DATA_FILE)
        self._catalog.replace_all(parts)
        print(f"Loaded {len(parts)} parts from {DATA_FILE.name}")

    def _say_goodbye(self) -> None:
        print("Goodbye!")
