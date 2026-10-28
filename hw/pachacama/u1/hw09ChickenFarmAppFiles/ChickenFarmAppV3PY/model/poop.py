"""
@author Andres Pachacama, Byte Work, @ESPE
"""


class Poop:

    def __init__(self, id, color, consistency):
        self.id = id
        self.color = color
        self.consistency = consistency

    def __str__(self):
        return ("Poop{id=" + str(self.id) + ", color=" + self.color
                + ", consistency=" + self.consistency + "}")
