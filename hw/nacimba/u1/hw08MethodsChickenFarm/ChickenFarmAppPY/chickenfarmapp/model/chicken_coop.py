"""ChickenCoop model."""



class ChickenCoop:
    CAPACITY = 10

    def __init__(self, chicken_coop_id):
        self.chicken_coop_id = chicken_coop_id
        self.chickens = [None] * ChickenCoop.CAPACITY
        self.chicken_count = 0
        self.iterator_index = 0
        self.next_id = 1

    def generate_id(self):
        """Generates a unique, auto-incremental id for a new chicken."""
        new_id = self.next_id
        self.next_id += 1
        return new_id

    def add(self, chicken):
        """Adds a chicken in the first free slot. Returns True if added."""
        if chicken is None:
            print("Cannot add a null chicken.")
            return False
        if self.is_full():
            print(f"The chicken coop is full. Cannot add more chickens. (Max {len(self.chickens)})")
            return False

        for i in range(len(self.chickens)):
            if self.chickens[i] is None:
                self.chickens[i] = chicken
                self.chicken_count += 1
                print(f"Chicken added successfully. Total chickens: {self.chicken_count}")
                return True
        return False

    def remove(self, chicken_id):
        """Removes the chicken with the given id. Returns True if removed."""
        for i in range(len(self.chickens)):
            if self.chickens[i] is not None and self.chickens[i].id == chicken_id:
                self.chickens[i] = None
                self.chicken_count -= 1
                print(f"Chicken with ID {chicken_id} removed.")
                return True
        print(f"Chicken with ID {chicken_id} not found.")
        return False

    def exists_id(self, chicken_id):
        return any(c is not None and c.id == chicken_id for c in self.chickens)

    def reset_iteration(self):
        self.iterator_index = 0

    def next(self):
        while self.iterator_index < len(self.chickens):
            chicken = self.chickens[self.iterator_index]
            self.iterator_index += 1
            if chicken is not None:
                return chicken
        return None

    def is_full(self):
        return self.chicken_count >= len(self.chickens)

    def get_chicken_count(self):
        return self.chicken_count

    def set_chickens(self, chickens):
        self.chickens = chickens
        self.chicken_count = sum(1 for c in chickens if c is not None)
        self.iterator_index = 0
        for c in chickens:
            if c is not None and c.id >= self.next_id:
                self.next_id = c.id + 1

    def __str__(self):
        chickens = "[" + ", ".join(str(c) for c in self.chickens) + "]"
        return f"ChickenCoop{{chickenCoopId={self.chicken_coop_id}, chickens={chickens}}}"
