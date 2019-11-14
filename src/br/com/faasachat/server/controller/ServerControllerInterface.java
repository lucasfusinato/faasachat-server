package br.com.faasachat.server.controller;

/**
 * Interface to server controller operations.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 10/11/2019
 * @version 1.0
 */
public interface ServerControllerInterface {
    
    /**
     * Attaches an observer.
     * @param observer
     */
    void attach(ServerControllerObserver observer);

    /**
     * Starts server.
     * @throws Exception 
     */
    void start() throws Exception;
    
    /**
     * Pauses server.
     * @throws Exception 
     */
    void pause() throws Exception;
    
}
