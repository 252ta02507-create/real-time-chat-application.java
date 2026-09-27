import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 5000);

            System.out.println("Connected to chat server!");

            BufferedReader input =
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));

            PrintWriter output =
                    new PrintWriter(socket.getOutputStream(), true);

            BufferedReader keyboard =
                    new BufferedReader(new InputStreamReader(System.in));

            String message;

            while (true) {

                // Send message to server
                System.out.print("You: ");
                message = keyboard.readLine();

                output.println(message);

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                // Receive message from server
                message = input.readLine();

                if (message == null || message.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.println("Server: " + message);
            }

            socket.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
