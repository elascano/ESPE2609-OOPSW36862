class Poop:
    def __init__(self, id: int, color: str, consistency: str):
        self.id = id
        self.color = color
        self.consistency = consistency

    def __str__(self) -> str:
        return f"Poop{{id={self.id}, color={self.color}, consistency={self.consistency}}}"

    __repr__ = __str__
