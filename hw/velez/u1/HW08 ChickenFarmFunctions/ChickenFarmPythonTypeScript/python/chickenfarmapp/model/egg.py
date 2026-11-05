class Egg:
    def __init__(self, egg_id: int, size: str):
        self.id = egg_id
        self.size = size

    def __str__(self) -> str:
        return f"Egg{{id={self.id}, size={self.size}}}"

    def get_id(self) -> int:
        return self.id

    def set_id(self, egg_id: int) -> None:
        self.id = egg_id

    def get_size(self) -> str:
        return self.size

    def set_size(self, size: str) -> None:
        self.size = size
