from datetime import datetime, date
from model.farm_animal import FarmAnimal
from model.grace_animal_i import GraceAnimalI
from model.food import Food
from model.location import Location

class Cow(FarmAnimal, GraceAnimalI):
    """
    Clase Cow traducida de ec.edu.espe.model.Cow
    Hereda de la clase abstracta FarmAnimal e implementa la interfaz GraceAnimalI.
    @author Daniel Sangoquiza, SaborCode
    """
    def __init__(self, is_producing_milk: bool, liters_of_milk_per_week: float, id: int, breed: str, born_on_date: date | datetime):
        super().__init__(id, breed, born_on_date)
        self._is_producing_milk = is_producing_milk
        self._liters_of_milk_per_week = liters_of_milk_per_week
        self._food: str | None = None

    def graze(self, location: Location) -> None:
        print(f"Feeding a cow in the stable with ---> {self._food}")

    def feed(self, food: Food, amount_of_food: int) -> None:
        print(f"Feeding a cow with ---> {food}")

    # --- Sobrecarga de milk() soportando 0, 1 (int/float) o 2 argumentos (float, datetime) ---
    def milk(self, amount_of_liters: int | float | None = None, hour: datetime | None = None) -> None:
        if amount_of_liters is None:
            print("Milking the cow as I need")
            return

        self._liters_of_milk_per_week += amount_of_liters

        if hour is not None:
            print(f"Milking the cow for {amount_of_liters} liters, at {hour.strftime('%H:%M')}")
        else:
            print(f"Milking the cow: {amount_of_liters} liters")

    @property
    def is_producing_milk(self) -> bool:
        return self._is_producing_milk

    @is_producing_milk.setter
    def is_producing_milk(self, value: bool):
        self._is_producing_milk = value

    def is_is_producing_milk(self) -> bool:
        return self.is_producing_milk

    @property
    def liters_of_milk_per_week(self) -> float:
        return self._liters_of_milk_per_week

    @liters_of_milk_per_week.setter
    def liters_of_milk_per_week(self, value: float):
        self._liters_of_milk_per_week = value

    def get_liters_of_milk_per_week(self) -> float:
        return self.liters_of_milk_per_week

    def __str__(self) -> str:
        return f"Cow{{{super().__str__()}, isProducingMilk={self.is_producing_milk}, litersOfMilkPerWeek={self.liters_of_milk_per_week}}}"