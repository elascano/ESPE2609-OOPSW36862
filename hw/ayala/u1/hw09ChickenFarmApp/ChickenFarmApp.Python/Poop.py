"""
@author Enrique Ayala, LogicLords,@ESPE
"""

class Poop:
    def __init__(self, poop_id: int, color: str, consistency: str):
        self._id = poop_id
        self._color = color
        self._consistency = consistency

    @property
    def id(self) -> int:
        return self._id

    @id.setter
    def id(self, poop_id: int):
        self._id = poop_id

    @property
    def color(self) -> str:
        return self._color

    @color.setter
    def color(self, color: str):
        self._color = color

    @property
    def consistency(self) -> str:
        return self._consistency

    @consistency.setter
    def consistency(self, consistency: str):
        self._consistency = consistency

    def __str__(self) -> str:
        return f"Poop{{id={self._id}, color='{self._color}', consistency='{self._consistency}'}}"