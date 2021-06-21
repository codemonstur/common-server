package server;

import java.io.IOException;
import java.net.Socket;

public final class Connection {
    private final Thread thread;
    private boolean running = true;

    public Connection(final Socket socket, final ConnectionHandler handler) throws IOException {
        final var out = socket.getOutputStream();
        final var in = socket.getInputStream();
        this.thread = new Thread(() -> {
            try (out; in; socket) {
                while (running) {
                    handler.handleInput(in, out);
                }
            } catch (Exception e) {}
        });
        this.thread.start();
    }

    public void stop() {
        this.running = false;
    }

    public boolean isAlive() {
        return thread.isAlive();
    }
}