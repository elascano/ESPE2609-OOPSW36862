from typing import List, Optional
from models import Chicken


class ChickenCoop:
    def __init__(self, chicken_coop_id: int = 0, chickens: Optional[List[Optional[Chicken]]] = None):
        self.chicken_coop_id = chicken_coop_id
        self.chickens: List[Optional[Chicken]] = chickens if chickens is not None else [None] * 10
        self.current_index = 0

    def add(self, chicken: Chicken) -> None:
        """Adds a chicken to the first available slot (None) in the list."""
        for i in range(len(self.chickens)):
            if self.chickens[i] is None:
                self.chickens[i] = chicken
                return
        print("The chicken coop is full. Cannot add more chickens.")

    def remove(self, chicken_id: int) -> None:
        """Removes a chicken by matching its ID."""
        for i in range(len(self.chickens)):
            chicken = self.chickens[i]
            if chicken is not None and chicken.id == chicken_id:
                self.chickens[i] = None
                return
        print(f"Chicken with ID {chicken_id} was not found.")

    def reset_iteration(self) -> None:
        """Resets the iteration index back to 0."""
        self.current_index = 0

    def next(self) -> Optional[Chicken]:
        """Returns the current chicken and moves the index forward."""
        while self.current_index < len(self.chickens):
            current = self.chickens[self.current_index]
            self.current_index += 1
            if current is not None:
                return current
        return None

    def __repr__(self) -> str:
        return f"ChickenCoop{{chickenCoopId={self.chicken_coop_id}, chickens={self.chickens}}}"
