# @author David Clavón, Infinite loops, @ESPE

class Food:
    def __init__(self, description: str):
        self.__description = description

    def get_description(self) -> str:
        return self.__description

    def set_description(self, description: str) -> None:
        self.__description = description

    def __str__(self) -> str:
        return self.__description