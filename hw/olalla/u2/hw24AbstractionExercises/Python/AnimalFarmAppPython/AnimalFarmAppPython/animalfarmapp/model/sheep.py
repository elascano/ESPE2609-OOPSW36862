from datetime import date
from animalfarmapp.model.farm_animal import FarmAnimal

# @author Matias Paucar, ByteWorks, @ESPE


class Sheep(FarmAnimal):

    def __init__(self, is_sheared: bool, last_sheering: date,
                 id: int, breed: str, born_on_date: date, is_meat: bool):
        super().__init__(id, breed, born_on_date, is_meat)
        self._is_sheared = is_sheared
        self._last_sheering = last_sheering

    def shear_sheep(self):
        print("Sheep is sheared")

    def __str__(self) -> str:
        return ("Sheep{" + super().__str__()
                + f"isSheared={self._is_sheared}, "
                + f"lastSheering={self.format_date(self._last_sheering)}" + "}")
    @property
    def is_sheared(self) -> bool:
        return self._is_sheared

    @is_sheared.setter
    def is_sheared(self, value: bool):
        self._is_sheared = value

    @property
    def last_sheering(self) -> date:
        return self._last_sheering

    @last_sheering.setter
    def last_sheering(self, value: date):
        self._last_sheering = value
