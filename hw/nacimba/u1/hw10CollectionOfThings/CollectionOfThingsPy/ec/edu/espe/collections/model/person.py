import struct


def _to_float32(value: float) -> float:
    """Emula el tipo `float` (32 bits) de Java."""
    return struct.unpack(">f", struct.pack(">f", value))[0]


def format_float(value: float) -> str:
    """Formatea un float32 como lo hace Java (ej. 2851.57, 1884.0)."""
    value = _to_float32(value)
    for precision in range(1, 10):
        text = f"{value:.{precision}g}"
        if _to_float32(float(text)) == value:
            break
    number = float(text)
    return str(int(number)) + ".0" if number == int(number) else text


class Person:
    def __init__(self, id: int, name: str, salary: float) -> None:
        self._id = id
        self._name = name
        self._salary = _to_float32(salary)

    def __str__(self) -> str:
        return f"Person{{id={self._id}, name={self._name}, salary={format_float(self._salary)}}}"

    __repr__ = __str__

    @property
    def id(self) -> int:
        return self._id

    @id.setter
    def id(self, value: int) -> None:
        self._id = value

    @property
    def name(self) -> str:
        return self._name

    @name.setter
    def name(self, value: str) -> None:
        self._name = value

    @property
    def salary(self) -> float:
        return self._salary

    @salary.setter
    def salary(self, value: float) -> None:
        self._salary = _to_float32(value)
