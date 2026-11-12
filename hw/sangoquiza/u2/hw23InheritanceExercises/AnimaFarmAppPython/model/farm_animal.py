from datetime import datetime, date

class FarmAnimal:
   
    def __init__(self, id: int, breed: str, born_on_date: date | datetime, is_meat: bool):
        self._id = id
        self._breed = breed
        self._born_on_date = born_on_date
        self._is_meat = is_meat

    @property
    def id(self) -> int:
        return self._id

    @id.setter
    def id(self, value: int):
        self._id = value

    @property
    def breed(self) -> str:
        return self._breed

    @breed.setter
    def breed(self, value: str):
        self._breed = value

    @property
    def born_on_date(self) -> date | datetime:
        return self._born_on_date

    @born_on_date.setter
    def born_on_date(self, value: date | datetime):
        self._born_on_date = value

    @property
    def is_meat(self) -> bool:
        return self._is_meat

    @is_meat.setter
    def is_meat(self, value: bool):
        self._is_meat = value

    def is_is_meat(self) -> bool:
        return self.is_meat

    def __str__(self) -> str:
        day = self.born_on_date.day
        month = self.born_on_date.month
        year = self.born_on_date.year
        return f"FarmAnimal{{id={self.id}, breed={self.breed}, is meat={self.is_meat}, bornOnDate={day} / {month} / {year}}}, "