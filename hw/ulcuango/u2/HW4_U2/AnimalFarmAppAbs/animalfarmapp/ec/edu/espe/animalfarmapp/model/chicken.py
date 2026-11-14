from datetime import date
from ec.edu.espe.animalfarmapp.model.farm_animal import FarmAnimal

class Chicken(FarmAnimal):
    def __init__(self, is_molting: bool, number_of_laid_egg_per_week: int, is_meat: bool, id: int, bred: str, born_on_date: date):
        super().__init__(id, bred, born_on_date)
        self._is_molting = is_molting
        self._number_of_laid_egg_per_week = number_of_laid_egg_per_week
        self._is_meat = is_meat

    def assign_coop(self):
        pass

    def lay_an_egg(self):
        print("The chicken is layin an Egg")

    def __str__(self) -> str:
        # Replicamos el formato de concatenación del toString de Java
        super_str = super().__str__().replace("}", "")
        return f"Chicken{{{super_str}, isMolting={self._is_molting}, numberOfLaidEggPerWeek={self._number_of_laid_egg_per_week}, isMeat={self._is_meat}}}"

    @property
    def is_is_molting(self) -> bool:
        return self._is_molting

    @is_is_molting.setter
    def is_is_molting(self, is_molting: bool):
        self._is_molting = is_molting

    @property
    def number_of_laid_egg_per_week(self) -> int:
        return self._number_of_laid_egg_per_week

    @number_of_laid_egg_per_week.setter
    def number_of_laid_egg_per_week(self, number_of_laid_egg_per_week: int):
        self._number_of_laid_egg_per_week = number_of_laid_egg_per_week

    @property
    def is_is_meat(self) -> bool:
        return self._is_meat

    @is_is_meat.setter
    def is_is_meat(self, is_meat: bool):
        self._is_meat = is_meat