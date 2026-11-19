# @author Andrés Pachacama, ByteWorks, @ESPE
from datetime import date

from animalfarmapp.model.farm_animal import FarmAnimal


class Pig(FarmAnimal):

    def show_pig_meat_cuts(self):
        meat_cuts = ["head", "cheek", "picnic", "clear plate", "boston shoulder", "back fat",
                     "loin", "ribs", "bacon", "leg", "ham", "hock"]
        print("Avalaible cuts for the pig" + "[" + ", ".join(meat_cuts) + "]")

    def __str__(self) -> str:
        return ("Pig{" + super().__str__()
                + "isWallowing=" + str(self._is_wallowing).lower()
                + ", weightGainPerWeek=" + str(self._weight_gain_per_week) + "}")

    def __init__(self, is_wallowing: bool, weight_gain_per_week: float,
                 id: int, breed: str, born_on_date: date, is_meat: bool):
        super().__init__(id, breed, born_on_date, is_meat)
        self._is_wallowing = is_wallowing
        self._weight_gain_per_week = weight_gain_per_week

    @property
    def is_wallowing(self) -> bool:
        return self._is_wallowing

    @is_wallowing.setter
    def is_wallowing(self, is_wallowing: bool):
        self._is_wallowing = is_wallowing

    @property
    def weight_gain_per_week(self) -> float:
        return self._weight_gain_per_week

    @weight_gain_per_week.setter
    def weight_gain_per_week(self, weight_gain_per_week: float):
        self._weight_gain_per_week = weight_gain_per_week
