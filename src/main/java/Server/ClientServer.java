package Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientServer {

    public static void run() throws Exception
    {
        String adrress="localhost"; // -- samemachine
        Socket SOCK =new Socket (adrress,5000);
        PrintStream PS =new PrintStream(SOCK.getOutputStream());
        PS.println("HELLO TO SERVER FROM CLIENT");
        InputStreamReader IR =new InputStreamReader(SOCK.getInputStream());
        BufferedReader BR = new BufferedReader(IR);
        String MESSAGE =BR.readLine();
        System.out.println(MESSAGE + "java");
    }

}
