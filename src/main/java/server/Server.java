package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;

public final class Server {

    private final int backlog;
    private final ConnectionHandler handler;
    private final int port;
    private final InetAddress bindAddress;

    public Server(final int port, final InetAddress bindAddress, final int backlog, final ConnectionHandler handler) {
        this.port = port;
        this.bindAddress = bindAddress;
        this.backlog = backlog;
        this.handler = handler;
    }

    private boolean running = true;
    private ServerSocket serverSocket;
    private final List<Connection> connections = new LinkedList<>();

    public Server start() throws IOException {
        this.serverSocket = new ServerSocket(port, backlog, bindAddress);
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
