"""
@author Enrique Ayala, LogicLords,@ESPE
"""

class Egg:
    def __init__(self, egg_id: int, size: str):
        self._id = egg_id
        self._size = size  # 'L', 'M', etc.

    @property
    def id(self) -> int:
        return self._id

    @id.setter
    def id(self, egg_id: int):
        self._id = egg_id

    @property
    def size(self) -> str:
        return self._size

    @size.setter
    def size(self, size: str):
        self._size = size

    def __str__(self) -> str:
        return f"Egg{{id={self._id}, size='{self._size}'}}"