# @author Andrés Pachacama, ByteWorks, @ESPE


class Food:

    def __str__(self) -> str:
        return self._description

    def __init__(self, description: str):
        self._description = description

    @property
    def description(self) -> str:
        return self._description

    @description.setter
    def description(self, description: str):
        self._description = description
