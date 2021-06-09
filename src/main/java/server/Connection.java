package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public final class Connection {
    private final Thread thread;
    private boolean running = true;

    public Connection(final Socket socket, final ConnectionHandler handler) throws IOException {
        final var out = new PrintWriter(socket.getOutputStream(), true);
        final var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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