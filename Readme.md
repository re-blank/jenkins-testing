# Http Server
A poor man's implementation of an http server using only Java SE 1.8

## Features
- [x] Can serve HTTP requests and responses
    - [x] Can parse incoming HTTP requests
        - [x] Parse Request Line (i.e. GET / HTTP/1.1)
        - [x] Parse Headers (Host: localhost:8080)
    - [x] Respond with HTTP response
        - [ ] Respond with HTML welcome page