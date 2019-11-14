package br.com.faasachat.server.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.faasachat.server.model.Server;

/**
 * Server controller
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 10/11/2019
 * @version 1.0
 */
public class ServerController implements ServerControllerInterface {
    
    /**
     * Server instance.
     */
    private Server server;
    
    /**
     * Server observers.
     */
    private List<ServerControllerObserver> observers;
    
    /**
     * Instantiates controller with server.
     * @param server
     */
    public ServerController() {
        this.server = new Server();
        this.observers = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attach(ServerControllerObserver observer) {
        this.observers.add(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() throws Exception {
        this.server.start();
        notifyUpdateServerStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pause() throws Exception {
        this.server.pause();
        notifyUpdateServerStatus();
    }
    
    /**
     * Notify all observers that server status has been updated.
     */
    protected void notifyUpdateServerStatus() {
        for(ServerControllerObserver observer : observers) {
            observer.updateServerStatus();
        }
    }

}
