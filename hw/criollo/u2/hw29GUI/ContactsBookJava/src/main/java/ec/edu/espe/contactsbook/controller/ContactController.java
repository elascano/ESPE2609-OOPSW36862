package ec.edu.espe.contactsbook.controller;

import ec.edu.espe.contactsbook.model.Contact;
import java.util.ArrayList;
import java.util.List;

public class ContactController {
    private final List<Contact> contacts;
    private int nextId = 1;

    public ContactController() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contact.setId(nextId++);
        contacts.add(contact);
    }

    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts);
    }

    public boolean updateContact(Contact updated) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId() == updated.getId()) {
                contacts.set(i, updated);
                return true;
            }
        }
        return false;
    }

    public boolean deleteContact(int id) {
        return contacts.removeIf(c -> c.getId() == id);
    }

    public Contact findById(int id) {
        for (Contact c : contacts) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
}
