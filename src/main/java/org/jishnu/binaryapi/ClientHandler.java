package org.jishnu.binaryapi;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread{
    private Socket socket = null;
    public ClientHandler(Socket socket) {
        this.socket = socket;
        new Thread(this).start();
    }

    public void run() {
        try {
            // takes input from the client socket
            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            byte requestType = 0;

            while(true) {
                requestType = in.readByte();

                if (requestType == 1) {
                    System.out.println("File upload requested");
                    new FileUploader().upload(in);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
}
