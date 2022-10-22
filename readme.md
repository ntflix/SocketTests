# SocketTests

*messing about with java.net.socket*

Simple client and server to send and receive text messages across
a network between two devices with known IP addresses and ports.

## Usage

### Server

```sh
$ java -jar sockettests.jar -mode server -port $PORT
```

### Client

```sh
$ java -jar sockettests.jar -mode client -server $SERVER_IP -port $PORT
```
