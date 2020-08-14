# Http Server
A poor man's implementation of an http server using only Java SE 1.8

## Features
- [x] Can serve HTTP requests and responses
    - [x] Can parse incoming HTTP requests
        - [x] Parse Request Line (i.e. GET / HTTP/1.1)
        - [x] Parse Headers (Host: localhost:8080)
    - [x] Respond with HTTP response
        - [x] Respond with HTML welcome page

## Usage
```bash
# Build docker image for database
docker build -t httpdb db/.

# Run database
docker run -p 5432:5432 --rm -it httpdb

# Compile
mvn compile -q

# Run
mvn exec:java -q

# Test
mvn test

# To set the port:
mvn exec:java -q -Dport=9999
```