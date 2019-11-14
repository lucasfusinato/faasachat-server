package br.com.faasachat;

import br.com.faasachat.server.controller.ServerController;
import br.com.faasachat.server.controller.ServerControllerInterface;

public class Main {

    /**
     * Main method.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ServerControllerInterface serverController = new ServerController();
        serverController.start();
    }
    
}
