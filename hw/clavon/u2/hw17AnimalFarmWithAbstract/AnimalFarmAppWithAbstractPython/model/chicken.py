from datetime import datetime
from model.farm_animal import FarmAnimal

class Chicken(FarmAnimal):
    def __init__(
        self,
        is_molting: bool,
        number_of_laid_eggs_in_month: int,
        is_meat: bool,
        id: int,
        breed: str,
        born_on_date: datetime,
    ):
        super().__init__(id, breed, born_on_date)
        self._is_molting = is_molting
        self._number_of_laid_eggs_in_month = number_of_laid_eggs_in_month
        self._is_meat = is_meat

    def __str__(self) -> str:
        return (
            f"chicken{{{super().__str__()}"
            f"isMolting={str(self._is_molting).lower()}, "
            f"numberOfLaidEggsInMonth={self._number_of_laid_eggs_in_month}, "
            f"isMeat={str(self._is_meat).lower()}}}"
        )

    def is_is_molting(self) -> bool:
        return self._is_molting

    def set_is_molting(self, is_molting: bool):
        self._is_molting = is_molting

    def get_number_of_laid_eggs_in_month(self) -> int:
        return self._number_of_laid_eggs_in_month

    def set_number_of_laid_eggs_in_month(self, number_of_laid_eggs_in_month: int):
        self._number_of_laid_eggs_in_month = number_of_laid_eggs_in_month

    def is_is_meat(self) -> bool:
        return self._is_meat

    def set_is_meat(self, is_meat: bool):
        self._is_meat = is_meat

    def lay_an_egg(self):
        print("The chicken is laying an Egg")