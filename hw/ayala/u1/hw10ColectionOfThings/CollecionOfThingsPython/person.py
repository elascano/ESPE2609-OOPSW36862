"""
@author Enrique Ayala, Byte and LogicalLords, @ESPE
"""

class Person:
    def __init__(self, person_id: int, name: str, salary: float):
        self._id = person_id
        self._name = name
        self._salary = salary

    @property
    def id(self) -> int:
        return self._id

    @id.setter
    def id(self, person_id: int):
        self._id = person_id

    @property
    def name(self) -> str:
        return self._name

    @name.setter
    def name(self, name: str):
        self._name = name

    @property
    def salary(self) -> float:
        return self._salary

    @salary.setter
    def salary(self, salary: float):
        self._salary = salary

    def __str__(self) -> str:
        return f"id={self._id}, name={self._name}, \tsalary={self._salary}"

    def __repr__(self) -> str:
        return self.__str__()