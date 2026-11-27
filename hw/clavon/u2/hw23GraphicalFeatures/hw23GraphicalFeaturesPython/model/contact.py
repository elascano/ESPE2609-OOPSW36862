from datetime import datetime


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
        birth_date: datetime,
        comments: str,
    ):
        self.__id = id
        self.__full_name = full_name
        self.__email = email
        self.__cell_phone_number = cell_phone_number
        self.__instagram = instagram
        self.__facebook = facebook
        self.__is_known = is_known
        self.__type = type
        self.__gender = gender
        self.__hobbies = hobbies
        self.__birth_date = birth_date
        self.__comments = comments

    def __str__(self) -> str:
        return (
            f"\n id={self.__id}"
            f"\n fullName={self.__full_name}"
            f"\n email={self.__email}"
            f"\n cellPhoneNumber={self.__cell_phone_number}"
            f"\n instagram={self.__instagram},"
            f"\n facebook={self.__facebook}"
            f"\n isKnown={self.__is_known}"
            f"\n type={self.__type}"
            f"\n gender={self.__gender}"
            f"\n hobbies={self.__hobbies}"
            f"\n birthDate={self.__birth_date} comments={self.__comments}"
        )

    def get_id(self) -> int:
        return self.__id

    def set_id(self, id: int) -> None:
        self.__id = id

    def get_full_name(self) -> str:
        return self.__full_name

    def set_full_name(self, full_name: str) -> None:
        self.__full_name = full_name

    def get_email(self) -> str:
        return self.__email

    def set_email(self, email: str) -> None:
        self.__email = email

    def get_cell_phone_number(self) -> str:
        return self.__cell_phone_number

    def set_cell_phone_number(self, cell_phone_number: str) -> None:
        self.__cell_phone_number = cell_phone_number

    def get_instagram(self) -> str:
        return self.__instagram

    def set_instagram(self, instagram: str) -> None:
        self.__instagram = instagram

    def get_facebook(self) -> str:
        return self.__facebook

    def set_facebook(self, facebook: str) -> None:
        self.__facebook = facebook

    def is_is_known(self) -> bool:
        return self.__is_known

    def set_is_known(self, is_known: bool) -> None:
        self.__is_known = is_known

    def get_type(self) -> str:
        return self.__type

    def set_type(self, type: str) -> None:
        self.__type = type

    def get_gender(self) -> str:
        return self.__gender

    def set_gender(self, gender: str) -> None:
        self.__gender = gender

    def get_hobbies(self) -> list[str]:
        return self.__hobbies

    def set_hobbies(self, hobbies: list[str]) -> None:
        self.__hobbies = hobbies

    def get_birth_date(self) -> datetime:
        return self.__birth_date

    def set_birth_date(self, birth_date: datetime) -> None:
        self.__birth_date = birth_date

    def get_comments(self) -> str:
        return self.__comments

    def set_comments(self, comments: str) -> None:
        self.__comments = comments