package view;

import Server.ClientServer;
import XML.XML_parser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.util.Base64;


public class Main extends Application {


    public static void main(String[] args) {
        String str = "";
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        System.out.print(cosa.charAt(cosa.length()));
//        byte bytes[] = Base64.getDecoder().decode(cosa);
//        try (FileOutputStream fos = new FileOutputStream("cosa.mp3")) {
//            fos.write(bytes);
//            //fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream
//        }
        //XML_parser.createAccount("Caca","23","23","HFHDHDHD");
        //XML_parser.loginRequest("MEME","MSMEMSMSM");
        //XML_parser.get_songs("Caca","2");
        //XML_parser.chunk("MEME.mp3","2");
        //XML_parser.prueba();
        //Cambiar a Login.fxml para probar metodos o a Main.fxml para reproductor
        //Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        //primaryStage.setTitle("Odyssey++");
        //primaryStage.setScene(new Scene(root, 1040, 650));
        //primaryStage.setResizable(false);
       // primaryStage.show();
       ClientServer client = new ClientServer();
       Thread clientThread = new Thread(client);
        clientThread.start();
    }
}
