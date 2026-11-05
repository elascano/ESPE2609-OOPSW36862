from typing import Optional
from .chicken import Chicken


class ChickenCoop:
    def __init__(self, chicken_coop_id: int, chickens: list[Optional[Chicken]]):
        self.chicken_coop_id = chicken_coop_id
        self.chickens = chickens
        self.current_index = 0

    def add(self, chicken: Chicken) -> None:
        for i in range(len(self.chickens)):
            if self.chickens[i] is None:
                self.chickens[i] = chicken
                print("Chicken added successfully")
                return
        print("The chicken coop is full")

    def remove(self, chicken_id: int) -> None:
        for i, chicken in enumerate(self.chickens):
            if chicken is not None and chicken.get_id() == chicken_id:
                for j in range(i, len(self.chickens) - 1):
                    self.chickens[j] = self.chickens[j + 1]
                self.chickens[-1] = None
                print("Chicken removed successfully")
                return
        print("Chicken not found")

    def reset_iteration(self) -> None:
        self.current_index = 0

    def next(self) -> Optional[Chicken]:
        while self.current_index < len(self.chickens):
            chicken = self.chickens[self.current_index]
            self.current_index += 1
            if chicken is not None:
                return chicken
        return None

    def get_chicken_coop_id(self) -> int:
        return self.chicken_coop_id

    def set_chicken_coop_id(self, chicken_coop_id: int) -> None:
        self.chicken_coop_id = chicken_coop_id

    def get_chickens(self) -> list[Optional[Chicken]]:
        return self.chickens

    def set_chickens(self, chickens: list[Optional[Chicken]]) -> None:
        self.chickens = chickens
