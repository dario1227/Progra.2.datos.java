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

    private static Stage mStage;


    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getmStage() {
        return mStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        ClientServer.Server = new ClientServer();
        //XML_parser.createAccount("alaba", "0", "666", "GG");
        //XML_parser.getXML_Archive(
//                "/home/germago/Music/1 song/04 Addicted To You.mp3",
//                "04 Addicted To You.mp3",
//                "GG",
//                "True",
//                "Avicii"
//        );
//        XML_parser.loginRequest("kenneth", "darksouls");
//         XML_parser.createAccount("Manolo","23","23","juano");
        //XML_parser.getXML_Archive(
        ///"/home/kenneth/Desktop/Caravan Palace - Lone Digger-[AudioTrimmer.com].mp3",
        ///"Caravan Palace - Lone Digger-[AudioTrimmer.com].mp3",
        ///"MAMA yo no fui dile que yo no fui",
        ///"Mama",
        ///"Mamas and the papas");
        ///XML_parser.get_songs("Nombre", "1", "MEME");
        //XML_parser.chunk("Caravan Palace - Lone Digger-[AudioTrimmer.com].mp3", "1");
        //System.out.print("TERMINO ALELUYA");
        //        System.out.print(cosa.charAt(cosa.length()));
        //        byte bytes[] = Base64.getDecoder().decode(cosa);
        //        try (FileOutputStream fos = new FileOutputStream("cosa.mp3")) {
        //            fos.write(bytes);
        //            //fos.close(); There is no more need for this line since you had created the
        // instance of "fos" inside the try. And this will automatically close the OutputStream
        //        }
        // XML_parser.createAccount("Caca","23","23","HFHDHDHD");
        // XML_parser.loginRequest("MEME","MSMEMSMSM");
        // XML_parser.get_songs("Caca","2");
        // XML_parser.chunk("MEME.mp3","2");
        // XML_parser.prueba();
        // Cambiar a Login.fxml para probar metodos o a Main.fxml para reproductor
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        mStage = primaryStage;
        mStage.setTitle("Odyssey++");
        mStage.setScene(new Scene(root, 400, 300));
        mStage.setResizable(false);
        mStage.show();
        //        ClientServer.Server.send("YOUR MOM GAY");
        //        ClientServer.Server.receive();
        // String cosa = XML_parser.getXML_Archive("/home/kenneth/Desktop/Tool - Pushit.mp3");
        // System.out.print("\n"+cosa);
        //  ClientServer.Server.send(cosa);
    }
}
