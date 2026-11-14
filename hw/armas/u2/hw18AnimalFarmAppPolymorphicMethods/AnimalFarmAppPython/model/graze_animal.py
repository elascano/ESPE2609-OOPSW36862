from abc import ABC, abstractmethod

from model.location import Location


class GrazeAnimal(ABC):
    @abstractmethod
    def graze(self, location: Location) -> str:
        pass
