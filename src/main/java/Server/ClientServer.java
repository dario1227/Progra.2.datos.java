package Server;

import java.io.*;
import java.net.Socket;

public class ClientServer {
    public static ClientServer Server;
    private static int userID;
    public Socket socket;
    public InputStream clientInput;
    public OutputStream clientOutput;
    byte[] buf = new byte[100000];
    private int message;
    
    public ClientServer () throws IOException {
        this.socket = new Socket("localhost", 5400);
        System.out.println("Connected to server!");
        this.clientInput = new DataInputStream(socket.getInputStream());
        this.clientOutput = new DataOutputStream(socket.getOutputStream());
        send("Connected");
    }

    /**
     * envia archivo xml
     * @param message
     * @throws IOException
     */
    public void send (String message) throws IOException {
        this.clientOutput.write(message.getBytes());
    }

    /**
     * Recibe y envia a parsear archivo xml
     * @return
     * @throws IOException
     */
    public String receive () throws IOException {
        String recivido = "";
        while (true) {
            try {
                Thread.sleep(300);
            } catch (Exception e) {
            }
            System.out.print(clientInput.available());
            message = this.clientInput.read(buf, 0, buf.length);
            recivido += new String(buf);
            
            // message=clientInput.read();
            // System.out.print(buf);
            if (clientInput.available() == 0) {
                break;
            }
            //  recivido+=message;
            //  recivido+=',';
            //  System.out.print((char) message);
    
        } // String[] byteValues = recivido.substring(0, recivido.length()).split(",");
        // byte[] bytes = new byte[byteValues.length];
        
        //        for (int i=0, len=bytes.length; i<len; i++) {
        //            bytes[i] = Byte.parseByte(byteValues[i].trim());
        //   }
        String prueba = recivido.split("#")[0];
        return prueba;
    }
}
