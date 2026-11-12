from model.farm_animal import FarmAnimal

class Chicken(FarmAnimal):
    def __init__(self, is_molting, number_of_laid_eggs_per_week,
                 id, breed, born_on_date, is_meat=False):
        super().__init__(id, breed, born_on_date, is_meat)
        self._is_molting = is_molting
        self._number_of_laid_eggs_per_week = number_of_laid_eggs_per_week

    def __str__(self):
        return (
            f"Chicken{{{super().__str__()}"
            f"isMolting={self._is_molting}, "
            f"numberOfLaidEggsPerWeek={self._number_of_laid_eggs_per_week}}}"
        )

    def get_is_molting(self):
        return self._is_molting

    def set_is_molting(self, is_molting):
        self._is_molting = is_molting

    def get_number_of_laid_eggs_per_week(self):
        return self._number_of_laid_eggs_per_week

    def set_number_of_laid_eggs_per_week(self, number):
        self._number_of_laid_eggs_per_week = number
