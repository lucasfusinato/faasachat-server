package br.com.faasachat.api.resource;

import br.com.faasachat.api.model.ResourceClass;
import br.com.faasachat.api.model.ResourceMethod;
import br.com.faasachat.api.service.SessionService;
import br.com.faasachat.domain.model.EmptyPasswordUser;
import br.com.faasachat.domain.model.Request;
import br.com.faasachat.domain.model.Response;
import br.com.faasachat.domain.model.Session;

/**
 * Service to process session requests.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 11/11/2019
 * @version 1.0
 */
@ResourceClass(value = "/sessions")
public class SessionResource {
    
    /**
     * Session service.
     */
    private SessionService sessionService = new SessionService();

    /**
     * Creates a session to user.
     * @throws Exception 
     */
    @ResourceMethod
    public void login(Request request, Response response) throws Exception {
        String nickname = request.getParameter("nickname", String.class);
        String password = request.getParameter("password", String.class);
        Session session = sessionService.createSession(nickname, password);
        if(session != null) {
            session.setUser(new EmptyPasswordUser(session.getUser()));
            response.setSuccess(true);
            response.setData(session);
        }
    }

    /**
     * Updates user session.
     * @throws Exception 
     */
    @ResourceMethod(authentication = true)
    public void updateSession(Request request, Response response) throws Exception {
        Session session = request.getSession();
        if(sessionService.updateSession(session)) {
            session.setUser(new EmptyPasswordUser(session.getUser()));
            response.setSuccess(true);
            response.setData(session);
        }
    }

    /**
     * Destroys user session.
     * @throws Exception 
     */
    @ResourceMethod(authentication = true)
    public void destroySession(Request request, Response response) throws Exception {
        Session session = request.getSession();
        if(sessionService.destroySession(session)) {
            session.setUser(new EmptyPasswordUser(session.getUser()));
            response.setSuccess(true);
            response.setData(session);
        }
    }

}
