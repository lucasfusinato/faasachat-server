package br.com.faasachat.api;

import java.io.PrintWriter;
import java.net.Socket;

import br.com.faasachat.domain.model.Response;

public class ResponseSocketCallback implements ServerResourceCallback {

    private Socket socket;

    public ResponseSocketCallback(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void call(Response response) {
        try {
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println(response);
            writer.flush();
            System.out.println("Server Send --> " + response);
        } catch(Exception e) {
            //Do nothing
        }
    }

}
