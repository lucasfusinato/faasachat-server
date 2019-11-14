package br.com.faasachat.api;

import br.com.faasachat.domain.model.Request;

public class ServerResourceFactory {
    
    private static ServerResourceFactory instance;
    
    public static synchronized ServerResourceFactory getInstance() {
        if(instance == null) {
            instance = new ServerResourceFactory();
        }
        return instance;
    }
    
    public ServerResource create(Request request, ServerResourceCallback callback) {
        return new ServerResource(request, callback);
    }

}
