package Server;

import XML.XML_parser;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientServer implements Runnable {
    private static final int serverPort = 5400;
    private static final String serverHost = "localhost";

    private static Socket socket;
    private static InputStream clientInput;
    private static OutputStream clientOutput;

    private static int message;

    private static int userID;

    @Override
    public void run() {
        try {
        socket = new Socket(serverHost, serverPort);
        System.out.println("Connection succesfull.");
        clientInput = new DataInputStream(socket.getInputStream());
        clientOutput = new DataOutputStream(socket.getOutputStream());
        PrintStream ps = new PrintStream(socket.getOutputStream());
           String buff=XML_parser.getXML_Archive("/home/kenneth/CLionProjects/Progra.2.datos.c-/cmake-build-debug/LOLA.mp3");
           System.out.print(buff);
           System.out.print(buff.length());
           // String buff = "23";
            clientOutput.write(buff.getBytes());
        System.out.println("Connected to server!");
        while(true){
            message = clientInput.read();
            System.out.print((char) message);

        }
    } catch (UnknownHostException e) {
        System.out.println("Cannot find host.");
    } catch (IOException e) {
        System.out.println("IO Exception thrown");
}
    }
}