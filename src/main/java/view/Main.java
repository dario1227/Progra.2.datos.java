package view;

import Server.ClientServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    
    private static Stage mStage;
    
    
    public static void main (String[] args) {
        launch(args);
    }
    
    public static Stage getmStage () {
        return mStage;
    }
    
    public static void exitServer () {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Request Server Shutdown");
        alert.showAndWait();
        try {
            ClientServer.Server.send("Salir");
            ClientServer.Server.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    
    @Override
    public void start (Stage primaryStage) throws Exception {
        
        ClientServer.Server = new ClientServer();
        // XML_parser.createAccount("alaba", "0", "666", "GG");
        //        XML_parser.getXML_Archive(
        //                "/home/kenneth/Desktop/Tool - H. w Lyrics (HD).mp3",
        //                "Tool - H. w Lyrics (HD).mp3",
        //                "What's coming through is a lie\n" +
        //                        "What's holding up is a mirror\n" +
        //                        "But what's singing songs is a snake\n" +
        //                        "Looking to turn this piss to wine\n" +
        //                        "\n" +
        //                        "They're both totally void of hate\n" +
        //                        "But killing me just the same",
        //                "Cosa",
        //                "Tool"
        //        );
        //        XML_parser.loginRequest("kenneth", "darksouls");
        //         XML_parser.createAccount("Manolo","23","23","juano");
        // XML_parser.getXML_Archive(
        /// "/home/kenneth/Desktop/Caravan Palace - Lone Digger-[AudioTrimmer.com].mp3",
        /// "Caravan Palace - Lone Digger-[AudioTrimmer.com].mp3",
        /// "MAMA yo no fui dile que yo no fui",
        /// "Mama",
        /// "Mamas and the papas");
        /// XML_parser.get_songs("Nombre", "1", "MEME");
        //   XML_parser.chunk("Tool - H. w Lyrics (HD).mp3", "1");
        // System.out.print("TERMINO ALELUYA");
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
        mStage.setTitle("Login");
        mStage.setScene(new Scene(root, 400, 300));
        mStage.setResizable(false);
        mStage.show();
    }
    
}
