package br.com.repository;

import java.util.List;

import br.com.model.User;

/**
 * Repository interface to access contacts in database.
 * @author João Victor Arruda
 * @since 12/11/2019
 * @version 1.0
 */
public interface ContactRepositoryInterface {

    /**
     * Returns all user contacts.
     * @param user
     * @return
     */
    List<User> getContacts(User user) throws Exception;

    /**
     * Inserts a contact.
     * @param user
     * @param contact
     * @return
     */
    boolean insert(User user, User userContact) throws Exception;

    /**
     * Removes a contact.
     * @param user
     * @param contact
     * @return
     */
    boolean remove(User user, User userContact) throws Exception;

}
