package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import player.OdysseyPlayer;

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

    private Main m = new Main();

    @FXML
    private void setPasswordField() throws Exception {
        String s = userField.getText();
        passwordField.setText(s);
        if (s != null) {
            OdysseyPlayer player = new OdysseyPlayer();
            Main.getmStage().setResizable(true);
            player.start(Main.getmStage());
        }

    }
}
