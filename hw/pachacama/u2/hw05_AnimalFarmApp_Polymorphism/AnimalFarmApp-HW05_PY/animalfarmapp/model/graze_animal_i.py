# @author Andrés Pachacama, ByteWorks, @ESPE
from abc import ABC, abstractmethod
from datetime import datetime

from animalfarmapp.model.location import Location


class GrazeAnimalI(ABC):

    @abstractmethod
    def send_to_graze(self, location: Location, date: datetime):
        pass
