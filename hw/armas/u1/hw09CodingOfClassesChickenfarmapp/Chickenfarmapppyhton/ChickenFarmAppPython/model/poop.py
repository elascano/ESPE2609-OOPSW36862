class Poop:
    def __init__(self, id_poop: int, color: str, consistency: str):
        self._id = id_poop
        self._color = color
        self._consistency = consistency

    def get_id(self) -> int:
        return self._id

    def set_id(self, id_poop: int):
        self._id = id_poop

    def get_color(self) -> str:
        return self._color

    def set_color(self, color: str):
        self._color = color

    def get_consistency(self) -> str:
        return self._consistency

    def set_consistency(self, consistency: str):
        self._consistency = consistency
        
        
    def __repr__(self) -> str:
        return self.__str__()

    def __str__(self) -> str:
        return f"Poop{{id={self._id}, color={self._color}, consistency={self._consistency}}}"