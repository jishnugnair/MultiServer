package org.jishnu.fileshare;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileShareClient extends Thread{
    private String host = null;
    private String filePath = null;
    private int port = 0;

    public FileShareClient(String host, int port, String filePath) {
        this.filePath = filePath;
        this.host = host;
        this.port = port;
        new Thread(this).start();
    }

    public void run() {
        FileInputStream fi = null;
        DataOutputStream out = null;
        Socket socket = null;
        String fileName = null;
        try {
            socket = new Socket(host, port);
            System.out.println("Connected");

            // sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());
            File file = new File(filePath);
            fi = new FileInputStream(file);
            fileName = file.getName();
            System.out.println("Sending file " + fileName);
            out.writeByte(1);
            out.writeChar((char)fileName.length());
            out.write(fileName.getBytes("UTF-8"));
            out.writeLong(file.length());
        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        }

        // string to read message from input

        byte[] buffer = new byte[65536];
        int byteCount = 0;

        // keep reading until "Over" is input
        while (true) {
            try {
                byteCount = fi.read(buffer);
                if (byteCount > 0)
                    out.write(buffer, 0, byteCount);
                else
                    break;
            } catch (IOException i) {
                System.out.println(i);
            }
        }

        System.out.println("Sent file " + fileName);
        // close the connection
        try {
            fi.close();
            out.close();
            socket.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }
}
