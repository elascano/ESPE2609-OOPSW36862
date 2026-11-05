class VehiclePart:
    def __init__(self, part_id: str, name: str, brand: str, price: float):
        self._part_id = self._require_text(part_id, "Id")
        self.name = name
        self.brand = brand
        self.price = price

    @property
    def part_id(self) -> str:
        return self._part_id

    @property
    def name(self) -> str:
        return self._name

    @name.setter
    def name(self, value: str) -> None:
        self._name = self._require_text(value, "Name")

    @property
    def brand(self) -> str:
        return self._brand

    @brand.setter
    def brand(self, value: str) -> None:
        self._brand = self._require_text(value, "Brand")

    @property
    def price(self) -> float:
        return self._price

    @price.setter
    def price(self, value: float) -> None:
        if not isinstance(value, (int, float)) or value <= 0:
            raise ValueError("Price must be a positive number")
        self._price = float(value)

    def to_dict(self) -> dict:
        return {
            "id": self._part_id,
            "name": self._name,
            "brand": self._brand,
            "price": self._price,
        }

    @classmethod
    def from_dict(cls, data: dict) -> "VehiclePart":
        try:
            return cls(data["id"], data["name"], data["brand"], data["price"])
        except (KeyError, TypeError) as error:
            raise ValueError(f"Invalid part in JSON: {error}") from error

    @staticmethod
    def _require_text(value: str, field_name: str) -> str:
        if not isinstance(value, str) or not value.strip():
            raise ValueError(f"{field_name} cannot be empty")
        return value.strip()

    def __str__(self) -> str:
        return f"{self._part_id:<8} {self._name:<20} {self._brand:<12} ${self._price:>8.2f}"
