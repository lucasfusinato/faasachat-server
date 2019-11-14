package br.com.faasachat.api;

import br.com.faasachat.domain.model.Response;

public interface ServerResourceCallback {
    
    void call(Response response);

}
