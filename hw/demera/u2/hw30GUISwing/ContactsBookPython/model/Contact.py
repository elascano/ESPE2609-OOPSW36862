from datetime import date


class Contact:
    def __init__(
        self,
        id: int,
        fullName: str,
        email: str,
        cellPhoneNumber: str,
        instagram: str,
        facebook: str,
        isKnown: bool,
        type: str,
        gender: str,
        hobbies: list[str],
        birthDate: date,
        comments: str,
    ):
        self.id = id
        self.fullName = fullName
        self.email = email
        self.cellPhoneNumber = cellPhoneNumber
        self.instagram = instagram
        self.facebook = facebook
        self.isKnown = isKnown
        self.type = type
        self.gender = gender
        self.hobbies = hobbies
        self.birthDate = birthDate
        self.comments = comments

    def __str__(self) -> str:
        formattedDate = (
            self.birthDate.strftime("%d/%m/%Y") if self.birthDate else "null"
        )
        return (
            f"id={self.id}\n"
            f" fullName={self.fullName}\n"
            f" email={self.email}\n"
            f" cellPhoneNumber={self.cellPhoneNumber}\n"
            f" instagram={self.instagram}\n"
            f" facebook={self.facebook}\n"
            f" isKnown={self.isKnown}\n"
            f" type={self.type}\n"
            f" gender={self.gender}\n"
            f" hobbies={self.hobbies}\n"
            f" birthDate={formattedDate}\n"
            f" comments={self.comments}}}"
        )