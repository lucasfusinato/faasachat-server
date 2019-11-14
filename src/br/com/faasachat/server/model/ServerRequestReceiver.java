package br.com.faasachat.server.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import br.com.faasachat.api.ResponseSocketCallback;
import br.com.faasachat.api.ServerResourceCallback;
import br.com.faasachat.api.ServerResourceFactory;
import br.com.faasachat.domain.adapter.GsonAdapter;
import br.com.faasachat.domain.model.Request;
import br.com.faasachat.domain.model.Response;

/**
 * Request receiver.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 10/11/2019
 * @version 1.0
 */
public class ServerRequestReceiver implements Runnable {

    /**
     * Server socket to receive connections.
     */
    private ServerSocket serverSocket;
    
    /**
     * Instantiates request receiver.
     * @param serverSocket
     * @param observer 
     * @param requestFactory
     */
    public ServerRequestReceiver(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        while(!serverSocket.isClosed()) {
            try {
                Socket socket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Request request = GsonAdapter.getInstance().fromJson(reader.readLine(), Request.class);
                System.out.println("Server Receive --> " + request);
                Thread thread = new Thread(ServerResourceFactory.getInstance().create(request, new ResponseSocketCallback(socket)));
                thread.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
