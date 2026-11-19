from abc import ABC, abstractmethod

class FarmAnimal(ABC):
    def __init__(self, animal_id, breed, born_on_date, is_meat):
        self._id = animal_id
        self._breed = breed
        self._born_on_date = born_on_date
        self._is_meat = is_meat

    @abstractmethod
    def feed(self, food, amount):
        pass

    def get_age_in_months(self):
        return 0

    def __str__(self):
        return f"FarmAnimal{{id={self._id}, breed={self._breed}, is meat={self._is_meat}, bornOnDate={self._born_on_date}}}"