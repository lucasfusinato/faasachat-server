package br.com.faasachat.server.model;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Server socket factory.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 10/11/2019
 * @version 1.0
 */
public class ServerSocketFactory {
    
    /**
     * Default server socket port.
     * TODO moves this value to a configuration file
     */
    public static final int DEFAULT_PORT = 56000;

    /**
     * Self unique instance.
     */
    private static ServerSocketFactory instance;
    
    /**
     * Returns self unique instance.
     * @return
     */
    public static synchronized ServerSocketFactory getInstance() {
        if(instance == null) {
            instance = new ServerSocketFactory();
        }
        return instance;
    }
    
    /**
     * Makes constructor private.
     */
    private ServerSocketFactory() {
        return;
    }

    /**
     * Creates a server socket.
     * @return
     * @throws IOException 
     */
    public ServerSocket create() throws IOException {
        ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT);
        serverSocket.setReuseAddress(true);
        return serverSocket;
    }

}
