package br.com.faasachat.api.resource;

import java.util.List;

import br.com.faasachat.api.model.ResourceClass;
import br.com.faasachat.api.model.ResourceMethod;
import br.com.faasachat.api.service.ContactService;
import br.com.faasachat.api.service.SessionService;
import br.com.faasachat.domain.model.Request;
import br.com.faasachat.domain.model.Response;
import br.com.faasachat.domain.model.Session;
import br.com.faasachat.domain.model.EmptyPasswordUser;
import br.com.faasachat.domain.model.User;

/**
 * Service to process contact requests.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 11/11/2019
 * @version 1.0
 */
@ResourceClass(value = "/contacts")
public class ContactResource {

    /**
     * Contact service.
     */
    private ContactService contactService = new ContactService();

    /**
     * Returns all user contacts.
     * @param session
     * @throws Exception 
     */
    @ResourceMethod(authentication = true)
    public void getContacts(Request request, Response response) throws Exception {
        List<User> contacts = contactService.getContacts(request.getSession().getUser());
        for(int index = 0; index < contacts.size(); index++) {
            User user = contacts.get(index);
            Session session = new SessionService().getActiveSessionByUser(user);
            if(session != null) {
                user.setOnline(session.isActive());
            }
            contacts.set(index, new EmptyPasswordUser(user));
        }
        response.setSuccess(true);
        response.setData(contacts);
    }

    /**
     * Add a user contact.
     * @throws Exception 
     */
    @ResourceMethod(authentication = true)
    public void addContact(Request request, Response response) throws Exception {
        User user = new User(request.getParameter("contactNickname", String.class));
        if(contactService.addContact(request.getSession().getUser(), user)) {
            response.setSuccess(true);
            response.setData(new EmptyPasswordUser(user));
        }
    }
    
    /**
     * Remove a user contact.
     * @throws Exception 
     */
    @ResourceMethod(authentication = true)
    public void removeContact(Request request, Response response) throws Exception {
        User user = new User(request.getParameter("contactNickname", String.class));
        if(contactService.removeContact(request.getSession().getUser(), user)) {
            response.setSuccess(true);
            response.setData(new EmptyPasswordUser(user));
        }
    }

}
