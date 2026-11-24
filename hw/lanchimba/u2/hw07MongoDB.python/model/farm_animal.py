"""Domain model representing a FarmAnimal persisted in MongoDB."""

from datetime import date
from typing import Any


class FarmAnimal:
    """Encapsulated domain entity for a farm animal with BSON/JSON serialization."""

    def __init__(
        self,
        animal_id: int,
        animal_type: str,
        breed: str,
        born_on: date,
        weight_kg: float,
        is_active: bool = True,
    ) -> None:
        self.__id: int = animal_id
        self.__animal_type: str = animal_type
        self.__breed: str = breed
        self.__born_on: date = born_on
        self.__weight_kg: float = weight_kg
        self.__is_active: bool = is_active

    def get_age_in_months(self) -> int:
        """Calculate exact age in completed months from born_on to today."""
        today = date.today()
        if self.__born_on > today:
            return 0
        months = (today.year - self.__born_on.year) * 12 + (today.month - self.__born_on.month)
        if today.day < self.__born_on.day:
            months -= 1
        return max(0, months)

    def to_dict(self) -> dict[str, Any]:
        """Serialize the FarmAnimal object into a MongoDB-compatible dictionary."""
        return {
            "id": self.__id,
            "animal_type": self.__animal_type,
            "breed": self.__breed,
            "born_on": self.__born_on.isoformat(),
            "weight_kg": self.__weight_kg,
            "is_active": self.__is_active,
        }

    @classmethod
    def from_dict(cls, data: dict[str, Any]) -> "FarmAnimal":
        """Deserialize a MongoDB document dictionary into a FarmAnimal instance."""
        raw_born_on = data.get("born_on")
        if isinstance(raw_born_on, date):
            born_on = raw_born_on
        elif isinstance(raw_born_on, str):
            born_on = date.fromisoformat(raw_born_on)
        else:
            born_on = date.today()

        return cls(
            animal_id=int(data["id"]),
            animal_type=str(data.get("animal_type", "Unknown")),
            breed=str(data.get("breed", "Unknown")),
            born_on=born_on,
            weight_kg=float(data.get("weight_kg", 0.0)),
            is_active=bool(data.get("is_active", True)),
        )

    def get_id(self) -> int:
        return self.__id

    def set_id(self, animal_id: int) -> None:
        self.__id = animal_id

    def get_animal_type(self) -> str:
        return self.__animal_type

    def set_animal_type(self, animal_type: str) -> None:
        self.__animal_type = animal_type

    def get_breed(self) -> str:
        return self.__breed

    def set_breed(self, breed: str) -> None:
        self.__breed = breed

    def get_born_on(self) -> date:
        return self.__born_on

    def set_born_on(self, born_on: date) -> None:
        self.__born_on = born_on

    def get_weight_kg(self) -> float:
        return self.__weight_kg

    def set_weight_kg(self, weight_kg: float) -> None:
        self.__weight_kg = weight_kg

    def get_is_active(self) -> bool:
        return self.__is_active

    def set_is_active(self, is_active: bool) -> None:
        self.__is_active = is_active

    def __str__(self) -> str:
        return (
            f"FarmAnimal{{id={self.__id}, type='{self.__animal_type}', "
            f"breed='{self.__breed}', bornOn={self.__born_on.isoformat()}, "
            f"age={self.get_age_in_months()}m, weight={self.__weight_kg:.2f}kg, "
            f"active={self.__is_active}}}"
        )
