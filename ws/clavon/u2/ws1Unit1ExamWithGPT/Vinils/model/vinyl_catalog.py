
import json
from pathlib import Path

from model.vinyl_record import VinylRecord


class VinylCatalog:

    def __init__(self, json_path):
        self._json_path = Path(json_path)
        self._records = []
        self.load_from_json()

    def add_record(self, record):
        if self.find_by_id(record.id) is not None:
            return False

        self._records.append(record)

        try:
            self.save_to_json()
        except OSError:
            self._records.pop()
            raise

        return True

    def get_all_records(self):
        return self._records.copy()

    def find_by_id(self, record_id):
        for record in self._records:
            if record.id == record_id:
                return record

        return None

    def save_to_json(self):
        records_data = [
            record.to_dict() for record in self._records
        ]

        with self._json_path.open("w", encoding="utf-8") as file:
            json.dump(records_data, file, indent=4, ensure_ascii=False)

    def load_from_json(self):
        if not self._json_path.exists():
            self._records = []
            return 0

        try:
            with self._json_path.open("r", encoding="utf-8") as file:
                records_data = json.load(file)

            if not isinstance(records_data, list):
                raise ValueError("JSON must contain a list.")

            records = [
                VinylRecord.from_dict(data)
                for data in records_data
            ]

            ids = [record.id for record in records]

            if len(ids) != len(set(ids)):
                raise ValueError("Duplicate IDs in JSON file.")

            self._records = records
            return len(self._records)

        except (OSError, json.JSONDecodeError, KeyError,
                TypeError, ValueError) as error:
            raise ValueError(
                f"Error loading JSON file: {error}"
            ) from error
