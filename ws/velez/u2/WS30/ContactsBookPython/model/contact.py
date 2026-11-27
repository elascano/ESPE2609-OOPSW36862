from datetime import date


class Contact:
    def __init__(
        self,
        contact_id: int,
        full_name: str,
        email: str,
        cell_phone_number: str,
        instagram: str,
        facebook: str,
        is_known: bool,
        contact_type: str,
        gender: str,
        hobbies: list,
        birth_date: date,
        comments: str,
    ):
        self.id = contact_id
        self.full_name = full_name
        self.email = email
        self.cell_phone_number = cell_phone_number
        self.instagram = instagram
        self.facebook = facebook
        self.is_known = is_known
        self.type = contact_type
        self.gender = gender
        self.hobbies = hobbies
        self.birth_date = birth_date
        self.comments = comments

    def __str__(self) -> str:
        date_str = self.birth_date.strftime("%d/%m/%Y") if self.birth_date else ""
        return (
            f"Contact{{\n"
            f"id={self.id}\n"
            f"fullName={self.full_name}\n"
            f"email={self.email}\n"
            f"cellPhoneNumber={self.cell_phone_number}\n"
            f"instagram={self.instagram}\n"
            f"Facebook={self.facebook}\n"
            f"isKNown={self.is_known}\n"
            f"type={self.type}\n"
            f"gender={self.gender}\n"
            f"hobbies={self.hobbies}\n"
            f"birtDate={date_str}\n"
            f"comments={self.comments}\n}}"
        )
