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
    byte[] buf=new byte[70000];

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
            try{
            Thread.sleep(500);}catch (Exception e){}
            System.out.print(clientInput.available());
           message =  this.clientInput.read(buf, 0,buf.length);
            recivido+=new String(buf);

            // message=clientInput.read();
            //System.out.print(buf);
            if(clientInput.available()==0)
            {
                break;
            }
          //  recivido+=message;
          //  recivido+=',';
          //  System.out.print((char) message);

        }//String[] byteValues = recivido.substring(0, recivido.length()).split(",");
      // byte[] bytes = new byte[byteValues.length];

//        for (int i=0, len=bytes.length; i<len; i++) {
//            bytes[i] = Byte.parseByte(byteValues[i].trim());
     //   }
        String prueba = recivido.split("#")[0];
        return  prueba;
    }

}