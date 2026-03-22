package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private final Router router;

    public HttpServer(Router router) {
        this.router = router;
    }

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
            // Parse request
            HttpRequestParser parser = new HttpRequestParser();
            HttpRequest request = parser.parse(in);

            if (request == null) {
                socket.close();
                return;
            }

            // Route Request
            String response = router.route(request);

            // Send response
            out.write(response.getBytes());
            out.flush();

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
