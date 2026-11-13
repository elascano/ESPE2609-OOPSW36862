from abc import ABC, abstractmethod

class FarmAnimal(ABC):
    def __init__(self, name: str, age: int, breed: str):
        self.name = name
        self.age = age
        self.breed = breed

    @abstractmethod
    def make_sound(self) -> str:
        pass

    def __str__(self):
        return f"{self.__class__.__name__} [Nombre: {self.name}, Edad: {self.age}, Raza: {self.breed}]"
