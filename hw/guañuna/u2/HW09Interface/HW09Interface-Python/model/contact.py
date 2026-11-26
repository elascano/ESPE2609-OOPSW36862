from dataclasses import dataclass, field
from datetime import date
from typing import List


@dataclass
class Contact:
    id: int
    full_name: str
    email: str
    cell_phone_number: str
    instagram: str
    facebook: str
    is_known: bool
    type: str
    gender: str
    hobbies: List[str] = field(default_factory=list)
    birth_date: date = None
    comments: str = ""

    def __str__(self):
        return (f"id={self.id}\n fullName={self.full_name}\n email={self.email}\n "
                f"cellPhoneNumber={self.cell_phone_number}\n instagram={self.instagram}\n "
                f"facebook={self.facebook}\n isKnown={self.is_known}\n type={self.type}\n "
                f"gender={self.gender}\n hobbies={self.hobbies}\n "
                f"birthDate={self.birth_date}\n comments={self.comments}")