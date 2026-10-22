class ChickenCoop:
    def __init__(self, chickenCoopId: int, chickens: list):
        self.__chickenCoopId = chickenCoopId
        self.__chickens = chickens
        self.__currentIndex = 0

    def add(self, chicken):
        for i in range(len(self.__chickens)):
            if self.__chickens[i] is None:
                self.__chickens[i] = chicken
                print("Chicken added successfully")
                return
        print("The chicken coop is full")

    def remove(self, chickenId: int):
        for i, chicken in enumerate(self.__chickens):
            if chicken is not None and chicken.getId() == chickenId:
                for j in range(i, len(self.__chickens) - 1):
                    self.__chickens[j] = self.__chickens[j + 1]
                self.__chickens[-1] = None
                print("Chicken removed successfully")
                return
        print("Chicken not found")

    def resetIteration(self):
        self.__currentIndex = 0

    def next(self):
        while self.__currentIndex < len(self.__chickens):
            chicken = self.__chickens[self.__currentIndex]
            self.__currentIndex += 1
            if chicken is not None:
                return chicken
        return None

    def getChickenCoopId(self):
        return self.__chickenCoopId

    def setChickenCoopId(self, chickenCoopId):
        self.__chickenCoopId = chickenCoopId

    def getChickens(self):
        return self.__chickens

    def setChickens(self, chickens):
        self.__chickens = chickens
        self.resetIteration()

    def __str__(self):
        return f"ChickenCoop{{chickenCoopId={self.__chickenCoopId}, chickens={self.__chickens}}}"
