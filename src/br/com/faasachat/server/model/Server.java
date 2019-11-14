package br.com.faasachat.server.model;

import java.net.ServerSocket;

/**
 * Server model.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 10/11/2019
 * @version 1.0
 */
public class Server {
    
    /**
     * Current server state.
     */
    private ServerState state;
    
    /**
     * Server socket.
     */
    private ServerSocket serverSocket;
    
    /**
     * Instantiates server.
     */
    public Server() {
        this.state = new ServerPaused(this);
    }
    
    /**
     * Starts the server.
     * @throws Exception
     */
    public void start() throws Exception {
        this.state.start();
    }
    
    /**
     * Pauses the server.
     * @throws Exception
     */
    public void pause() throws Exception {
        this.state.pause();
    }

    /**
     * Changes server state.
     * @param state
     */
    public void setState(ServerState state) {
        this.state = state;
        this.state.initState();
    }

    /**
     * Returns server socket.
     * @return
     */
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    /**
     * Defines server socket.
     * @param serverSocket
     */
    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

}
