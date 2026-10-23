import random

class ChickenCoop:
    def __init__(self, chicken_coop_id, chickens=None):
        self.chicken_coop_id = chicken_coop_id
        self.chickens = chickens if chickens is not None else [None] * 10
        self.current_index = 0

    def generate_unique_id(self):
        while True:
            generated_id = random.randint(1, 100)
            is_duplicate = False

            for c in self.chickens:
                if c is not None and c.get_id() == generated_id:
                    is_duplicate = True
                    break

            if not is_duplicate:
                return generated_id

    def add(self, chicken):
        if chicken is None:
            return False
        for i in range(len(self.chickens)):
            if self.chickens[i] is None:
                self.chickens[i] = chicken
                return True
        return False

    def remove(self, chicken_id):
        for i in range(len(self.chickens)):
            if self.chickens[i] is not None and self.chickens[i].get_id() == chicken_id:
                self.chickens[i] = None
                return True
        return False

    def reset_iteration(self):
        self.current_index = 0

    def next(self):
        while self.current_index < len(self.chickens):
            current = self.chickens[self.current_index]
            self.current_index += 1
            if current is not None:
                return current
        return None

    def get_current_position(self):
        return self.current_index

    def get_count(self):
        count = 0
        for c in self.chickens:
            if c is not None:
                count += 1
        return count

    def get_chicken_coop_id(self):
        return self.chicken_coop_id

    def set_chicken_coop_id(self, chicken_coop_id):
        self.chicken_coop_id = chicken_coop_id

    def get_chickens(self):
        return self.chickens

    def set_chickens(self, chickens):
        self.chickens = chickens

    def __str__(self):
        return f"ChickenCoop{{chickenCoopId={self.chicken_coop_id}, chickens={self.chickens}}}"