import json

from model.vehicle_part import VehiclePart


class VehiclePartFileManager:

    def save(self, parts: list[VehiclePart], path: str) -> None:
        data = [part.to_dict() for part in parts]
        with open(path, "w", encoding="utf-8") as file:
            json.dump(data, file, indent=2, ensure_ascii=False)

    def load(self, path: str) -> list[VehiclePart]:
    
        with open(path, encoding="utf-8") as file:
            data = json.load(file)
        if not isinstance(data, list):
            raise ValueError("The JSON file must contain a list of parts")
        return [VehiclePart.from_dict(item) for item in data]
