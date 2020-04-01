package org.jishnu.binaryapi;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class FileUploader {

    public void upload(DataInputStream in) {
        byte[] dataBuffer = new byte[65536];
        long fileLength = 0;
        long fileOffset = 0;
        int byteCount = 0;
        String filename = null;

        try {
            int nameLength = in.readChar();

            byte[] nameBuffer = new byte[nameLength];
            in.readFully(nameBuffer);
            filename = new String(nameBuffer);

            fileLength = in.readLong();

        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
            return;
        }

        try(FileOutputStream fos = new FileOutputStream(filename)) {

            while(fileLength > fileOffset) {
                byteCount = in.read(dataBuffer);
                fos.write(dataBuffer, 0 , byteCount);
                fileOffset += byteCount;
            }
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
}
