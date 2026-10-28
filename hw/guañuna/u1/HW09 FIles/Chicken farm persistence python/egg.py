class Egg:
    def __init__(self, id, size):
        self.id = id
        self.size = size

    def __str__(self):
        return f"Egg{{id={self.id}, size={self.size}}}"