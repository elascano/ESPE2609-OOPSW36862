from .FarmAnimal import FarmAnimal
from .SellAnimal import SellAnimal


class Pig(FarmAnimal, SellAnimal):
    def __init__(self, is_wallowing: bool, weight_gain_per_week: float,
                 animal_id: int, breed: str, born_on_date, is_meat: bool):
        super().__init__(animal_id, breed, born_on_date, is_meat)
        self.isWallowing = is_wallowing
        self.weightGainPerWeek = weight_gain_per_week

    def feed(self, food, amount: int):
        print(f"feeding the pig with {amount} kg of {food}")

    def sellAnimal(self, price: float):
        print(f"sold the pig in {price:.1f} dollars.")

    def showPigMeatCuts(self):
        meat_cuts = ["head", "cheek", "picnic", "clear plate", "boston shoulder",
                     "back fat", "loin", "ribs", "bacon", "leg", "ham", "hock"]
        print("Avalaible cuts for the pig[" + ", ".join(meat_cuts) + "]")

    def getWeightGainPerWeek(self, weight_gain: int):
        print(f"the pig has gained {weight_gain} kg of weight")

    def __str__(self):
        return (f"Pig{{{super().__str__()}isWallowing={str(self.isWallowing).lower()}, "
                f"weightGainPerWeek={self.weightGainPerWeek:g}}}")
