"""
@author Enrique Ayala, LogicLords,@ESPE
"""

from typing import List, Optional
from datetime import date
import Chicken

class ChickenCoop:
    MAX_CAPACITY = 10

    def __init__(self, chicken_coop_id: int):
        self._chicken_coop_id = chicken_coop_id
        self._chickens: List[Optional[Chicken.Chicken]] = [None] * self.MAX_CAPACITY
        self._count = 0
        self._current_index = 0

    def add(self, chicken: Chicken.Chicken) -> bool:
        if self._find_index_by_id(chicken.id) != -1:
            print(f"There is already a chicken with id {chicken.id}")
            return False
        if self._count >= self.MAX_CAPACITY:
            print(f"The coop is full (maximum {self.MAX_CAPACITY}).")
            return False
        
        self._chickens[self._count] = chicken
        self._count += 1
        return True

    def remove(self, chicken_id: int) -> bool:
        index = self._find_index_by_id(chicken_id)
        if index == -1:
            print(f"There is no chicken with id {chicken_id}")
            return False
        
        for i in range(index, self._count - 1):
            self._chickens[i] = self._chickens[i + 1]
        
        self._chickens[self._count - 1] = None
        self._count -= 1
        return True

    def update(self, chicken_id: int, name: str, color: str, born_on_date: date, molting: bool) -> bool:
        index = self._find_index_by_id(chicken_id)
        if index == -1:
            print(f"There is no chicken with id {chicken_id}")
            return False
        
        chicken = self._chickens[index]
        chicken.name = name
        chicken.color = color
        chicken.born_on_date = born_on_date
        chicken.molting = molting
        chicken.set_age_in_years()
        return True

    def _find_index_by_id(self, chicken_id: int) -> int:
        for i in range(self._count):
            if self._chickens[i].id == chicken_id:
                return i
        return -1

    def to_array(self) -> List[Chicken.Chicken]:
        return [c for c in self._chickens[:self._count] if c is not None]

    def reset_iteration(self):
        self._current_index = 0

    def next(self) -> Optional[Chicken.Chicken]:
        if self._current_index >= self._count:
            return None
        chicken = self._chickens[self._current_index]
        self._current_index += 1
        return chicken

    @property
    def chicken_coop_id(self) -> int:
        return self._chicken_coop_id

    @chicken_coop_id.setter
    def chicken_coop_id(self, chicken_coop_id: int):
        self._chicken_coop_id = chicken_coop_id

    @property
    def count(self) -> int:
        return self._count

    def __str__(self) -> str:
        text = f"ChickenCoop{{id={self._chicken_coop_id}, chickens={self._count}"
        for i in range(self._count):
            text += f"\n  {self._chickens[i]}"
        return text + "}"