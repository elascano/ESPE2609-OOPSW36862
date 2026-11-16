"""Author: Mateo Nacimba, @ESPE"""
from abc import ABC, abstractmethod
from datetime import datetime

from .Location import Location


class GrazeAnimal(ABC):
    @abstractmethod
    def send_to_graze(self, location: Location, date: datetime) -> None:
        ...
