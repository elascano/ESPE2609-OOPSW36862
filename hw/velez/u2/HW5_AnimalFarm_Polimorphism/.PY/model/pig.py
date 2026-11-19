from model.farm_animal import FarmAnimal
from model.sell_animal import SellAnimal

class Pig(FarmAnimal, SellAnimal):
    def __init__(self, is_wallowing, weight_gain_per_week, animal_id, breed, born_on_date, is_meat):
        super().__init__(animal_id, breed, born_on_date, is_meat)
        self._is_wallowing = is_wallowing
        self._weight_gain_per_week = weight_gain_per_week

    def show_pig_meat_cuts(self):
        meat_cuts = ["head", "cheek", "picnic", "clear plate", "boston shoulder", "back fat", "loin", "ribs", "bacon", "leg", "ham", "hock"]
        print(f"Available cuts for the pig: {meat_cuts}")

    def feed(self, food, amount):
        print(f"feeding the pig with {amount} kg of {food}")

    def sell_animal(self, price):
        print(f"sold the pig in {price} dollars.")

    def get_weight_gain_per_week(self, weight_gain):
        print(f"the pig has gained {weight_gain} kg of weight")

    def __str__(self):
        return f"Pig{{{super().__str__()}, isWallowing={self._is_wallowing}, weightGain={self._weight_gain_per_week}}}"