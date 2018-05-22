import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GenericController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label answerLabel;

    @FXML
    private JFXButton guessBtn;

    @FXML
    private JFXTextField genericTextField;

    @FXML
    void genericSearch(ActionEvent event) {

    	String s = genericTextField.getText();
    	answerLabel.setText(s)

    }