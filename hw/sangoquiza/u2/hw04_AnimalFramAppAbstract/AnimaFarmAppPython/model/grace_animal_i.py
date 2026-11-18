from abc import ABC, abstractmethod
from model.location import Location

class GraceAnimalI(ABC):
    """
    Interfaz GraceAnimalI traducida de ec.edu.espe.model.GraceAnimalI
    @author Daniel Sangoquiza, SaborCode
    """
    @abstractmethod
    def graze(self, location: Location) -> None:
        pass