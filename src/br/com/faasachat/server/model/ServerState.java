package br.com.faasachat.server.model;

/**
 * Server state interface.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 10/11/2019
 * @version 1.0
 */
interface ServerState {
    
    /**
     * Init state behaviors.
     */
    void initState();
    
    /**
     * Starts the server.
     * @throws Exception
     */
    void start() throws Exception;

    /**
     * Pauses the server.
     * @throws Exception
     */
    void pause() throws Exception;

}
