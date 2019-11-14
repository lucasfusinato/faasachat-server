package br.com.faasachat.api.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.faasachat.domain.model.Contact;
import br.com.faasachat.domain.model.User;

/**
 * Repository to access contacts in database.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 11/11/2019
 * @version 1.0
 */
public class ContactMemoryRepository {
    
    /**
     * List of all contacts.
     * TODO remove it and implement all operations in database
     */
    private static List<Contact> contacts = new ArrayList<>();

    /**
     * Returns all user contacts.
     * @param user
     * @return
     */
    public List<User> getContacts(User user) {
        List<User> userContacts = new ArrayList<>();
        for(Contact contact : contacts) {
            if(user.equals(contact.getUser())) {
                userContacts.add(contact.getContact());
            }
        }
        return userContacts;
    }

    /**
     * Inserts a contact.
     * @param user
     * @param contact
     * @return
     */
    public boolean insert(User user, User userContact) {
        Contact contact = new Contact();
        contact.setUser(user);
        contact.setContact(userContact);
        contacts.add(contact);
        return true;
    }

    /**
     * Removes a contact.
     * @param user
     * @param contact
     * @return
     */
    public boolean remove(User user, User userContact) {
        Contact contact = null;
        boolean removed = false;
        int index = contacts.size() - 1;
        while(index-- >= 0) {
            contact = contacts.get(index);
            if(user.equals(contact.getUser()) && userContact.equals(contact.getContact())) {
                contacts.remove(index);
                removed = true;
                break;
            }
        }
        return removed;
    }

}
