package Server;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientServer implements Runnable {
    private static final int serverPort = 5000;
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

                if (message.equals("S_Message1")){
                    clientOutput.writeUTF("C_Message2");
                }
                else if (message.equals("S_Message2")){
                    System.out.println("Accepted on table!");
                }
                else if (message.equals("S_Message3")){
                    System.out.println("Rejected");
                }
            }
        } catch (UnknownHostException e) {
            System.out.println("Cannot find host.");
        } catch (IOException e) {
            System.out.println("IO Exception thrown");

        }
    }
}