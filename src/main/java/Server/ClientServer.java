package Server;

import XML.XML_parser;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.zip.DeflaterOutputStream;

public class ClientServer {
    public static ClientServer Server;
    public Socket socket;
    public InputStream clientInput;
    public OutputStream clientOutput;

    private int message;

    private static int userID;
    byte[] buf=new byte[7000000];

    public ClientServer() throws IOException {
        this.socket = new Socket("localhost", 5400);
        System.out.println("Connected to server!");
        this.clientInput = new DataInputStream(socket.getInputStream());
        this.clientOutput = new DataOutputStream(socket.getOutputStream());
        send("Connected");

    }

    public void send(String message) throws IOException {
        this.clientOutput.write(message.getBytes());

    }

    public String  receive() throws IOException {
        String recivido="";
        while (true) {
           message =  this.clientInput.read(buf, 0,buf.length);
            if((char)message == '#')
            {
                break;
            }
            recivido+=message;
            recivido+=',';
            System.out.print((char) message);

        }String[] byteValues = recivido.substring(0, recivido.length()).split(",");
        byte[] bytes = new byte[byteValues.length];

        for (int i=0, len=bytes.length; i<len; i++) {
            bytes[i] = Byte.parseByte(byteValues[i].trim());
        } String prueba = new String(bytes);
        return  prueba;
    }

}