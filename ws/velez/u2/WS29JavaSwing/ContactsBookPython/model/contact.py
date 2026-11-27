from datetime import datetime


class Contact:
    def __init__(
        self,
        ide: int,
        full_name: str,
        email: str,
        cell_phone_number: str,
        instagram: str,
        facebook: str,
        is_known: bool,
        contact_type: str,
        gender: str,
        hobbies: list,
        birth_date: datetime,
        comments: str,
    ):
        self.id = ide
        self.fullName = full_name
        self.email = email
        self.cellPhoneNumber = cell_phone_number
        self.instagram = instagram
        self.Facebook = facebook
        self.isKNown = is_known
        self.type = contact_type
        self.gender = gender
        self.hobbies = hobbies
        self.birtDate = birth_date
        self.comments = comments

    def __str__(self) -> str:
        return (
            f"Contact{{\n"
            f"id={self.id}\n"
            f"fullName={self.fullName}\n"
            f"email={self.email}\n"
            f"cellPhoneNumber={self.cellPhoneNumber}\n"
            f"instagram={self.instagram}\n"
            f"Facebook={self.Facebook}\n"
            f"isKNown={self.isKNown}\n"
            f"type={self.type}\n"
            f"gender={self.gender}\n"
            f"hobbies={self.hobbies}\n"
            f"birtDate={self.birtDate}\n"
            f"comments={self.comments}\n}}"
        )
