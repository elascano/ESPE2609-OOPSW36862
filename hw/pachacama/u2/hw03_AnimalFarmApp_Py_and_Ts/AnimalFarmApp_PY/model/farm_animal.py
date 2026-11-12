"""
@author Andres Pachacama, Byte Works, ESPE
"""


class FarmAnimal:

    def __init__(self, id, breed, born_on_date):
        self._id = id
        self._breed = breed
        self._born_on_date = born_on_date

    def __str__(self):
        return ("id=" + str(self._id) + ", breed=" + self._breed
                + ", bornOnDate=" + str(self._born_on_date.day)
                + "/" + str(self._born_on_date.month) + "/" + str(self._born_on_date.year) + ",")

    def get_age_in_months(self):
        # Todo Compute the age
        return 0

    def get_id(self):
        """@return the id"""
        return self._id

    def set_id(self, id):
        """@param id the id to set"""
        self._id = id

    def get_breed(self):
        """@return the breed"""
        return self._breed

    def set_breed(self, breed):
        """@param breed the breed to set"""
        self._breed = breed

    def get_born_on_date(self):
        """@return the born_on_date"""
        return self._born_on_date

    def set_born_on_date(self, born_on_date):
        """@param born_on_date the born_on_date to set"""
        self._born_on_date = born_on_date
