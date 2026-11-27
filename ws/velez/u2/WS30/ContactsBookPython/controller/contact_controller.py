from model.contact import Contact


class ContactController:
    contacts = []

    @classmethod
    def add_contact(cls, contact: Contact) -> None:
        cls.contacts.append(contact)

    @classmethod
    def get_contacts(cls):
        return cls.contacts
