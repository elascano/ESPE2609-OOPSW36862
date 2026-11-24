"""Module defining the FarmAnimal superclass."""

from datetime import date


class FarmAnimal:
    """Superclass representing a generic farm animal."""

    def __init__(self, animal_id: int, breed: str, born_on: date) -> None:
        self.__id: int = animal_id
        self.__breed: str = breed
        self.__born_on: date = born_on

    def get_age_in_months(self) -> int:
        """Calculate the exact age of the animal in completed months."""
        today = date.today()
        if self.__born_on > today:
            return 0
        months = (today.year - self.__born_on.year) * 12 + (today.month - self.__born_on.month)
        if today.day < self.__born_on.day:
            months -= 1
        return max(0, months)

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
