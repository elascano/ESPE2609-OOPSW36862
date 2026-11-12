from datetime import datetime

from ec.edu.espe.animalfarmapp.model.farm_animal import FarmAnimal


class Cow(FarmAnimal):
    def __init__(self, is_producing_milk: bool, liters_of_milk_per_week: float,
                 id: int, breed: str, born_on_date: datetime) -> None:
        super().__init__(id, breed, born_on_date)
        self._is_producing_milk = is_producing_milk
        self._liters_of_milk_per_week = liters_of_milk_per_week

    def __str__(self) -> str:
        return ('Cow{' + super().__str__() +
                f'isProducingMilk={str(self._is_producing_milk).lower()}, '
                f'litersOfMilkPerWeek={self._liters_of_milk_per_week}' + '}')

    def milk(self) -> None:
        print('Milking the cow ')

    @property
    def is_producing_milk(self) -> bool:
        return self._is_producing_milk

    @is_producing_milk.setter
    def is_producing_milk(self, value: bool) -> None:
        self._is_producing_milk = value

    @property
    def liters_of_milk_per_week(self) -> float:
        return self._liters_of_milk_per_week

    @liters_of_milk_per_week.setter
    def liters_of_milk_per_week(self, value: float) -> None:
        self._liters_of_milk_per_week = value
