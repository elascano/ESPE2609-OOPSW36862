"""
@author Andres Pachacama, Byte Works, ESPE
"""
from model.farm_animal import FarmAnimal


class Chicken(FarmAnimal):

    def __init__(self, is_molting, number_of_laid_eggs_per_week, is_meat, id, breed, born_on_date):
        super().__init__(id, breed, born_on_date)
        self._is_molting = is_molting
        self._number_of_laid_eggs_per_week = number_of_laid_eggs_per_week
        self._is_meat = is_meat

    def __str__(self):
        return ("Chicken{" + super().__str__() + " isMolting=" + str(self.is_is_molting())
                + ", numberOfLaidEggsPerWeek=" + str(self.get_number_of_laid_eggs_per_week())
                + ", isMeat=" + str(self.is_is_meat()) + "}")

    def is_is_molting(self):
        """@return the is_molting"""
        return self._is_molting

    def set_is_molting(self, is_molting):
        """@param is_molting the is_molting to set"""
        self._is_molting = is_molting

    def get_number_of_laid_eggs_per_week(self):
        """@return the number_of_laid_eggs_per_week"""
        return self._number_of_laid_eggs_per_week

    def set_number_of_laid_eggs_per_week(self, number_of_laid_eggs_per_week):
        """@param number_of_laid_eggs_per_week the number_of_laid_eggs_per_week to set"""
        self._number_of_laid_eggs_per_week = number_of_laid_eggs_per_week

    def is_is_meat(self):
        """@return the is_meat"""
        return self._is_meat

    def set_is_meat(self, is_meat):
        """@param is_meat the is_meat to set"""
        self._is_meat = is_meat
