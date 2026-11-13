from datetime import date
from animalfarmapp.model.farm_animal import FarmAnimal

# @author Matias Paucar, ByteWorks, @ESPE


class Pig(FarmAnimal):

    def __init__(self, is_wallowing: bool, weight_gain_per_week: float,
                 id: int, breed: str, born_on_date: date, is_meat: bool):
        super().__init__(id, breed, born_on_date, is_meat)
        self._is_wallowing = is_wallowing
        self._weight_gain_per_week = weight_gain_per_week

    def show_pig_meat_cuts(self):
        meat_cuts = ["head", "cheek", "picnic", "clear plate", "boston shoulder",
                     "back fat", "loin", "ribs", "bacon", "leg", "ham", "hock"]
        print("Avalaible cuts for the pig" + "[" + ", ".join(meat_cuts) + "]")

    def __str__(self) -> str:
        return ("Pig{" + super().__str__()
                + f"isWallowing={self._is_wallowing}, "
                + f"weightGainPerWeek={self._weight_gain_per_week}" + "}")
    @property
    def is_wallowing(self) -> bool:
        return self._is_wallowing

    @is_wallowing.setter
    def is_wallowing(self, value: bool):
        self._is_wallowing = value

    @property
    def weight_gain_per_week(self) -> float:
        return self._weight_gain_per_week

    @weight_gain_per_week.setter
    def weight_gain_per_week(self, value: float):
        self._weight_gain_per_week = value
