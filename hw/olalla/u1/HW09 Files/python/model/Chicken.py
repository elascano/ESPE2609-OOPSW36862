from datetime import date, datetime

class Chicken:
    def __init__(self, chicken_id: int, name: str, color: str,
                 born_on_date: date, age_in_years: int = 0,
                 molting: bool = False):
        self.id = chicken_id
        self.name = name
        self.color = color
        self.born_on_date = born_on_date
        self.age_in_years = age_in_years
        self.molting = molting
        self.set_age_in_years()

    def compute_age_in_years(self) -> int:
        today = date.today()
        years = today.year - self.born_on_date.year
        if (today.month, today.day) < (self.born_on_date.month, self.born_on_date.day):
            years -= 1
        return years

    def set_age_in_years(self):
        self.age_in_years = self.compute_age_in_years()

    def do_stuff(self):
        print(f"The chicken {self.name} is clucking")
        print(f"The chicken {self.name} is wandering")
        print(f"The chicken {self.name} is drinking --> 1.3 ml of water")
        print("poop--> Poop{id=2, color=green, consistency=M}")
        print("egg--> Egg{id=2, size=S}")

    def to_dict(self) -> dict:
        self.set_age_in_years()
        return {
            "id": self.id,
            "name": self.name,
            "color": self.color,
            "bornOnDate": self.born_on_date.isoformat(),
            "ageInYears": self.age_in_years,
            "molting": self.molting
        }

    @staticmethod
    def from_dict(data: dict):
        born = date.fromisoformat(data["bornOnDate"])
        return Chicken(
            int(data["id"]), str(data["name"]), str(data["color"]),
            born, int(data.get("ageInYears", 0)), bool(data["molting"])
        )

    def __str__(self):
        return (f"Chicken{{id={self.id}, name={self.name}, color={self.color}, "
                f"bornOnDate={self.born_on_date}, age={self.age_in_years}, "
                f"molting={str(self.molting).lower()}}}")
