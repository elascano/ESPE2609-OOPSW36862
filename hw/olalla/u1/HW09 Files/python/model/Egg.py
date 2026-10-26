class Egg:
    def __init__(self, egg_id: int, size: str):
        self.id = egg_id
        self.size = size

    def __str__(self):
        return f"Egg{{id={self.id}, size={self.size}}}"
