package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

import javax.xml.soap.Text;

public class GeneticController {

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

    //BOTON GUESS
    @FXML
    void genericSearch(ActionEvent event) {

        String s = genericTextField.getText();
        answerLabel.setText(s);
    }

    //BOTON START
    @FXML
    void startGame(ActionEvent event) {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Select a Song");
        inputDialog.setHeaderText("Please type a song name");
        inputDialog.setContentText("Song:");

        Optional<String> res = inputDialog.showAndWait();

        res.ifPresent(System.out::println);

        res.ifPresent(s -> answerLabel.setText(s));

        //EJEMPLO YA ESTA EL DIALOG Y AHORITA AGARRA EL RESULTADO Y LO PONE EN answerLabel.
    }
}