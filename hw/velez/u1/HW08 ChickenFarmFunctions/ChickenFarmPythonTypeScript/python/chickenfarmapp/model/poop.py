class Poop:
    def __init__(self, poop_id: int, color: str, consistency: str):
        self.id = poop_id
        self.color = color
        self.consistency = consistency

    def __str__(self) -> str:
        return (
            f"Poop{{id={self.id}, color={self.color}, "
            f"consistency={self.consistency}}}"
        )

    def get_id(self) -> int:
        return self.id

    def set_id(self, poop_id: int) -> None:
        self.id = poop_id

    def get_color(self) -> str:
        return self.color

    def set_color(self, color: str) -> None:
        self.color = color

    def get_consistency(self) -> str:
        return self.consistency

    def set_consistency(self, consistency: str) -> None:
        self.consistency = consistency
