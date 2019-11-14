package br.com.faasachat.api.resource;

import br.com.faasachat.api.model.ResourceClass;
import br.com.faasachat.api.model.ResourceMethod;
import br.com.faasachat.api.service.UserService;
import br.com.faasachat.domain.model.EmptyPasswordUser;
import br.com.faasachat.domain.model.Request;
import br.com.faasachat.domain.model.Response;
import br.com.faasachat.domain.model.User;

/**
 * Service to process user requests.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 11/11/2019
 * @version 1.0
 */
@ResourceClass(value = "/users")
public class UserResource {
    
    /**
     * User service.
     */
    private UserService service = new UserService();
    
    /**
     * Creates a user.
     * @throws Exception 
     */
    @ResourceMethod
    public void signup(Request request, Response response) throws Exception {
        User user = new User(request.getParameters());
        if(service.createUser(user)) {
            response.setSuccess(true);
            response.setData(new EmptyPasswordUser(user));
        }
    }

    /**
     * Updates a user.
     * @throws Exception 
     */
    @ResourceMethod(authentication = true)
    public void updateProfile(Request request, Response response) throws Exception {
        User user = new User(request.getParameters());
        user.setId(request.getSession().getUser().getId());
        if(service.updateUser(user)) {
            response.setSuccess(true);
            response.setData(new EmptyPasswordUser(user));
        }
    }
    
}
