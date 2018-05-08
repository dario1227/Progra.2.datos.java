package Server;

import XML.XML_parser;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientServer {
    public static ClientServer Server;
    private Socket socket;
    private InputStream clientInput;
    private OutputStream clientOutput;

    private int message;

    private static int userID;

    public ClientServer() throws IOException {
        this.socket = new Socket("localhost", 5400);
        System.out.println("Connected to server!");
        this.clientInput = new DataInputStream(socket.getInputStream());
        this.clientOutput = new DataOutputStream(socket.getOutputStream());

    }

    public void send(String message) throws IOException {
        this.clientOutput.write(message.getBytes());

    }

    public void  receive() throws IOException {
        while (true) {
           message =  this.clientInput.read();
            System.out.print((char) message);
        }
    }

}