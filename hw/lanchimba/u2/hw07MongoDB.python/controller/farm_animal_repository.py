"""Repository (DAO) for FarmAnimal CRUD operations in MongoDB Atlas."""

from datetime import date
from typing import Any, Optional

from pymongo.collection import Collection
from pymongo.errors import DuplicateKeyError

from model.farm_animal import FarmAnimal


class FarmAnimalRepository:
    """Data Access Object implementing CRUD operations for FarmAnimal documents."""

    def __init__(self, collection: Collection) -> None:
        self.__collection: Collection = collection
        # Enforce uniqueness on domain 'id' field in MongoDB
        self.__collection.create_index("id", unique=True)

    def insert_one(self, animal: FarmAnimal) -> str:
        """Insert a single FarmAnimal document and return its MongoDB ObjectId string."""
        document = animal.to_dict()
        try:
            result = self.__collection.insert_one(document)
            return str(result.inserted_id)
        except DuplicateKeyError as exc:
            raise ValueError(
                f"An animal with id={animal.get_id()} already exists in the collection."
            ) from exc

    def find_all(self) -> list[FarmAnimal]:
        """Retrieve all FarmAnimal documents ordered by id ascending."""
        cursor = self.__collection.find().sort("id", 1)
        return [FarmAnimal.from_dict(doc) for doc in cursor]

    def find_by_id(self, animal_id: int) -> Optional[FarmAnimal]:
        """Find a single FarmAnimal by its domain id."""
        doc = self.__collection.find_one({"id": animal_id})
        if doc is None:
            return None
        return FarmAnimal.from_dict(doc)

    def update_by_id(self, animal_id: int, updated_fields: dict[str, Any]) -> bool:
        """Update allowed fields of a FarmAnimal identified by its domain id."""
        allowed_keys = {"animal_type", "breed", "born_on", "weight_kg", "is_active"}
        sanitized: dict[str, Any] = {}

        for key, value in updated_fields.items():
            if key in allowed_keys:
                if key == "born_on" and isinstance(value, date):
                    sanitized[key] = value.isoformat()
                else:
                    sanitized[key] = value

        if not sanitized:
            return False

        result = self.__collection.update_one(
            {"id": animal_id},
            {"$set": sanitized},
        )
        return result.matched_count > 0

    def delete_by_id(self, animal_id: int) -> bool:
        """Delete a FarmAnimal document by its domain id."""
        result = self.__collection.delete_one({"id": animal_id})
        return result.deleted_count > 0
