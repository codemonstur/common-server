package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;

public final class Server {

    private final int port;
    private final int backlog;
    private final String address;
    private final ConnectionHandler handler;

    public Server(final int port, final int backlog, final String address, final ConnectionHandler handler) {
        this.port = port;
        this.backlog = backlog;
        this.address = address;
        this.handler = handler;
    }

    private boolean running = true;
    private ServerSocket serverSocket;
    private final List<Connection> connections = new LinkedList<>();

    public Server start() throws IOException {
        this.serverSocket = new ServerSocket(port, backlog, InetAddress.getByName(address));
        new Thread(() -> {
            while (running) {
                try {
                    synchronized (connections) {
                        connections.add(new Connection(serverSocket.accept(), handler));
                        connections.removeIf(connection -> !connection.isAlive());
                    }
                } catch (Exception e) {}
            }
        }).start();

        return this;
    }

    public void stop() throws IOException {
        this.running = false;

        try {
            serverSocket.close();
        } finally {
            synchronized (connections) {
                connections.forEach(Connection::stop);
            }
        }
    }

}
