from typing import List, Optional
from chicken import Chicken

class ChickenCoop:
    def __init__(self, chicken_coop_id: int, chickens: Optional[List[Chicken]] = None):
        self._chicken_coop_id = chicken_coop_id
        self._chickens: List[Optional[Chicken]] = [None] * 10
        self._count = 0
        self._current_index = 0

        if chickens is not None:
            for chicken in chickens:
                if chicken is not None:
                    self.add(chicken)

    def add(self, chicken: Chicken) -> bool:
        if chicken is None or self._count >= len(self._chickens):
            return False
        self._chickens[self._count] = chicken
        self._count += 1
        return True

    def remove(self, chicken_id: int) -> bool:
        index_found = -1
        for i in range(self._count):
            ch = self._chickens[i]
            if ch is not None and ch.id == chicken_id:
                index_found = i
                break

        if index_found != -1:
            for i in range(index_found, self._count - 1):
                self._chickens[i] = self._chickens[i + 1]
            self._chickens[self._count - 1] = None
            self._count -= 1
            return True
        return False

    def reset_iteration(self) -> None:
        self._current_index = 0

    def next(self) -> Optional[Chicken]:
        if self._current_index < self._count:
            current_chicken = self._chickens[self._current_index]
            self._current_index += 1
            return current_chicken
        return None

    def get_current_position(self) -> int:
        return self._current_index

    def get_current_chicken(self) -> Optional[Chicken]:
        if 0 < self._current_index <= self._count:
            return self._chickens[self._current_index - 1]
        return None

    @property
    def chicken_coop_id(self) -> int:
        return self._chicken_coop_id

    @chicken_coop_id.setter
    def chicken_coop_id(self, value: int) -> None:
        self._chicken_coop_id = value

    @property
    def count(self) -> int:
        return self._count

    def __str__(self) -> str:
        active_chickens = [str(self._chickens[i]) for i in range(self._count)]
        chickens_str = ", ".join(active_chickens)
        return f"ChickenCoop{{chickenCoopId={self._chicken_coop_id}, totalChickens={self._count}, chickens=[{chickens_str}]}}"