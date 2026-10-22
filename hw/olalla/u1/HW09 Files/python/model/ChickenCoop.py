class ChickenCoop:
    def __init__(self, chicken_coop_id: int, chickens=None, capacity: int = 10):
        self.chicken_coop_id = chicken_coop_id
        self.capacity = capacity
        self.chickens = list(chickens) if chickens is not None else []
        self.chickens = self.chickens[:capacity]
        self.current_index = 0

    def add(self, chicken):
        if len(self.chickens) >= self.capacity:
            print("The chicken coop is full.")
            return False
        self.chickens.append(chicken)
        print("Chicken added successfully.")
        return True

    def remove(self, chicken_id: int):
        for index, chicken in enumerate(self.chickens):
            if chicken.id == chicken_id:
                self.chickens.pop(index)
                print("Chicken removed successfully.")
                return True
        print("Chicken not found.")
        return False

    def reset_iteration(self):
        self.current_index = 0

    def next(self):
        if self.current_index >= len(self.chickens):
            return None
        chicken = self.chickens[self.current_index]
        self.current_index += 1
        return chicken
