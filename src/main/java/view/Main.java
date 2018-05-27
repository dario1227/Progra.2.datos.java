package view;

import Server.ClientServer;
import XML.MP3Bytes;
import XML.XML_parser;
import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;


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
        Parent root = FXMLLoader.load(getClass().getResource("App.fxml"));
        mStage = primaryStage;
        mStage.setOnCloseRequest(e -> exitServer());
        mStage.setTitle("Odyssey++");
        //mStage.setScene(new Scene(root, 400, 300));
        mStage.setScene(new Scene(root, 1280, 720));
        //mStage.setResizable(false);
        mStage.show();
        //        ClientServer.Server.send("YOUR MOM GAY");
        //        ClientServer.Server.receive();
        // String cosa = XML_parser.getXML_Archive("/home/kenneth/Desktop/Tool - Pushit.mp3");
        // System.out.print("\n"+cosa);
        //  ClientServer.Server.send(cosa);
        //    XML_parser.getXML_Archive("/home/germago/13.mp3", "13.mp3", "Blah", "ISM", "Savant");


//        Integer chunknum = 0;
//        String songNombre = "Tool - H. w Lyrics (HD).mp3";
//        byte[] test = XML_parser.get_chunk_bytes(songNombre, chunknum.toString());
//        System.out.println("LARGO: " + test.length);
//        MP3Bytes mp3Bytes = new MP3Bytes(test, songNombre);
//        mp3Bytes.play();

//        InputStream stream = new ByteArrayInputStream(test);
//        System.out.println("STREAM: " + stream.toString());
//        Clip clip = AudioSystem.getClip();
//        AudioInputStream buffer = AudioSystem.getAudioInputStream(stream);
//        clip.open(buffer);
//        System.out.println("CLIP START/ Buffer: " + buffer.toString());
//        clip.start();


//        File f = new File("/home/germago/13.mp3");
//        byte[] as = Files.readAllBytes(f.toPath());
//        System.out.println("LARGO 2: " + as.length);
//        MP3Bytes m = new MP3Bytes(as, "13.mp3");
//        if (mp3Bytes.isfinish()) {
//            System.out.println("TERMINO EL CHUNK 1");
//            m.play();
//        }
    }
    
}
