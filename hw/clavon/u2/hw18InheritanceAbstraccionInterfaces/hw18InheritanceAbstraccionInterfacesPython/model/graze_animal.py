# @author David Clavón, Infinite loops, @ESPE

from abc import ABC, abstractmethod
from model.location import Location


class GrazeAnimal(ABC):
    @abstractmethod
    def graze(self, location: Location) -> None:
        pass