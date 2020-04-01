package org.jishnu.binaryapi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class APIServer {
    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(8888);
            System.out.println("Server started");
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

            int clientCount = 0;

            while(true) {
                try {
                    Socket socket = server.accept();
                    System.out.println("Client number " + ++clientCount + " accepted");
                    new ClientHandler(socket);
                    System.out.println("Client number " + clientCount + " dispatched to handler");
                } catch (IOException ex) {
                    System.out.println("Client socket failed: " + ex.getLocalizedMessage());
                }
            }

    }
}
