package agrendalath.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Client {
    private Client(final String HOST, final int PORT) {
        Socket socket;
        while (true) {
            try {
                socket = new Socket(HOST, PORT);
                break;
            } catch (IOException e) {
                System.err.println("Could not connect to " + HOST + " at port " + PORT + ".");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e1) {
                    System.exit(1);
                }
            }
        }
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            new Thread(() -> {
                String inputLine;
                try {
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println(inputLine);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Server down.");
                System.exit(0);
            }).start();

            String input;
            while ((input = stdIn.readLine()) != null) {
                out.println(input);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("Disconnected.");
        System.exit(0);
    }


    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java Client <host> <port number>");
            System.exit(1);
        }
        new Client(args[0], Integer.parseInt(args[1]));
    }
}
