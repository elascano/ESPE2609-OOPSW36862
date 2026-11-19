from model.farm_animal import FarmAnimal

class Sheep(FarmAnimal):
    def __init__(self, is_sheared, last_sheering, animal_id, breed, born_on_date, is_meat):
        super().__init__(animal_id, breed, born_on_date, is_meat)
        self._is_sheared = is_sheared
        self._last_sheering = last_sheering

    def shear_sheep(self):
        print("Sheep is sheared")

    def __str__(self):
        return f"Sheep{{{super().__str__()}, isSheared={self._is_sheared}, lastSheering={self._last_sheering}}}"