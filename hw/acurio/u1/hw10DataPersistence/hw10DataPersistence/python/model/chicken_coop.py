"""Gallinero con capacidad fija de 10, como ChickenCoop.java."""
from typing import Optional
from model.chicken import Chicken


class ChickenCoop:
    CAPACITY = 10

    def __init__(self, chicken_coop_id: int):
        self._chicken_coop_id = chicken_coop_id
        self._chickens: list[Optional[Chicken]] = [None] * self.CAPACITY
        self._current_index = 0

    def add(self, chicken: Chicken) -> bool:
        for existing in self._chickens:
            if existing is not None and existing.get_id() == chicken.get_id():
                print(f"There is already a chicken with id {chicken.get_id()}.")
                return False

        for i, existing in enumerate(self._chickens):
            if existing is None:
                self._chickens[i] = chicken
                print(f"Chicken added: {chicken.get_name()}")
                return True

        print(f"The coop is full (maximum {self.CAPACITY}).")
        return False

    def remove(self, chicken_id: int) -> bool:
        for i, existing in enumerate(self._chickens):
            if existing is not None and existing.get_id() == chicken_id:
                name = existing.get_name()
                for j in range(i, self.CAPACITY - 1):
                    self._chickens[j] = self._chickens[j + 1]
                self._chickens[-1] = None
                print(f"Chicken removed: {name}")
                return True

        print(f"No chicken found with id {chicken_id}.")
        return False

    def reset_iteration(self) -> None:
        self._current_index = 0
        print("Iteration reset.")

    def next(self) -> None:
        if self._current_index < self.CAPACITY and self._chickens[self._current_index] is not None:
            print(self._chickens[self._current_index])
            self._current_index += 1
        else:
            print("No more chickens. Reset the iteration to start over.")

    def get_chicken_coop_id(self) -> int:
        return self._chicken_coop_id

    def set_chicken_coop_id(self, value: int) -> None:
        self._chicken_coop_id = value

    def get_chickens(self) -> list[Optional[Chicken]]:
        return self._chickens

    def set_chickens(self, chickens: list[Optional[Chicken]]) -> None:
        if len(chickens) != self.CAPACITY:
            raise ValueError("The coop must have exactly 10 positions")
        self._chickens = chickens

    def get_current_index(self) -> int:
        return self._current_index

    def set_current_index(self, value: int) -> None:
        self._current_index = value

    def __str__(self) -> str:
        chickens_text = ", ".join(str(c) if c is not None else "null" for c in self._chickens)
        return (
            f"ChickenCoop{{chickenCoopId={self._chicken_coop_id}, "
            f"chickens=[{chickens_text}], currentIndex={self._current_index}}}"
        )
