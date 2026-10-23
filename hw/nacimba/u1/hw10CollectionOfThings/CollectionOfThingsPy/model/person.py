class Person:
    def __init__(self, id: int, name: str, salary: float):
        self.id = id
        self.name = name
        self.salary = salary

    def __str__(self):
        return f"Person{{id={self.id}, name={self.name}, salary={self.salary}}}"

    # En una lista, Python usa __repr__ para mostrar cada elemento
    __repr__ = __str__
