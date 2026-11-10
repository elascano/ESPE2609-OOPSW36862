from model.vehicle_part import VehiclePart


class VehiclePartCatalog:

    def __init__(self):
        self._parts: list[VehiclePart] = []

    def add(self, part: VehiclePart) -> None:
        if self.contains(part.part_id):
            raise ValueError(f"Part already exists: {part.part_id}")
        self._parts.append(part)

    def contains(self, part_id: str) -> bool:
        for part in self._parts:
            if part.part_id == part_id:
                return True
        return False

    def find_by_id(self, part_id: str) -> VehiclePart:
        for part in self._parts:
            if part.part_id == part_id:
                return part
        raise ValueError(f"Part not found: {part_id}")

    def find_all(self) -> list[VehiclePart]:
        return list(self._parts)

    def replace_all(self, parts: list[VehiclePart]) -> None:
        unique_ids = {part.part_id for part in parts}
        if len(unique_ids) != len(parts):
            raise ValueError("The file contains repeated ids")
        self._parts = list(parts)
