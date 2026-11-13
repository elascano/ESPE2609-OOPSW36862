from datetime import date
from model.farm_animal import FarmAnimal

"""
@author Leslye Cuichan, Byte Fabric & devs, @ESPE
"""
class Chicken(FarmAnimal):
    def __init__(self, is_molting: bool, number_of_laid_egg_in_month: int, is_meat: bool, id_animal: int, breed: str, born_on_date: date):
        super().__init__(id_animal, breed, born_on_date)
        self._is_molting = is_molting
        self._number_of_laid_egg_in_month = number_of_laid_egg_in_month
        self._is_meat = is_meat

    def __str__(self):
        return f"Chicken{{{super().__str__()},isMolting={self._is_molting}, numberOfLaidEggInMonth={self._number_of_laid_egg_in_month}, isMeat={self._is_meat}}}"