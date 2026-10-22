class Egg:
    def __init__(self, id: int, size: str):
        self.__id = id
        self.__size = size

    def getId(self):
        return self.__id

    def setId(self, id):
        self.__id = id

    def getSize(self):
        return self.__size

    def setSize(self, size):
        self.__size = size

    def __str__(self):
        return f"Egg{{id={self.__id}, size={self.__size}}}"
