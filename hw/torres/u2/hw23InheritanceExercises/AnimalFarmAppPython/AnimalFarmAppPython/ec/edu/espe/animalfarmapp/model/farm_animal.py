"""
@author Ostin Torres, CodeLex, @ESPE
"""
from datetime import date


class FarmAnimal:

    def __init__(self, id: int, breed: str, born_on_date: date, is_meat: bool):
        self.__id = id
        self.__breed = breed
        self.__born_on_date = born_on_date
        self.__is_meat = is_meat

    def __str__(self) -> str:
        return ("FarmAnimal{" + "id=" + str(self.id) + ", breed=" + self.breed
                + ", is meat=" + str(self.is_meat).lower()
                + ", bornOnDate=" + str(self.born_on_date.day) + " / "
                + str(self.born_on_date.month) + " / " + str(self.born_on_date.year)
                + "}, ")

    @property
    def id(self) -> int:
        """the id"""
        return self.__id

    @id.setter
    def id(self, id: int) -> None:
        self.__id = id

    @property
    def breed(self) -> str:
        """the breed"""
        return self.__breed

    @breed.setter
    def breed(self, breed: str) -> None:
        self.__breed = breed

    @property
    def born_on_date(self) -> date:
        """the bornOnDate"""
        return self.__born_on_date

    @born_on_date.setter
    def born_on_date(self, born_on_date: date) -> None:
        self.__born_on_date = born_on_date

    @property
    def is_meat(self) -> bool:
        """the isMeat"""
        return self.__is_meat

    @is_meat.setter
    def is_meat(self, is_meat: bool) -> None:
        self.__is_meat = is_meat
