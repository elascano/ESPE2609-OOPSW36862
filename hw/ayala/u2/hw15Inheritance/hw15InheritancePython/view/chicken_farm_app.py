import os
import sys

sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

from controller.farm_controller import FarmController


class ChickenFarmApp:
    def __init__(self):
        self._controller = FarmController()

    def run(self) -> None:
        print("Enrique Farm App")
        print("=============================")

        farm_animal = self._controller.create_farm_animal(1, "Molstein")
        print("MY farm Animal is --->" + str(farm_animal))

        chicken = self._controller.create_chicken(True, 10, False, 2, "palish")
        print("Chicken --->" + str(chicken))


if __name__ == "__main__":
    ChickenFarmApp().run()
