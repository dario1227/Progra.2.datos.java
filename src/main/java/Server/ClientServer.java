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
    public static boolean estado = false;
    public  static int estado2=0;
    public ClientServer () throws IOException {
        this.socket = new Socket("localhost", 5400);
        System.out.println("Connected to server!");
        this.clientInput = new DataInputStream(socket.getInputStream());
        this.clientOutput = new DataOutputStream(socket.getOutputStream());
        send("Connected");
        estado=false;
    }

    public void send (String message) throws IOException {
        while(estado){
System.out.println(estado);
        }
        estado=true;
        this.clientOutput.write(message.getBytes());
    }

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
            if (clientInput.available() == 0||recivido.contains("#")) {
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
        System.out.println(prueba+"MEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        estado=false;

        return prueba;
    }
}
