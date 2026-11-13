from model.farm_animal import FarmAnimal

class Pig(FarmAnimal):
    def __init__(self, is_wallowing, weight_gain_per_week,
                 id, breed, born_on_date, is_meat):
        super().__init__(id, breed, born_on_date, is_meat)
        self._is_wallowing = is_wallowing
        self._weight_gain_per_week = weight_gain_per_week

    def __str__(self):
        return (
            f"Pig{{{super().__str__()}"
            f"isWallowing={self._is_wallowing}, "
            f"weightGainPerWeek={self._weight_gain_per_week}}}"
        )

    def get_is_wallowing(self):
        return self._is_wallowing

    def set_is_wallowing(self, is_wallowing):
        self._is_wallowing = is_wallowing

    def get_weight_gain_per_week(self):
        return self._weight_gain_per_week

    def set_weight_gain_per_week(self, weight):
        self._weight_gain_per_week = weight
