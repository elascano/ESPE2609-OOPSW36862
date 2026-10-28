class Egg:

    def __init__(self, id_egg: int, size: str):
        self._id = id_egg
        self._size = size

    def get_id(self) -> int:
        return self._id

    def set_id(self, id_egg: int):
        self._id = id_egg

    def get_size(self) -> str:
        return self._size

    def set_size(self, size: str):
        self._size = size

    def __str__(self) -> str:
        return f"Egg{{id={self._id}, size={self._size}}}"

    __repr__ = __str__