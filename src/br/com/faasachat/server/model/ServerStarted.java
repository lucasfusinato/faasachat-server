package br.com.faasachat.server.model;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Server started state.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 10/11/2019
 * @version 1.0
 */
class ServerStarted implements ServerState {

    /**
     * Server instance.
     */
    private Server server;

    /**
     * Instantiates started server state.
     * @param server
     */
    public ServerStarted(Server server) {
        this.server = server;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initState() {
        try {
            ServerSocket serverSocket = ServerSocketFactory.getInstance().create();
            this.server.setServerSocket(serverSocket);
            Thread thread = new Thread(new ServerRequestReceiver(serverSocket));
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
            this.server.setState(new ServerPaused(this.server));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() throws Exception {
        throw new Exception("Server already started.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pause() throws Exception {
        this.server.getServerSocket().close();
        this.server.setServerSocket(null);
        this.server.setState(new ServerPaused(this.server));
    }

}
