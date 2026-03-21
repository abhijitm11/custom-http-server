package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);   // Opens a port, listens for incoming connections
            System.out.println("Server started on port: " + port);

            while (true) {
                Socket socket = serverSocket.accept();  // Waits until a client connects
                System.out.println("Client Connected!");

                // we'll handle request here
                handleRequest(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleRequest(Socket socket) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                OutputStream out = socket.getOutputStream()
        ) {
            // read the request line
            String requestLine = in.readLine();
            System.out.println("Request: " + requestLine);

            // read remaining header
            String line;
            while (!(line = in.readLine()).isEmpty()) {
                // System.out.println(line);
            }

            // prepare response body
            String responseBody = "Hello World from Server!";

            // build proper http response
            String response =
                    "HTTP/1.1 200 OK\r\n" +
                            "Content-Type: text/plain\r\n" +
                            "Content-Length: " + responseBody.length() + "\r\n" +
                            "\r\n" +
                            responseBody;


            // send response
            out.write(response.getBytes());
            out.flush();

            // close connection
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
