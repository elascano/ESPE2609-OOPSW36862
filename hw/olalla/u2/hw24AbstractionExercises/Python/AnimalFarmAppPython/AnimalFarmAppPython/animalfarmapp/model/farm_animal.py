from abc import ABC
from datetime import date

# @author Matias Paucar, ByteWorks, @ESPE


class FarmAnimal(ABC):

    def __init__(self, id: int, breed: str, born_on_date: date, is_meat: bool):
        if type(self) is FarmAnimal:
            raise TypeError("FarmAnimal es abstracta y no se puede instanciar")
        self._id = id
        self._breed = breed
        self._born_on_date = born_on_date
        self._is_meat = is_meat

    @staticmethod
    def format_date(d: date) -> str:
        return f"{d.day} / {d.month} / {d.year}"

    def __str__(self) -> str:
        return ("FarmAnimal{" + f"id={self.id}, breed={self.breed}, "
                + f"is meat={self.is_meat}, "
                + f"bornOnDate={self.format_date(self.born_on_date)}" + "}, ")
    @property
    def id(self) -> int:
        return self._id

    @id.setter
    def id(self, id: int):
        self._id = id

    @property
    def breed(self) -> str:
        return self._breed

    @breed.setter
    def breed(self, breed: str):
        self._breed = breed

    @property
    def born_on_date(self) -> date:
        return self._born_on_date

    @born_on_date.setter
    def born_on_date(self, born_on_date: date):
        self._born_on_date = born_on_date

    @property
    def is_meat(self) -> bool:
        return self._is_meat

    @is_meat.setter
    def is_meat(self, is_meat: bool):
        self._is_meat = is_meat
