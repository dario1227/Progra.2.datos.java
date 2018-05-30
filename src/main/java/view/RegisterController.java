package view;

import XML.XML_parser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class RegisterController {
    
    @FXML
    private AnchorPane pane;
    
    private static Integer id = 0;
    @FXML
    public JFXTextField regUserText;
    @FXML
    public JFXPasswordField regPassText;
    @FXML
    public JFXTextField regAgeText;
    @FXML
    public JFXButton createBtn;
    
    @FXML
    private void createHandler (ActionEvent ac) {
        String user = regUserText.getText();
        String pass = regPassText.getText();
        String age = regAgeText.getText();
        if ((user != null) && (pass != null) && (age != null)) {
            if (XML_parser.createAccount(user, id.toString(), age, pass)) {
                JFXSnackbar error = new JFXSnackbar(pane);
                error.show("Success", 1000);
                
                id++;
                LoginController.usuario = user;
    
                AppController.USER = user;
    
                LoginController lg = new LoginController();
                lg.openPlayer();
    
                Stage stage = (Stage) createBtn.getScene().getWindow();
                stage.close();
    
    
            } else {
                JFXSnackbar error = new JFXSnackbar(pane);
                error.show("Error", 2000);
                
            }
        }
    }
}
