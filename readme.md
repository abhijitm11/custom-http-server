# Custom HTTP Server (Java)

A lightweight HTTP server built from scratch in Java to understand how
backend frameworks like Spring Boot work internally.

------------------------------------------------------------------------

## Features

-   Custom HTTP server using sockets
-   Request parsing (method, path, headers, body)
-   Pattern-based routing (`/users/{id}`)
-   Method-based routing (GET, POST)
-   Query parameter support
-   Layered architecture (Controller → Service → Repository)
-   In-memory database using HashMap

------------------------------------------------------------------------

## Architecture

    HttpServer
       ↓
    HttpRequestParser
       ↓
    Router
       ↓
    Route (pattern matching)
       ↓
    RouteHandler (functional interface)
       ↓
    Controller
       ↓
    Service
       ↓
    Repository (In-Memory DB)

------------------------------------------------------------------------

## Tech Stack

-   Java (Core)
-   Sockets (TCP)
-   No external frameworks

------------------------------------------------------------------------

## Modules

### 1. Models

-   User
-   Task

### 2. Repository Layer

-   Interfaces + InMemory implementations
-   Uses HashMap as fake DB

### 3. Service Layer

-   Business logic
-   Interacts with repositories

### 4. HTTP Server

-   Handles socket connections
-   Reads and writes raw HTTP

### 5. Request Parser

Parses: - HTTP Method - Path - Headers - Body - Query Params

### 6. Controllers

-   Handles incoming requests
-   Calls services

### 7. Routing Engine

-   Pattern-based routing
-   Supports dynamic paths (`/users/{id}`)
-   Grouped by HTTP method

------------------------------------------------------------------------

## Routing Design

### Route Registration

``` java
router.addRoute("GET", "/users/{id}", handler);
```

### Internal Structure

    GET  → [ /users, /users/{id} ]
    POST → [ /users ]

------------------------------------------------------------------------

## API Endpoints

### ✅ Get All Users

    GET /users

### ✅ Get User by ID

    GET /users/1

### ✅ Create User

    POST /users
    Content-Type: application/x-www-form-urlencoded

    name=abhi&email=test@gmail.com

------------------------------------------------------------------------

## Query Params vs Path Params

### Path Param

    /users/123
    → id = 123

### Query Param

    /users?id=123&name=abhi
    → {id=123, name=abhi}

------------------------------------------------------------------------

## Sample Request

    GET /users/1 HTTP/1.1
    Host: localhost:8080

------------------------------------------------------------------------

## Sample Response

    HTTP/1.1 200 OK
    Content-Type: text/plain
    Content-Length: 32

    User{name=abhi, email=test@gmail.com}

------------------------------------------------------------------------

## How to Run

1.  Clone the repo
2.  Run Main.java
3.  Server starts at:

```{=html}
<!-- -->
```
    http://localhost:8080

------------------------------------------------------------------------

## Testing

### Browser

-   http://localhost:8080/users
-   http://localhost:8080/users/1

### Postman

-   POST /users
-   Body: x-www-form-urlencoded

------------------------------------------------------------------------

## Limitations

-   No persistence (data resets on restart)
-   No JSON support
-   Basic error handling

------------------------------------------------------------------------

## Future Improvements

-   JSON support
-   Database integration
-   Error handling (400, 500)
-   Middleware
-   Logging
-   Thread safety

------------------------------------------------------------------------

## Key Learnings

-   How HTTP works internally
-   Building a server from scratch
-   Routing and pattern matching
-   Functional interfaces
-   Backend architecture design

------------------------------------------------------------------------

## Author

Abhijit Mudoi
