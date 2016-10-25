package agrendalath.chat.server;

import java.io.IOException;
import java.util.Map;

class ClientHandler extends Thread {
    private User user;
    private Map<String, User> users;
    private static String[] commands = {"/help", "/h", "/w", "/quit", "/q"};

    ClientHandler(User user, Map<String, User> users) {
        super("Chat server thread");
        this.user = user;
        this.users = users;
    }

    private void sendAll(String message) {
        for (User user : users.values()) {
            System.out.println("Sending to port " + user.getSocket().getPort());
            user.sendMessage(message);
        }
    }

    private void getUniqueName() {
        boolean unique = false;
        while (!unique) {
            user.sendMessage("Please introduce yourself:");
            user.setName(user.getMessage());
            if (user.getName().isEmpty())
                user.sendMessage("Username cannot be empty");
            else if (users.containsKey(user.getName()))
                user.sendMessage("This username has already been taken.");
            else
                unique = true;
        }
    }

    private void introduceNewUser() {
        System.out.println("New user is " + user);
        user.getOutput().println("Hello, " + user + ".");
        sendAll("User " + user + " has joined the chat.");
    }

    private void registerUser() {
        getUniqueName();
        introduceNewUser();
        users.put(user.getName(), user);
    }

    private void printHelp() {
        user.sendMessage(
                "/help - display this message" + "\n" +
                        "/h - display this message" + "\n" +
                        "/w <username> - whisper to user" + "\n" +
                        "/q - quit the chat" + "\n" +
                        "/quit - quit the chat"
        );
    }

    private void whisper(String input) {
        String[] query = input.split(" ");
        if (query.length >= 3) {
            if (users.containsKey(query[1])) {
                User recipient = users.get(query[1]);
                StringBuilder message = new StringBuilder(user + " whispers: ");
                for (int i = 2; i < query.length; ++i)
                    message.append(query[i]);

                user.sendMessage(message.toString());
                recipient.sendMessage(message.toString());
            } else
                user.sendMessage("User " + query[1] + " doesn't exist.");
        } else
            printHelp();
    }

    @Override
    public void run() {
        try {
            String inputLine;
            boolean quit = false;
            registerUser();

            while (!quit && (inputLine = user.getInput().readLine()) != null) {
                int command;
                for (command = 0; command < commands.length; ++command)
                    if (inputLine.startsWith(commands[command]))
                        break;

                switch (command) {
                    case 0:
                    case 1:
                        printHelp();
                        break;
                    case 2:
                        whisper(inputLine);
                        break;
                    case 3:
                    case 4:
                        quit = true;
                        break;
                    default:
                        System.out.println(inputLine);
                        sendAll(user + ": " + inputLine);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        users.remove(user.getName());
        sendAll("User " + user + " has disconnected.");
    }
}
