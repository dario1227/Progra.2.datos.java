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

import java.io.IOException;

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
    
                Stage stage = (Stage) loginBtn.getScene().getWindow();
                stage.close();
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
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        Stage stage = (Stage) regBtn.getScene().getWindow();
        stage.close();
    }
    
    // RUN THIS WHEN THE LOGIN IS CORRECT
    public void openPlayer () {
    
        new Thread(() -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("App.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Odyssey++");
                stage.setScene(new Scene(root, 1280, 720));
                stage.setResizable(true);
                stage.setOnCloseRequest(e -> Main.exitServer());
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
