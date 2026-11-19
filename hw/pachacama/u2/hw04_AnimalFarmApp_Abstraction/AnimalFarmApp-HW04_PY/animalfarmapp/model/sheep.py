# @author Andrés Pachacama, ByteWorks, @ESPE
import time
from datetime import date, datetime

from animalfarmapp.model.farm_animal import FarmAnimal


class Sheep(FarmAnimal):

    def shear_sheep(self):
        print("Sheep is sheared")

    def __str__(self) -> str:
        return ("Sheep{" + super().__str__()
                + "isSheared=" + str(self._is_sheared).lower()
                + ", lastSheering=" + self._format_date_time(self._last_sheering) + "}")

    def __init__(self, is_sheared: bool, last_sheering: datetime,
                 id: int, breed: str, born_on_date: date, is_meat: bool):
        super().__init__(id, breed, born_on_date, is_meat)
        self._is_sheared = is_sheared
        self._last_sheering = last_sheering

    @staticmethod
    def _format_date_time(value: datetime) -> str:
        offset_minutes = -time.timezone // 60
        sign = "+" if offset_minutes >= 0 else "-"
        hours, minutes = divmod(abs(offset_minutes), 60)
        zone = f"GMT{sign}{hours:02d}:{minutes:02d}"
        return value.strftime("%a %b %d %H:%M:%S ") + zone + " " + str(value.year)

    @property
    def is_sheared(self) -> bool:
        return self._is_sheared

    @is_sheared.setter
    def is_sheared(self, is_sheared: bool):
        self._is_sheared = is_sheared

    @property
    def last_sheering(self) -> datetime:
        return self._last_sheering

    @last_sheering.setter
    def last_sheering(self, last_sheering: datetime):
        self._last_sheering = last_sheering
