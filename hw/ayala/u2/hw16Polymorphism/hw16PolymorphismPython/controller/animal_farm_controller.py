from datetime import date

from model import Chicken, Cow, FarmAnimal
from view import AnimalFarmView


class AnimalFarmController:
    def __init__(self, view: AnimalFarmView):
        self._view = view

    def run(self) -> None:
        self._view.show_title()
        born_on_date = date(2020, 11, 10)

        farm_animal: FarmAnimal = FarmAnimal(1, "holstein", date.today())
        self._view.show_animal("my farm animal", farm_animal)
        self._view.show_animal_type("FarmAnimal", farm_animal)
        self._view.show_animal_class("FarmAnimal", farm_animal)

        farm_animal = Chicken(True, 10, 2, "Paleesh", date.today(), True)
        self._view.show_animal_type("FarmAnimal", farm_animal)

        chicken = Chicken(True, 10, 2, "Paleesh", date.today(), True)
        self._view.show_animal("chicken", chicken)

        cow: FarmAnimal = Cow(True, 100.5, 3, "holstein", date(2123, 9, 20))

        cow = Chicken(True, 4, 0, "paleesh", born_on_date, True)
        self._view.show_animal("cow", cow)

        cow2 = Cow(True, 0, 0, "palich", born_on_date)
        self._view.show_message(cow2.milk())
        self._view.show_message(chicken.lay_an_egg())
