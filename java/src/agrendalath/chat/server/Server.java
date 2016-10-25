package agrendalath.chat.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private Server(int port) {
        Map<String, User> users = new ConcurrentHashMap<>();
        Listener.listen(port, users);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Server <port number>");
            System.exit(1);
        }

        new Server(Integer.parseInt(args[0]));
    }
}
