"""
@author Paucar Matias, ByteWorks, @ESPE
"""
from model.chicken import Chicken


class ChickenCoop:
    # we need to know the maximum, in this case is 10
    MAX_CHICKENS = 10

    def __init__(self, chicken_coop_id: int):
        self.__chicken_coop_id = chicken_coop_id
        self.__chickens = [None] * ChickenCoop.MAX_CHICKENS
        self.__current_index = 0
        self.__number_of_chickens = 0

    # returns True if the chicken was added, False if the coop is full
    def add(self, chicken: Chicken) -> bool:
        # if we have less than 10 chickens, we can add one more
        # else we can not add any more
        if self.__number_of_chickens < len(self.__chickens):
            self.__chickens[self.__number_of_chickens] = chicken
            self.__number_of_chickens += 1
            return True
        return False

    # returns True if the chicken was removed, False if there is no chicken with that id
    def remove(self, chicken_id: int) -> bool:
        position = -1

        # we look for the position of the chicken with that id
        for i in range(self.__number_of_chickens):
            if self.__chickens[i].get_id() == chicken_id:
                position = i
                break

        if position == -1:
            return False

        # we move the next chickens one position to the left
        for i in range(position, self.__number_of_chickens - 1):
            self.__chickens[i] = self.__chickens[i + 1]

        self.__chickens[self.__number_of_chickens - 1] = None
        self.__number_of_chickens -= 1
        return True

    def find_by_id(self, chicken_id: int):
        for i in range(self.__number_of_chickens):
            if self.__chickens[i].get_id() == chicken_id:
                return self.__chickens[i]
        return None

    def reset_iteration(self):
        self.__current_index = 0

    def has_next(self) -> bool:
        return self.__current_index < self.__number_of_chickens

    def next(self):
        if self.has_next():
            chicken = self.__chickens[self.__current_index]
            self.__current_index += 1
            return chicken
        return None

    def __str__(self):
        chickens_text = ", ".join(str(c) for c in self.__chickens)
        return ("ChickenCoop{chickenCoopId=" + str(self.__chicken_coop_id)
                + ", numberOfChickens=" + str(self.__number_of_chickens)
                + ", chickens=[" + chickens_text + "]}")

    # getters and setters
    def get_chicken_coop_id(self) -> int:
        return self.__chicken_coop_id

    def set_chicken_coop_id(self, chicken_coop_id: int):
        self.__chicken_coop_id = chicken_coop_id

    def get_chickens(self) -> list:
        return self.__chickens

    def get_number_of_chickens(self) -> int:
        return self.__number_of_chickens
