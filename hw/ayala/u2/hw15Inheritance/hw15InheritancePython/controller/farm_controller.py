from datetime import datetime

from model.chicken import Chicken
from model.farm_animal import FarmAnimal


class FarmController:
    def create_farm_animal(self, id: int, breed: str) -> FarmAnimal:
        return FarmAnimal(id, breed, datetime.now())

    def create_chicken(
        self,
        is_molting: bool,
        number_of_last_eggs_per_week: int,
        is_meat: bool,
        id: int,
        breed: str,
    ) -> Chicken:
        return Chicken(
            is_molting,
            number_of_last_eggs_per_week,
            is_meat,
            id,
            breed,
            datetime.now(),
        )
