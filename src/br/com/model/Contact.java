package br.com.model;

/**
 * Model that represents a contact between two users.
 * @author João Victor Arruda
 * @since 12/11/2019
 * @version 1.0
 */
public class Contact {
    
    /**
     * User.
     */
    private User user;

    /**
     * User contact.
     */
    private User contact;

    /**
     * Returns user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Defines user.
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Returns user contact.
     * @return
     */
    public User getContact() {
        return contact;
    }

    /**
     * Defines user contact.
     * @param contact
     */
    public void setContact(User contact) {
        this.contact = contact;
    }

}
