package server;

import java.io.BufferedReader;
import java.io.PrintWriter;

public interface ConnectionHandler {
    void handleInput(BufferedReader in, PrintWriter out);
}
