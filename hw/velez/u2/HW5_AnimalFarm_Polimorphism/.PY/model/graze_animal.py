from abc import ABC, abstractmethod

class GrazeAnimal(ABC):
    @abstractmethod
    def send_to_graze(self, location, date):
        pass