"""Author: Mateo Nacimba, @ESPE"""
from abc import ABC, abstractmethod


class SellAnimal(ABC):
    @abstractmethod
    def sell_animal(self, price: float) -> None:
        ...
