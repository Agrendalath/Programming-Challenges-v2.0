package agrendalath.chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class User {
    private final Socket socket;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private String name;

    User(Socket socket) {
        this.socket = socket;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    Socket getSocket() {
        return socket;
    }

    PrintWriter getOutput() {
        return out;
    }

    BufferedReader getInput() {
        return in;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    void sendMessage(String message) {
        getOutput().println(message);
    }

    String getMessage() {
        try {
            return getInput().readLine();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        User user = (User) o;

        return name != null ? name.equals(user.name) : user.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
