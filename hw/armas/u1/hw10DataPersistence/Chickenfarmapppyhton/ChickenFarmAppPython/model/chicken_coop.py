from model.chicken import Chicken


class ChickenCoop:
    CAPACITY = 10

    def __init__(self, chicken_coop_id: int):
        self.chicken_coop_id = chicken_coop_id
        self.chickens = [None] * self.CAPACITY
        self.current_index = 0

    def add(self, chicken: Chicken) -> bool:
        for i in range(self.CAPACITY):
            if self.chickens[i] is None:
                self.chickens[i] = chicken
                print(f"Chicken added: {chicken.get_name()}")
                return True
        print("The coop is full.")
        return False

    def remove(self, chicken_id: int) -> bool:
        for i in range(self.CAPACITY):
            c = self.chickens[i]
            if c is not None and c.get_id() == chicken_id:
                self.chickens[i] = None
                print(f"Chicken {chicken_id} removed.")
                return True
        print(f"No chicken found with id {chicken_id}.")
        return False

    def next(self):
        """Devuelve la siguiente gallina (ignora los espacios vacíos)."""
        for _ in range(self.CAPACITY):
            c = self.chickens[self.current_index]
            self.current_index = (self.current_index + 1) % self.CAPACITY
            if c is not None:
                return c
        return None

    def reset_iteration(self):
        self.current_index = 0

    def __str__(self):
        chickens = [c for c in self.chickens if c is not None]
        return (f"ChickenCoop{{chickenCoopId={self.chicken_coop_id}, "
                f"chickens={chickens}, currentIndex={self.current_index}}}")

    __repr__ = __str__