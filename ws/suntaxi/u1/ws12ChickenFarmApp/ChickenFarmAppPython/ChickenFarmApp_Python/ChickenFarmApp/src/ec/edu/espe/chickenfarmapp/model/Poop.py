class Poop:
    def __init__(self, id_poop: int, weight: float):
        self.id_poop = id_poop
        self.weight = weight

    def __str__(self):
        return f"Poop(id={self.id_poop}, weight={self.weight}g)"
