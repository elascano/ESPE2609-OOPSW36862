class Egg:
    def __init__(self, id: int, size: str):
        self.id = id
        self.size = size

    def __str__(self) -> str:
        return f"Egg{{id={self.id}, size={self.size}}}"

    __repr__ = __str__
