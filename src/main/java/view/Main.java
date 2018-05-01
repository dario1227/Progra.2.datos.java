package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Cambiar a Login.fxml para probar metodos o a Main.fxml para reproductor
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("Odyssey++");
        primaryStage.setScene(new Scene(root, 1040, 650));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}