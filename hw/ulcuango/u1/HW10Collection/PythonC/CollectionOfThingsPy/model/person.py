class Person:
    """
    @author Josselyn Ulcuango, CodeLex, @ESPE
    """
    def __init__(self, id: int, name: str, salary: float):
        self.__id = id
        self.__name = name
        self.__salary = salary

    def __str__(self):
        return f"Person{{id={self.get_id()}, name={self.get_name()}, salary={self.get_salary()}}}"

    def get_id(self) -> int:
        return self.__id

    def set_id(self, id: int):
        self.__id = id

    def get_name(self) -> str:
        return self.__name

    def set_name(self, name: str):
        self.__name = name

    def get_salary(self) -> float:
        return self.__salary

    def set_salary(self, salary: float):
        self.__salary = salary