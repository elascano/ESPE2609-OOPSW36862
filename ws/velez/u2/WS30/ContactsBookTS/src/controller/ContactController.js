"use strict";
class ContactController {
    static addContact(contact) {
        ContactController.contacts.push(contact);
    }
    static getContacts() {
        return ContactController.contacts;
    }
}
ContactController.contacts = [];
