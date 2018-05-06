package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientServer {

            public static void run() throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
                //get the localhost IP address, if server is running on some other IP, you need to use that
                InetAddress host = InetAddress.getLocalHost();
                Socket socket = null;
                ObjectOutputStream oos = null;
                ObjectInputStream ois = null;
                    socket = new Socket(host.getHostName(), 5000);
                    PrintStream PS =new PrintStream(socket.getOutputStream());
                    PS.println("HELLO TO SERVER FROM CLIENT");
                    System.out.println("Sending request to Socket Server");
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    //read the server response message
                    ois = new ObjectInputStream(socket.getInputStream());
                    String message = (String) ois.readObject();
                    System.out.println("Message: " + message);
                    //close resources
                    ois.close();
                    oos.close();
                    Thread.sleep(100);
                }
            }
