"""
@author Josselyn Ulcuango, Codelex, @ESPE
"""

class Egg:
    def __init__(self, egg_id: int, size: str):
        self.id = egg_id
        self.size = size

    def __str__(self):
        return f"Egg{{id={self.id}, size={self.size}}}"

    # Getters y Setters
    def get_id(self):
        return self.id

    def set_id(self, egg_id):
        self.id = egg_id

    def get_size(self):
        return self.size

    def set_size(self, size):
        self.size = size