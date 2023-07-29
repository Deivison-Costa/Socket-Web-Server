import java.io.*;
import java.net.Socket;

public class FileClient {

    public static void main(String[] args) {
        final String SERVER_IP = "127.0.0.1"; // Enter the server IP here
        final int SERVER_PORT = 8080; // Enter the server port here
        final String FILE_NAME = "/path/to/shared/folder"; // Enter the file name here

        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ) {
            // Sending the requested file name to the server
            oos.writeObject(FILE_NAME);

            // Receiving the file content from the server
            byte[] buffer = new byte[4096];
            int bytesRead;
            try (FileOutputStream fos = new FileOutputStream(FILE_NAME)) {
                while ((bytesRead = ois.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
            }

            System.out.println("File received successfully: " + FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}