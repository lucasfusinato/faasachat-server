package br.com.faasachat.api;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import br.com.faasachat.api.model.ResourceMethod;
import br.com.faasachat.api.service.SessionService;
import br.com.faasachat.domain.model.Request;
import br.com.faasachat.domain.model.Response;
import br.com.faasachat.domain.model.Session;

/**
 * Server resource class.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 11/11/2019
 * @version 1.0
 */
public class ServerResource implements Runnable {
    
    /**
     * Received request data.
     */
    private Request request;
    
    /**
     * Created response data.
     */
    private Response response;

    /**
     * Callback to resource execution.
     */
    private ServerResourceCallback callback;

    /**
     * Instantiates server resource class.
     * @param callback 
     */
    public ServerResource(Request request, ServerResourceCallback callback) {
        this.request = request;
        this.response = new Response();
        this.callback = callback;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        try {
            Object resource   = ResourceFactory.getInstance().create(request.getResource());
            String methodName = request.getMethod();
            Method method     = resource.getClass().getDeclaredMethod(methodName, Request.class, Response.class);

            //ResourceMethod resourceMethod = getResourceAnnotation(method);
            //if(resourceMethod.authentication()) {
            request.setSession(getSessionByToken());
            //}

            method.invoke(resource, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.setSuccess(false);
            response.setData(new Exception(e.getMessage()));
        } finally {
            callback.call(response);
        }
    }
    
    /**
     * Returns request annotation from method if it's defined.
     * @param method
     * @return
     * @throws Exception
     */
    private ResourceMethod getResourceAnnotation(Method method) throws Exception {
        ResourceMethod requestAnnotation = method.getAnnotation(ResourceMethod.class);
        if(requestAnnotation == null) {
            throw new Exception("Invalid request.");
        }
        return requestAnnotation;
    }
    
    /**
     * Returns session instance by request token parameter.
     * @return
     * @throws Exception
     */
    private Session getSessionByToken() throws Exception {
        String token = request.getParameter("token", String.class);
        if(token == null) {
            return null;
//            throw new Exception("Request requires a token.");
        }
        Session session = new SessionService().getSessionByToken(token);
        if(session == null) {
            return null;
//            throw new Exception("Request requires a valid authentication token.");
        }
        return session;
    }
    
}
