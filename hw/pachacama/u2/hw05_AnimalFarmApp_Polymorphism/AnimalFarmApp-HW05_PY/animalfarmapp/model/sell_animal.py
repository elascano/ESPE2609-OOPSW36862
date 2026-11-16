# @author Andrés Pachacama, ByteWorks, @ESPE
from abc import ABC, abstractmethod


class SellAnimal(ABC):

    @abstractmethod
    def sell_animal(self, price: float):
        pass
