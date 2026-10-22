import json
import os
from datetime import datetime
from chicken import Chicken
from chicken_coop import ChickenCoop

FILENAME = "chickens.json"

def save_coop_to_json(coop: ChickenCoop, filename: str = FILENAME) -> None:
    data = []
    coop.reset_iteration()
    chicken = coop.next()
    while chicken is not None:
        data.append({
            "id": chicken.id,
            "name": chicken.name,
            "color": chicken.color,
            "born_on_date": chicken.born_on_date.strftime("%Y-%m-%d"),
            "is_molting": chicken.is_molting
        })
        chicken = coop.next()
    
    with open(filename, "w", encoding="utf-8") as file:
        json.dump(data, file, indent=4)

def load_coop_from_json(coop: ChickenCoop, filename: str = FILENAME) -> None:
    if not os.path.exists(filename):
        return

    try:
        with open(filename, "r", encoding="utf-8") as file:
            data = json.load(file)
            for item in data:
                born_date = datetime.strptime(item["born_on_date"], "%Y-%m-%d")
                chicken = Chicken(
                    chicken_id=item["id"],
                    name=item["name"],
                    color=item["color"],
                    born_on_date=born_date,
                    is_molting=item["is_molting"]
                )
                coop.add(chicken)
    except (json.JSONDecodeError, KeyError, ValueError) as e:
        print(f"Advertencia: No se pudieron cargar datos desde {filename}: {e}")