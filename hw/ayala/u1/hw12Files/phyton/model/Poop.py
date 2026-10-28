class Poop:
    def __init__(self, poop_id: int, color: str, consistency: str):
        self.id = poop_id
        self.color = color
        self.consistency = consistency

    def __str__(self):
        return f"Poop{{id={self.id}, color={self.color}, consistency={self.consistency}}}"