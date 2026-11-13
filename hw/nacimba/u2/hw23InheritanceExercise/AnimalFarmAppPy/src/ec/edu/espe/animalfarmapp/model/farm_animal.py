from datetime import datetime


class FarmAnimal:
    def __init__(self, id: int, breed: str, born_on_date: datetime) -> None:
        self._id = id
        self._breed = breed
        self._born_on_date = born_on_date

    def __str__(self) -> str:
        d = self._born_on_date
        return (f'id={self._id}, breed={self._breed}, '
                f'bornOnDate={d.day}/{d.month}/{d.year}, ')

    def get_age_in_months(self) -> int:
        # TODO: calcular la edad en meses
        return 0

    @property
    def id(self) -> int:
        return self._id

    @id.setter
    def id(self, value: int) -> None:
        self._id = value

    @property
    def breed(self) -> str:
        return self._breed

    @breed.setter
    def breed(self, value: str) -> None:
        self._breed = value

    @property
    def born_on_date(self) -> datetime:
        return self._born_on_date

    @born_on_date.setter
    def born_on_date(self, value: datetime) -> None:
        self._born_on_date = value
