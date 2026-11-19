from abc import ABC, abstractmethod
from datetime import date
from .location import Location

class GrazeAnimalI(ABC):
    @abstractmethod
    def sen_to_graze(self, location: Location, date: date):
        pass