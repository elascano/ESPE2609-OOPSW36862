"""
@author Josselyn Ulcuango, Codelex, @ESPE
"""

class Poop:
    def __init__(self, poop_id: int, color: str, consistency: str):
        self.id = poop_id
        self.color = color
        self.consistency = consistency

    def __str__(self):
        return f"Poop{{id={self.id}, color={self.color}, consistency={self.consistency}}}"

    # Getters y Setters
    def get_id(self):
        return self.id

    def set_id(self, poop_id):
        self.id = poop_id

    def get_color(self):
        return self.color

    def set_color(self, color):
        self.color = color

    def get_consistency(self):
        return self.consistency

    def set_consistency(self, consistency):
        self.consistency = consistency