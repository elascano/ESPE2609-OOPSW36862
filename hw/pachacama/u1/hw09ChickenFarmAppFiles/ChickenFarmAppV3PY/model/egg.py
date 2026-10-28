"""
@author Andres Pachacama, Byte Work, @ESPE
"""


class Egg:

    def __init__(self, id, size):
        self.id = id
        self.size = size

    def __str__(self):
        return "Egg{id=" + str(self.id) + ", size=" + self.size + "}"
