from datetime import datetime

class Chicken:
    def __init__(self, chicken_id: int, name: str, color: str, born_on_date: datetime, is_molting: bool):
        self._id = chicken_id
        self._name = name
        self._color = color
        self._born_on_date = born_on_date
        self._is_molting = is_molting

    @property
    def id(self) -> int:
        return self._id

    @property
    def name(self) -> str:
        return self._name

    @property
    def color(self) -> str:
        return self._color

    @property
    def born_on_date(self) -> datetime:
        return self._born_on_date

    @property
    def is_molting(self) -> bool:
        return self._is_molting

    def __str__(self) -> str:
        date_str = self._born_on_date.strftime("%Y-%m-%d")
        return (f"Chicken{{id={self._id}, name='{self._name}', color='{self._color}', "
                f"bornOnDate={date_str}, isMolting={self._is_molting}}}") 