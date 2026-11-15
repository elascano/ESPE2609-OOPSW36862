from abc import ABC, abstractmethod


class SellAnimal(ABC):
    @abstractmethod
    def sellAnimal(self, price: float):
        raise NotImplementedError
