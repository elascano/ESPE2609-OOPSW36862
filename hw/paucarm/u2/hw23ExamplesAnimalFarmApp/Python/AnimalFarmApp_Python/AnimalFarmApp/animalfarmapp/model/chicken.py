from datetime import date
from animalfarmapp.model.farm_animal import FarmAnimal

# @author Matias Paucar, ByteWorks, @ESPE


class Chicken(FarmAnimal):
    def __init__(self, is_molting: bool, number_of_laid_eggs_per_week: int,
                 is_meat: bool, id: int, breed: str, born_on_date: date):
        super().__init__(id, breed, born_on_date)
        self._is_molting = is_molting
        self._number_of_laid_eggs_per_week = number_of_laid_eggs_per_week
        self._is_meat = is_meat

    def __str__(self) -> str:
        return ("Chicken{" + super().__str__()
                + f"isMolting={self._is_molting}, "
                + f"numberOfLaidEggsPerWeek={self._number_of_laid_eggs_per_week}, "
                + f"isMeat={self._is_meat}" + "}")

    @property
    def is_molting(self) -> bool:
        return self._is_molting

    @is_molting.setter
    def is_molting(self, is_molting: bool):
        self._is_molting = is_molting

    @property
    def number_of_laid_eggs_per_week(self) -> int:
        return self._number_of_laid_eggs_per_week

    @number_of_laid_eggs_per_week.setter
    def number_of_laid_eggs_per_week(self, value: int):
        self._number_of_laid_eggs_per_week = value

    @property
    def is_meat(self) -> bool:
        return self._is_meat

    @is_meat.setter
    def is_meat(self, is_meat: bool):
        self._is_meat = is_meat
