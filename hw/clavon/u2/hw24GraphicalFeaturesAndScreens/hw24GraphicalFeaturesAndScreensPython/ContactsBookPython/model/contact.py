from datetime import datetime

class Contact:
    def __init__(self, id: int, full_name: str, email: str, cell_phone_number: str,
                 instagram: str, facebook: str, is_known: bool, type: str,
                 gender: str, hobbies: list[str], birth_date: datetime, comments: str):
        self.id = id
        self.full_name = full_name
        self.email = email
        self.cell_phone_number = cell_phone_number
        self.instagram = instagram
        self.facebook = facebook
        self.is_known = is_known
        self.type = type
        self.gender = gender
        self.hobbies = hobbies
        self.birth_date = birth_date
        self.comments = comments

    def __str__(self) -> str:
        return (
            f"\n id={self.id}"
            f"\n fullName={self.full_name}"
            f"\n email={self.email}"
            f"\n cellPhoneNumber={self.cell_phone_number}"
            f"\n instagram={self.instagram},"
            f"\n facebook={self.facebook}"
            f"\n isKnown={self.is_known}"
            f"\n type={self.type}"
            f"\n gender={self.gender}"
            f"\n hobbies={self.hobbies}"
            f"\n birthDate={self.birth_date} comments={self.comments}"
        )