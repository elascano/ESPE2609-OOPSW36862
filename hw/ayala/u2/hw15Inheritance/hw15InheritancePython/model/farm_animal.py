from datetime import datetime


class FarmAnimal:
    def __init__(self, id: int, breed: str, born_on_date: datetime):
        self._id = id
        self._breed = breed
        self._born_on_date = born_on_date

    def __str__(self) -> str:
        return (
            f"id={self._id}, breed={self._breed}, "
            f"bornOnDate={self._born_on_date.day}/"
            f"{self._born_on_date.month - 1}/{self._born_on_date.year},"
        )

    def get_age_in_months(self) -> int:
        return 0

    def get_id(self) -> int:
        return self._id

    def set_id(self, id: int) -> None:
        self._id = id

    def get_breed(self) -> str:
        return self._breed

    def set_breed(self, breed: str) -> None:
        self._breed = breed

    def get_born_on_date(self) -> datetime:
        return self._born_on_date

    def set_born_on_date(self, born_on_date: datetime) -> None:
        self._born_on_date = born_on_date
