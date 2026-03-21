package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);   // Opens a port, listens for incoming connections
            System.out.println("Server started on port: "+port);

            while(true) {
                Socket socket = serverSocket.accept();  // Waits until a client connects
                System.out.println("Client Connected!");

                // we'll handle request here
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
