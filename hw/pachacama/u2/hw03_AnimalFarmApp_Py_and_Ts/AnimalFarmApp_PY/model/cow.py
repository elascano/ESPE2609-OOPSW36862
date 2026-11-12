"""
@author Andres Pachacama, Byte Works, ESPE
"""
from model.farm_animal import FarmAnimal


class Cow(FarmAnimal):

    def __init__(self, liters_of_milk_per_day, is_pregnant, weight_in_kg, is_milking, id, breed, born_on_date):
        super().__init__(id, breed, born_on_date)
        self._liters_of_milk_per_day = liters_of_milk_per_day
        self._is_pregnant = is_pregnant
        self._weight_in_kg = weight_in_kg
        self._is_milking = is_milking

    def __str__(self):
        return ("Cow{" + super().__str__() + " litersOfMilkPerDay=" + str(self.get_liters_of_milk_per_day())
                + ", isPregnant=" + str(self.is_is_pregnant())
                + ", weightInKg=" + str(self.get_weight_in_kg())
                + ", isMilking=" + str(self.is_is_milking()) + "}")

    def get_liters_of_milk_per_day(self):
        """@return the liters_of_milk_per_day"""
        return self._liters_of_milk_per_day

    def set_liters_of_milk_per_day(self, liters_of_milk_per_day):
        """@param liters_of_milk_per_day the liters_of_milk_per_day to set"""
        self._liters_of_milk_per_day = liters_of_milk_per_day

    def is_is_pregnant(self):
        """@return the is_pregnant"""
        return self._is_pregnant

    def set_is_pregnant(self, is_pregnant):
        """@param is_pregnant the is_pregnant to set"""
        self._is_pregnant = is_pregnant

    def get_weight_in_kg(self):
        """@return the weight_in_kg"""
        return self._weight_in_kg

    def set_weight_in_kg(self, weight_in_kg):
        """@param weight_in_kg the weight_in_kg to set"""
        self._weight_in_kg = weight_in_kg

    def is_is_milking(self):
        """@return the is_milking"""
        return self._is_milking

    def set_is_milking(self, is_milking):
        """@param is_milking the is_milking to set"""
        self._is_milking = is_milking
