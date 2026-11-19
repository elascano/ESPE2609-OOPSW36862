from model.farm_animal import FarmAnimal

class Pig(FarmAnimal):
    def __init__(self, is_wallowing, weight_gain_per_week, animal_id, breed, born_on_date, is_meat):
        super().__init__(animal_id, breed, born_on_date, is_meat)
        self._is_wallowing = is_wallowing
        self._weight_gain_per_week = weight_gain_per_week

    def show_pig_meat_cuts(self):
        meat_cuts = ["head", "cheek", "picnic", "clear plate", "boston shoulder", "back fat", "loin", "ribs", "bacon", "leg", "ham", "hock"]
        print(f"Available cuts for the pig: {meat_cuts}")

    def __str__(self):
        return f"Pig{{{super().__str__()}, isWallowing={self._is_wallowing}, weightGain={self._weight_gain_per_week}}}"