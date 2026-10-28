from typing import List, Optional

from chicken import Chicken

CAPACITY = 10


class ChickenCoop:
    def __init__(self, chicken_coop_id: int):
        self.chicken_coop_id = chicken_coop_id
        self.chickens: List[Optional[Chicken]] = [None] * CAPACITY
        self.current_index = 0

    def add(self, chicken: Chicken) -> None:
        for existing in self.chickens:
            if existing is not None and existing.id == chicken.id:
                print(f"There is already a chicken with id {chicken.id}.")
                return
        for i in range(len(self.chickens)):
            if self.chickens[i] is None:
                self.chickens[i] = chicken
                print(f"Chicken added: {chicken.name}")
                return
        print(f"The coop is full (maximum {len(self.chickens)}).")

    def remove(self, chicken_id: int) -> None:
        for i, chicken in enumerate(self.chickens):
            if chicken is not None and chicken.id == chicken_id:
                name = chicken.name
                for j in range(i, len(self.chickens) - 1):
                    self.chickens[j] = self.chickens[j + 1]
                self.chickens[-1] = None
                print(f"Chicken removed: {name}")
                return
        print(f"No chicken found with id {chicken_id}.")

    def reset_iteration(self) -> None:
        self.current_index = 0
        print("Iteration reset.")

    def next(self) -> None:
        if self.current_index < len(self.chickens) and self.chickens[self.current_index] is not None:
            print(self.chickens[self.current_index])
            self.current_index += 1
        else:
            print("No more chickens. Reset the iteration to start over.")

    def __str__(self) -> str:
        return (
            f"ChickenCoop{{chickenCoopId={self.chicken_coop_id}, "
            f"chickens={self.chickens}, currentIndex={self.current_index}}}"
        )
