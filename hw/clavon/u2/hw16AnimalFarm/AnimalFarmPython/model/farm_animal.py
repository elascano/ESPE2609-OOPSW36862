from datetime import date

# @author David Clavón, Infinite Loops, @ESPE
class FarmAnimal:
    def __init__(self, id: int, breed: str, born_on_date: date):
        self.__id = id
        self.__breed = breed
        self.__born_on_date = born_on_date

    def get_age_in_months(self) -> int:
        return 0

    def __str__(self) -> str:
        formatted_date = f"{self.__born_on_date.day}/{self.__born_on_date.month}/{self.__born_on_date.year}"
        return f"id={self.__id}, breed={self.__breed}, bornOnDate={formatted_date},"

    @property
    def id(self) -> int:
        return self.__id

    @id.setter
    def id(self, id: int):
        self.__id = id

    @property
    def breed(self) -> str:
        return self.__breed

    @breed.setter
    def breed(self, breed: str):
        self.__breed = breed

    @property
    def born_on_date(self) -> date:
        return self.__born_on_date

    @born_on_date.setter
    def born_on_date(self, born_on_date: date):
        self.__born_on_date = born_on_date