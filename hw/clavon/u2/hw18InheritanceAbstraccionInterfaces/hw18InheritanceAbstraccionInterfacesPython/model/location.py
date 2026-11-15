# @author David Clavón, Infinite loops, @ESPE

class Location:
    def __init__(self, id: int, description: str):
        self.__id = id
        self.__description = description

    def get_id(self) -> int:
        return self.__id

    def set_id(self, id: int) -> None:
        self.__id = id

    def get_description(self) -> str:
        return self.__description

    def set_description(self, description: str) -> None:
        self.__description = description

    def __str__(self) -> str:
        return f"Location(id={self.__id}, description='{self.__description}')"