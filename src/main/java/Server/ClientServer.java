package Server;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientServer implements Runnable {
    private static final int serverPort = 5700;
    private static final String serverHost = "localhost";

    private static Socket socket;
    private static DataInputStream clientInput;
    private static DataOutputStream clientOutput;

    private static String message;

    private static int userID;

    @Override
    public void run() {

        try {
            socket = new Socket(serverHost, serverPort);
            System.out.println("Connection succesfull.");

            clientInput = new DataInputStream(socket.getInputStream());
            clientOutput = new DataOutputStream(socket.getOutputStream());

            clientOutput.writeUTF("C_Message1");
            System.out.println("Connected to server!");

            while (true){
                message = clientInput.readUTF();
                System.out.print(message);
            }
        } catch (UnknownHostException e) {
            System.out.println("Cannot find host.");
        } catch (IOException e) {
            System.out.println("IO Exception thrown");

        }
    }
}