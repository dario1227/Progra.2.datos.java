package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class LoginController {

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private JFXTextField userField;

    /**
     * Extrae texto que uno escribe en el user y la pone en password y al mismo tiempo desactiva el boton de login. En el FXML esta para que cuando se de click al boton de login se corra este metodo.
     * IMPORTANTE SIEMPRE PONER @FXML
     */
    @FXML
    private void setPasswordField() {
        String s = userField.getText();
        passwordField.setText(s);
        loginBtn.setDisable(true);
    }
}
