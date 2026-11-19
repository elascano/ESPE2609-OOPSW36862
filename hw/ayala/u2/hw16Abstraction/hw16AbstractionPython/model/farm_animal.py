from datetime import date


class FarmAnimal:
    def __init__(self, id: int, breed: str, born_on_date: date):
        self._id = id
        self._breed = breed
        self._born_on_date = born_on_date

    def get_age_in_months(self) -> int:
        return 0

    def __str__(self) -> str:
        return (
            f"id={self._id}, breed={self._breed}, "
            f"bornOnDate={self._born_on_date.day}/{self._born_on_date.month}/{self._born_on_date.year},"
        )

    @property
    def id(self) -> int:
        return self._id

    @id.setter
    def id(self, id: int) -> None:
        self._id = id

    @property
    def breed(self) -> str:
        return self._breed

    @breed.setter
    def breed(self, breed: str) -> None:
        self._breed = breed

    @property
    def born_on_date(self) -> date:
        return self._born_on_date

    @born_on_date.setter
    def born_on_date(self, born_on_date: date) -> None:
        self._born_on_date = born_on_date
