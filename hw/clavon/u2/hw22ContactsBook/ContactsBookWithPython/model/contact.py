from datetime import date


class Contact:
    def __init__(
        self,
        id: int,
        full_name: str,
        email: str,
        cell_phone_number: str,
        instagram: str,
        facebook: str,
        is_known: bool,
        type: str,
        gender: str,
        hobbies: list[str],
        birth_date: date,
        comments: str,
    ):
        self._id = id
        self._full_name = full_name
        self._email = email
        self._cell_phone_number = cell_phone_number
        self._instagram = instagram
        self._facebook = facebook
        self._is_known = is_known
        self._type = type
        self._gender = gender
        self._hobbies = hobbies
        self._birth_date = birth_date
        self._comments = comments

    def __str__(self) -> str:
        return (
            f"\n id={self._id}\n fullName={self._full_name}"
            f"\n email={self._email}\n cellPhoneNumber={self._cell_phone_number}"
            f"\n instagram={self._instagram},\n facebook={self._facebook}"
            f"\n isKnown={self._is_known}\n type={self._type}"
            f"\n gender={self._gender}\n hobbies={self._hobbies}"
            f"\n birthDate={self._birth_date} comments={self._comments}"
        )

    @property
    def id(self) -> int:
        return self._id

    @id.setter
    def id(self, id: int):
        self._id = id

    @property
    def full_name(self) -> str:
        return self._full_name

    @full_name.setter
    def full_name(self, full_name: str):
        self._full_name = full_name

    @property
    def email(self) -> str:
        return self._email

    @email.setter
    def email(self, email: str):
        self._email = email

    @property
    def cell_phone_number(self) -> str:
        return self._cell_phone_number

    @cell_phone_number.setter
    def cell_phone_number(self, cell_phone_number: str):
        self._cell_phone_number = cell_phone_number

    @property
    def instagram(self) -> str:
        return self._instagram

    @instagram.setter
    def instagram(self, instagram: str):
        self._instagram = instagram

    @property
    def facebook(self) -> str:
        return self._facebook

    @facebook.setter
    def facebook(self, facebook: str):
        self._facebook = facebook

    @property
    def is_known(self) -> bool:
        return self._is_known

    @is_known.setter
    def is_known(self, is_known: bool):
        self._is_known = is_known

    @property
    def type(self) -> str:
        return self._type

    @type.setter
    def type(self, type: str):
        self._type = type

    @property
    def gender(self) -> str:
        return self._gender

    @gender.setter
    def gender(self, gender: str):
        self._gender = gender

    @property
    def hobbies(self) -> list[str]:
        return self._hobbies

    @hobbies.setter
    def hobbies(self, hobbies: list[str]):
        self._hobbies = hobbies

    @property
    def birth_date(self) -> date:
        return self._birth_date

    @birth_date.setter
    def birth_date(self, birth_date: date):
        self._birth_date = birth_date

    @property
    def comments(self) -> str:
        return self._comments

    @comments.setter
    def comments(self, comments: str):
        self._comments = comments