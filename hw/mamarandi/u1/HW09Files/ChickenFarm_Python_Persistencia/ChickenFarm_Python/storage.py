import json
from datetime import date
from pathlib import Path
from chicken import Chicken

DATA_FILE = Path(__file__).with_name("chickens.json")

def save_chickens(chickens):
    data = [{"id": c.getId(), "name": c.getName(), "color": c.getColor(),
             "bornOnDate": c.getBorn0nDate().isoformat(), "molting": c.isMolting()}
            for c in chickens if c is not None]
    DATA_FILE.write_text(json.dumps(data, ensure_ascii=False, indent=2), encoding="utf-8")
    print("Data saved in chickens.json")

def load_chickens():
    if not DATA_FILE.exists():
        return []
    try:
        data = json.loads(DATA_FILE.read_text(encoding="utf-8"))
        if not isinstance(data, list):
            raise ValueError("Expected a list")
        if len(data) > 10:
            raise ValueError("More than 10 chickens")
        result = []
        for item in data:
            c = Chicken(int(item["id"]), str(item["name"]), str(item["color"]),
                        date.fromisoformat(item["bornOnDate"]), item["molting"])
            if type(item["molting"]) is not bool:
                raise ValueError("Invalid molting")
            result.append(c)
        return result
    except (OSError, ValueError, TypeError, KeyError) as e:
        print(f"Could not read chickens.json: {e}")
        raise SystemExit(1)
