from dataclasses import dataclass
from datetime import datetime


# Equivalente a ec.edu.espe.contactsbook.model.Contact
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
    gender: str            # 'F' o 'M' (char en Java)
    hobbies: list[str]
    birth_date: datetime
    comments: str

    # Equivalente a toString()
    def __str__(self) -> str:
        return (
            f"id={self.id}\n fullName={self.full_name}"
            f"\n email={self.email}\n cellPhoneNumber={self.cell_phone_number}"
            f"\n instagram={self.instagram}\n facebook={self.facebook}"
            f"\n isKnown={self.is_known}\n type={self.type}\n gender={self.gender}"
            f"\n hobbies={', '.join(self.hobbies)}"
            f"\n birthDate={self.birth_date:%d/%m/%Y}\n comments={self.comments}"
        )
