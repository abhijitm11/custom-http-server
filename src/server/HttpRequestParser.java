package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
POST /users HTTP/1.1
Host: localhost:8080
Content-Type: application/x-www-form-urlencoded
Content-Length: 27

name=abhi&email=test@gmail.com
*/

public class HttpRequestParser {
    public HttpRequest parse(BufferedReader reader) throws IOException {

        String requestLine = reader.readLine();        // GET /users?name=abhi&age=25 HTTP/1.1

        // null check
        if(requestLine == null || requestLine.isEmpty()) {
            return null;
        }

        // Transformation 1: Split request line
        String[] parts = requestLine.split(" ");

        String method = parts[0];             // "GET"
        String fullPath = parts[1];           // "/users?name=abhi&age=25"

        // Transformation 2: Extract Path
        String path = fullPath.split("\\?")[0];          // /users

        // Transformation 3: Extract query params
        Map<String, String> queryParams = parseQueryParams(fullPath);

        // Transformation 4: Read headers
        Map<String, String> headers = new HashMap<>();

        String line;
        while (!(line = reader.readLine()).isEmpty()) {

            // System.out.println("Header: " + line);              // Host: localhost:8080

            // Input:  "Host: localhost:8080"
            // Output: ["Host", "localhost:8080"]
            String[] headerParts = line.split(": ");

            headers.put(headerParts[0], headerParts[1]);
        }

        // Transformation 5: Read body
        String body = "";
        String contentLengthHeader = headers.get("Content-Length");

        if (contentLengthHeader != null) {             // GET request → no body → no Content-Length || POST request → has body
            int contentLength = Integer.parseInt(contentLengthHeader);

            char[] bodyChars = new char[contentLength];
            reader.read(bodyChars, 0, contentLength);

            body = new String(bodyChars);
            Map<String, String> parsedBody = parseBody(body);
        }

        return new HttpRequest(
                method,
                path,
                headers,
                queryParams,
                body.toString()
        );
    }

    private Map<String, String> parseQueryParams(String fullPath) {
        Map<String, String> params = new HashMap<>();

        // Input: "/users" → no query → return empty map
        if(!fullPath.contains("?")) {
            return params;
        }

        String query = fullPath.split("\\?")[1];       // "name=abhi&age=25"

        String[] pairs = query.split("&");            // ["name=abhi", "age=25"]

        for(String pair: pairs) {
            String[] kv = pair.split("=");

            String key = kv[0];
            String value = kv.length >1 ? kv[1]:"";

            params.put(key, value);
        }
        return params;

    }

    private Map<String, String> parseBody(String body) {
        Map<String, String> parsedBody = new HashMap<>();
        if(body.isEmpty()) {
            return parsedBody;
        }

        String[] pairs = body.split("&");
        for(String pair: pairs) {
            parsedBody.put(pair.split("=")[0], pair.split("=")[1]);
        }
        return parsedBody;
    }

}
