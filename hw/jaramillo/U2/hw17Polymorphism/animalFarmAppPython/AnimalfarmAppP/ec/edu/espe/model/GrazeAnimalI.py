from abc import ABC, abstractmethod
from datetime import datetime
from ec.edu.espe.model.Location import Location

class GrazeAnimalI(ABC):
    @abstractmethod
    def send_to_graze(self, location: Location, date: datetime):
        pass