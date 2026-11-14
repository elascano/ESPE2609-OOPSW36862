from datetime import date

class FarmAnimal:
    def __init__(self, id: int, bred: str, born_on_date: date):
        self._id = id
        self._bred = bred
        self._born_on_date = born_on_date

    def get_age_in_months(self) -> int:
        return 0

    def __str__(self) -> str:
        return f"FarmAnimal{{id={self._id}, bred={self._bred}, bornOnDate={self._born_on_date}}}"

    # Getters y Setters usando propiedades de Python
    @property
    def id(self) -> int:
        return self._id

    @id.setter
    def id(self, id: int):
        self._id = id

    @property
    def bred(self) -> str:
        return self._bred

    @bred.setter
    def bred(self, bred: str):
        self._bred = bred

    @property
    def born_on_date(self) -> date:
        return self._born_on_date

    @born_on_date.setter
    def born_on_date(self, born_on_date: date):
        self._born_on_date = born_on_date