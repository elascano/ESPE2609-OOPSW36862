"""Module defining the abstract FarmAnimal superclass."""

from abc import abstractmethod
from datetime import date
from typing import Optional, overload
from .i_farm_animal import IFarmAnimal


class FarmAnimal(IFarmAnimal):
    """Abstract superclass implementing IFarmAnimal.

    Demonstrates static polymorphism via @overload / optional parameters
    (constructor and feed) and leaves compute_food_cost() and produce()
    abstract for dynamic polymorphism in concrete subclasses.
    """

    @overload
    def __init__(self) -> None: ...

    @overload
    def __init__(self, animal_id: int, breed: str) -> None: ...

    @overload
    def __init__(self, animal_id: int, breed: str, born_on: date) -> None: ...

    def __init__(
        self,
        animal_id: int = 0,
        breed: str = "Unknown",
        born_on: Optional[date] = None,
    ) -> None:
        self.__id: int = animal_id
        self.__breed: str = breed
        self.__born_on: date = born_on if born_on is not None else date.today()

    def get_age_in_months(self) -> int:
        """Calculate the exact age of the animal in completed months."""
        today = date.today()
        if self.__born_on > today:
            return 0
        months = (today.year - self.__born_on.year) * 12 + (today.month - self.__born_on.month)
        if today.day < self.__born_on.day:
            months -= 1
        return max(0, months)

    @overload
    def feed(self) -> str: ...

    @overload
    def feed(self, food_type: str, quantity: float) -> str: ...

    def feed(
        self, food_type: Optional[str] = None, quantity: Optional[float] = None
    ) -> str:
        """Feed the animal with a default ration or a specific food type and quantity."""
        animal_type = self.__class__.__name__
        if food_type is not None and quantity is not None:
            return (
                f"{animal_type} #{self.__id} ({self.__breed}) "
                f"is being fed {quantity:.2f} kg of {food_type}."
            )
        return (
            f"{animal_type} #{self.__id} ({self.__breed}) "
            "is being fed a standard daily ration."
        )

    @abstractmethod
    def compute_food_cost(self) -> float:
        """Compute the monthly food cost for this specific animal."""

    @abstractmethod
    def produce(self) -> str:
        """Describe the specific output or production of this animal."""

    def get_id(self) -> int:
        return self.__id

    def set_id(self, animal_id: int) -> None:
        self.__id = animal_id

    def get_breed(self) -> str:
        return self.__breed

    def set_breed(self, breed: str) -> None:
        self.__breed = breed

    def get_born_on(self) -> date:
        return self.__born_on

    def set_born_on(self, born_on: date) -> None:
        self.__born_on = born_on

    def __str__(self) -> str:
        return (
            f"FarmAnimal{{id={self.__id}, breed='{self.__breed}', "
            f"bornOn={self.__born_on.isoformat()}}}"
        )
