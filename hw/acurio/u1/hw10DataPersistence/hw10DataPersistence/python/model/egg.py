"""Equivalente de Egg.java."""


class Egg:
    def __init__(self, id: int, size: str):
        self._id = id
        self._size = size

    def get_id(self) -> int:
        return self._id

    def set_id(self, value: int) -> None:
        self._id = value

    def get_size(self) -> str:
        return self._size

    def set_size(self, value: str) -> None:
        self._size = value

    def __str__(self) -> str:
        return f"Egg{{id={self._id}, size={self._size}}}"
