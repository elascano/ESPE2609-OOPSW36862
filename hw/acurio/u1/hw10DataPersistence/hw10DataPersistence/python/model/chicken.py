"""Modelo Chicken. Reconstruido a partir de los usos en ChickenFarmApp.java.

IMPORTANTE: faltó Chicken.java entre los archivos compartidos. La lógica exacta
original de doStuff() y el formato original de toString() quedan por confirmar.
"""
from datetime import date


class Chicken:
    def __init__(self, id: int, name: str, color: str, born_on_date: date):
        self._id = id
        self._name = name
        self._color = color
        self._born_on_date = born_on_date
        self._molting = False
        self._age_in_years = 0
        self.set_age_in_years()

    def get_id(self) -> int:
        return self._id

    def set_id(self, value: int) -> None:
        self._id = value

    def get_name(self) -> str:
        return self._name

    def set_name(self, value: str) -> None:
        self._name = value

    def get_color(self) -> str:
        return self._color

    def set_color(self, value: str) -> None:
        self._color = value

    def get_born_on_date(self) -> date:
        return self._born_on_date

    def set_born_on_date(self, value: date) -> None:
        self._born_on_date = value
        self.set_age_in_years()

    def get_age_in_years(self) -> int:
        return self._age_in_years

    def compute_age_in_years(self) -> int:
        today = date.today()
        years = today.year - self._born_on_date.year
        if (today.month, today.day) < (self._born_on_date.month, self._born_on_date.day):
            years -= 1
        return years

    def set_age_in_years(self) -> None:
        self._age_in_years = self.compute_age_in_years()

    def is_molting(self) -> bool:
        return self._molting

    def set_molting(self, value: bool) -> None:
        self._molting = value

    def do_stuff(self) -> None:
        # Comportamiento provisional hasta recibir Chicken.java.
        print(f"{self._name} is doing chicken stuff.")

    def to_dict(self) -> dict:
        return {
            "id": self._id,
            "name": self._name,
            "color": self._color,
            "bornOnDate": self._born_on_date.isoformat(),
            "ageInYears": self._age_in_years,
            "molting": self._molting,
        }

    @classmethod
    def from_dict(cls, data: dict) -> "Chicken":
        if not isinstance(data, dict):
            raise ValueError("Chicken data must be a JSON object")
        chicken_id = data["id"]
        name = data["name"]
        color = data["color"]
        born_on_date = data["bornOnDate"]
        if type(chicken_id) is not int or not isinstance(name, str) or not isinstance(color, str):
            raise ValueError("Invalid chicken id, name or color")
        if not isinstance(born_on_date, str):
            raise ValueError("Invalid birth date")
        molting = data.get("molting", False)
        if not isinstance(molting, bool):
            raise ValueError("Invalid molting value")
        chicken = cls(chicken_id, name, color, date.fromisoformat(born_on_date))
        chicken.set_molting(molting)
        chicken.set_age_in_years()  # Edad calculada de nuevo al cargar.
        return chicken

    def __str__(self) -> str:
        return (
            f"Chicken{{id={self._id}, name={self._name}, color={self._color}, "
            f"bornOnDate={self._born_on_date.isoformat()}, "
            f"ageInYears={self._age_in_years}, molting={str(self._molting).lower()}}}"
        )
