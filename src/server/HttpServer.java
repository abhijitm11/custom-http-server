package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    public void start(int port) {

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                handleClient(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket socket) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                OutputStream out = socket.getOutputStream()
        ) {
            String requestLine = readRequest(in);
            String response = processRequest(requestLine);
            writeResponse(out, response);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readRequest(BufferedReader in) throws IOException {
        String requestLine = in.readLine();
        System.out.println("Request: " + requestLine);

        // Read headers (for now just consume them)
        String line;
        while (!(line = in.readLine()).isEmpty()) {
            System.out.println(line);
        }

        return requestLine;
    }

    private String processRequest(String requestLine) {
        // For now, ignore actual parsing
        return "Hello World";
    }

    private void writeResponse(OutputStream out, String body) throws IOException {
        String response =
                "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/plain\r\n" +
                        "Content-Length: " + body.length() + "\r\n" +
                        "\r\n" +
                        body;

        out.write(response.getBytes());
        out.flush();
    }
}
