package br.com.faasachat.server.controller;

/**
 * Interface to server controller observers.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 10/11/2019
 * @version 1.0
 */
public interface ServerControllerObserver {
    
    /**
     * Server status update.
     */
    void updateServerStatus();

}
