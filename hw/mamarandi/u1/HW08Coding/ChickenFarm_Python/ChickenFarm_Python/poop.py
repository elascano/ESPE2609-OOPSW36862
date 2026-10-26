class Poop:
    def __init__(self, id: int, color: str, consistency: str):
        self.__id = id
        self.__color = color
        self.__consistency = consistency

    def getId(self):
        return self.__id

    def setId(self, id):
        self.__id = id

    def getColor(self):
        return self.__color

    def setColor(self, color):
        self.__color = color

    def getConsistency(self):
        return self.__consistency

    def setConsistency(self, consistency):
        self.__consistency = consistency

    def __str__(self):
        return f"Poop{{id={self.__id}, color={self.__color}, consistency={self.__consistency}}}"
