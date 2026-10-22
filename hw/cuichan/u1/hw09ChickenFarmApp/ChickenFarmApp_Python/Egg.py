class Egg:
    def __init__(self, id, size):
        self.id = id
        self.size = size

    def get_id(self):
        return self.id

    def set_id(self, id):
        self.id = id

    def get_size(self):
        return self.size

    def set_size(self, size):
        self.size = size

    def __str__(self):
        return f"Egg{{id={self.get_id()}, size={self.get_size()}}}"