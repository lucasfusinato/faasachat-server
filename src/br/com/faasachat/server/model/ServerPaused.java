package br.com.faasachat.server.model;

/**
 * Server paused state.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 10/11/2019
 * @version 1.0
 */
class ServerPaused implements ServerState {
    
    /**
     * Server instance.
     */
    private Server server;
    
    /**
     * Instantiates paused server state.
     * @param server
     */
    public ServerPaused(Server server) {
        this.server = server;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initState() {
        return;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() throws Exception {
        this.server.setState(new ServerStarted(this.server));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pause() throws Exception {
        throw new Exception("Server already paused.");
    }

}
