from abc import ABC, abstractmethod
from model.location import Location

class GrazeAnimal(ABC):
    @abstractmethod
    def sendto_graze(self, location: Location):
        pass
    