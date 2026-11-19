from abc import ABC, abstractmethod


class GrazeAnimalI(ABC):
    @abstractmethod
    def sendToGraze(self, location, date):
        pass
