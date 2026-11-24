"""Module defining the IFarmAnimal interface."""

from abc import ABC, abstractmethod


class IFarmAnimal(ABC):
    """Interface defining the public contract for all farm animals."""

    @abstractmethod
    def compute_food_cost(self) -> float:
        """Compute the monthly food cost for this animal in USD."""

    @abstractmethod
    def feed(self) -> str:
        """Feed the animal using its default farm ration."""

    @abstractmethod
    def produce(self) -> str:
        """Describe the specific output or production of the animal."""
