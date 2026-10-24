"""
@author Paucar Matias, ByteWorks, @ESPE
"""


class Person:

    def __init__(self, id: int, name: str, salary: float):
        self.__id = id
        self.__name = name
        self.__salary = float(salary)

    def get_id(self) -> int:
        return self.__id

    def set_id(self, id: int) -> None:
        self.__id = id

    def get_name(self) -> str:
        return self.__name

    def set_name(self, name: str) -> None:
        self.__name = name

    def get_salary(self) -> float:
        return self.__salary

    def set_salary(self, salary: float) -> None:
        self.__salary = float(salary)

    def __str__(self) -> str:
        return f"id={self.__id}, name={self.__name}, \tsalary={self.__salary}"

    # Para que al imprimir la lista se vea igual que en Java
    __repr__ = __str__
