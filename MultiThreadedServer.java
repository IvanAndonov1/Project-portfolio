import java.io.*;
import java.net.*;

public class MultiThreadedServer {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");

                ClientHandler clientHandler = new ClientHandler(clientSocket);

                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

