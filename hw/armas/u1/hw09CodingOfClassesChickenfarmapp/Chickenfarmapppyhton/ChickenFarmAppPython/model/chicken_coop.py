from model.chicken import Chicken

class ChickenCoop:
    def __init__(self, chicken_coop_id: int):
        self._chicken_coop_id = chicken_coop_id
        self._chickens = [None] * 10
        self._current_index = 0

    def get_chicken_coop_id(self) -> int:
        return self._chicken_coop_id

    def set_chicken_coop_id(self, chicken_coop_id: int):
        self._chicken_coop_id = chicken_coop_id

    def get_chickens(self) -> list:
        return self._chickens

    def set_chickens(self, chickens: list):
        self._chickens = chickens

    def get_current_index(self) -> int:
        return self._current_index

    def set_current_index(self, current_index: int):
        self._current_index = current_index

    def add(self, chicken: Chicken):
        for existing in self._chickens:
            if existing is not None and existing.get_id() == chicken.get_id():
                print(f"There is already a chicken with id {chicken.get_id()}.")
                return

        for i in range(len(self._chickens)):
            if self._chickens[i] is None:
                self._chickens[i] = chicken
                print(f"Chicken added: {chicken.get_name()}")
                return

        print(f"The coop is full (maximum {len(self._chickens)}).")

    def remove(self, chicken_id: int):
        for i in range(len(self._chickens)):
            if self._chickens[i] is not None and self._chickens[i].get_id() == chicken_id:
                removed_chicken = self._chickens[i]
                for j in range(i, len(self._chickens) - 1):
                    self._chickens[j] = self._chickens[j + 1]
                self._chickens[-1] = None
                print(f"Chicken removed: {removed_chicken.get_name()}")
                return

        print(f"No chicken found with id {chicken_id}.")

    def reset_iteration(self):
        self._current_index = 0
        print("Iteration reset.")

    def next(self) -> Chicken:
        if self._current_index < len(self._chickens) and self._chickens[self._current_index] is not None:
            chicken = self._chickens[self._current_index]
            self._current_index += 1
            return chicken

        print("No more chickens. Reset the iteration to start over.")
        return None

    def __str__(self) -> str:
        return f"ChickenCoop{{chickenCoopId={self._chicken_coop_id}, chickens={self._chickens}, currentIndex={self._current_index}}}"