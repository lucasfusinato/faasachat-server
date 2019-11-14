package br.com.faasachat.api.service;

import java.util.List;

import br.com.faasachat.api.repository.ContactMemoryRepository;
import br.com.faasachat.domain.model.User;

/**
 * Service to execute contacts business rules.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 11/11/2019
 * @version 1.0
 */
public class ContactService {
    
    /**
     * Repository to manipulate contacts in database.
     */
    private ContactMemoryRepository contactRepository = new ContactMemoryRepository();

    /**
     * Returns all user contacts.
     * @param user
     * @return
     */
    public List<User> getContacts(User user) throws Exception {
        return contactRepository.getContacts(user);
    }

    /**
     * Adds a user contact.
     * @param user
     * @param contact
     * @return
     */
    public boolean addContact(User user, User contact) throws Exception {
        return contactRepository.insert(user, contact);
    }

    /**
     * Removes a user contact.
     * @param user
     * @param contact
     * @return
     */
    public boolean removeContact(User user, User contact) throws Exception {
        return contactRepository.remove(user, contact);
    }

}
