package view;

import XML.XML_parser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import player.OdysseyPlayer;

public class LoginController {
    
    
    @FXML
    private JFXPasswordField passwordField;
    
    @FXML
    private JFXButton loginBtn;
    
    @FXML
    private JFXTextField userField;
    
    @FXML
    private JFXButton regBtn;
    
    /**
     * Extrae texto que uno escribe en el user y la pone en password y al mismo tiempo desactiva el
     * boton de login. En el FXML esta para que cuando se de click al boton de login se corra este
     * metodo. IMPORTANTE SIEMPRE PONER @FXML
     */
    @FXML
    private void setPasswordField () {
        String user = userField.getText();
        String pass = passwordField.getText();
        if ((user != null) && (pass != null)) {
            if (XML_parser.loginRequest(user, pass)) {
                AppController.USER = user;
                openPlayer();
            } else {
                // TODO Error Dialog
            }
        }
    }
    
    @FXML
    private void newRegister () {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Register");
            stage.setScene(new Scene(root1, 500, 400));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // RUN THIS WHEN THE LOGIN IS CORRECT
    private void openPlayer () {
        OdysseyPlayer player = new OdysseyPlayer();
        Main.getmStage().setResizable(true);
        player.start(Main.getmStage());
    }
}
