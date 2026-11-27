package ec.edu.espe.contactsbook.controller;

import ec.edu.espe.contactsbook.model.Contact;
import java.util.ArrayList;

/**
 *
 * @author Yandry Velez, Codelex, @ESPE
 */
public class ContactController {

    private static final ArrayList<Contact> contacts = new ArrayList<>();

    public static void addContact(Contact contact) {
        contacts.add(contact);
    }

    public static ArrayList<Contact> getContacts() {
        return contacts;
    }
}
