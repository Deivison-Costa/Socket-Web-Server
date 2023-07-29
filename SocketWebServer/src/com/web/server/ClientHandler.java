package com.web.server;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private final String sharedFolderPath;

    public ClientHandler(Socket clientSocket, String sharedFolderPath) {
        this.clientSocket = clientSocket;
        this.sharedFolderPath = sharedFolderPath;
    }

    @Override
    public void run() {
        try (
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
        ) {
            String requestedFileName = (String) ois.readObject();
            System.out.println("Client requested file: " + requestedFileName);

            File requestedFile = new File(sharedFolderPath + File.separator + requestedFileName);

            if (requestedFile.exists()) {
                // File found, sending it to the client
                try (FileInputStream fis = new FileInputStream(requestedFile)) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        oos.write(buffer, 0, bytesRead);
                    }
                }
                System.out.println("File sent to client successfully: " + requestedFileName);
            } else {
                // File not found, notifying the client
                oos.writeObject("File not found: " + requestedFileName);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}