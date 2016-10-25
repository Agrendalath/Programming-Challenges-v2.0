package agrendalath.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;

class Listener {
    @SuppressWarnings("InfiniteLoopStatement")
    static void listen(int port, Map<String, User> users) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
//                User user = new User(new ServerSocket(port++).accept()); // DEBUG
                User user = new User(serverSocket.accept());
                new ClientHandler(user, users).start();
                System.out.println("User connected.");
            }
        } catch (IOException e) {
            System.out.println("Cannot listen at port " + port);
        }
    }
}
