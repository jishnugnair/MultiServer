package org.jishnu.fileshare;

public class FileUploadRequester {
    public static void main(String[] args) {
        String[] files = {
                ""
        };

        for(String file: files)
            new FileShareClient("localhost", 8888, file);
    }
}
