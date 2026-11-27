class ContactController {
    private static contacts: Contact[] = [];

    static addContact(contact: Contact): void {
        ContactController.contacts.push(contact);
    }

    static getContacts(): Contact[] {
        return ContactController.contacts;
    }
}
