from model.farm_animal import FarmAnimal

class Sheep(FarmAnimal):
    def __init__(self, is_sheared, last_shearing,
                 id, breed, born_on_date, is_meat):
        super().__init__(id, breed, born_on_date, is_meat)
        self._is_sheared = is_sheared
        self._last_shearing = last_shearing

    def __str__(self):
        return (
            f"Sheep{{{super().__str__()}"
            f"isSheared={self._is_sheared}, "
            f"lastShearing={self._last_shearing:%d/%m/%Y}}}"
        )

    def get_is_sheared(self):
        return self._is_sheared

    def set_is_sheared(self, is_sheared):
        self._is_sheared = is_sheared

    def get_last_shearing(self):
        return self._last_shearing

    def set_last_shearing(self, last_shearing):
        self._last_shearing = last_shearing
