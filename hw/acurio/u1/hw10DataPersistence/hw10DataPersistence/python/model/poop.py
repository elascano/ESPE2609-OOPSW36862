"""Equivalente de Poop.java."""


class Poop:
    def __init__(self, id: int, color: str, consistency: str):
        self._id = id
        self._color = color
        self._consistency = consistency

    def get_id(self) -> int:
        return self._id

    def set_id(self, value: int) -> None:
        self._id = value

    def get_color(self) -> str:
        return self._color

    def set_color(self, value: str) -> None:
        self._color = value

    def get_consistency(self) -> str:
        return self._consistency

    def set_consistency(self, value: str) -> None:
        self._consistency = value

    def __str__(self) -> str:
        return f"Poop{{id={self._id}, color={self._color}, consistency={self._consistency}}}"
