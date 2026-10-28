from datetime import datetime


class Chicken:
    def __init__(self, id: int, name: str, color: str, date_of_birth: datetime, is_laying_eggs: bool):
        self.id = id
        self.name = name
        self.color = color
        self.date_of_birth = date_of_birth
        self.is_laying_eggs = is_laying_eggs

    def compute_age_in_years(self) -> int:
        today = datetime.now()
        age = today.year - self.date_of_birth.year
        if (today.month, today.day) < (self.date_of_birth.month, self.date_of_birth.day):
            age -= 1
        return age

    def do_stuff(self) -> None:
        print(f"{self.name} is clucking and scratching the ground...")

    def __repr__(self) -> str:
        return (f"Chicken{{id={self.id}, name={self.name}, color={self.color}, "
                f"dateOfBirth={self.date_of_birth.date()}, isLayingEggs={self.is_laying_eggs}}}")


class Egg:
    def __init__(self, id: int, size: str):
        self.id = id
        self.size = size

    def __repr__(self) -> str:
        return f"Egg{{id={self.id}, size={self.size}}}"


class Poop:
    def __init__(self, id: int, color: str, size: str):
        self.id = id
        self.color = color
        self.size = size

    def __repr__(self) -> str:
        return f"Poop{{id={self.id}, color={self.color}, size={self.size}}}"
