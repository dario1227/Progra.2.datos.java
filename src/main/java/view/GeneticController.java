package view;

import Structures.Genetic_algorithm;
import Structures.ListaSimple;
import Structures.Palabra;
import XML.Canciones;
import XML.XML_parser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class GeneticController {
    static Genetic_algorithm algoritmo;
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
    /**
     * Inicia la busqueda
     */
    void genericSearch (ActionEvent event) {
    
        // String s = genericTextField.getText();
        // String s = "lol";
        // answerLabel.setText(s);
        String numero = genericTextField.getText();
        if (numero.isEmpty()) {
            Palabra palabra = algoritmo.palabra_mas_coincidencia();
            answerLabel.setText(palabra.palabra);
            return;
        }
        String[] numeros = numero.split(",");
        ListaSimple<Integer> list = new ListaSimple<Integer>();
        int x = 0;
        while (x < numeros.length) {
            list.append(Integer.parseInt(numeros[x]));
            x++;
        }
        algoritmo.reordenate(answerLabel.getText(), list);
        Palabra palabra = algoritmo.palabra_mas_coincidencia();
        answerLabel.setText(palabra.palabra);
    }
    
    //BOTON START
    @FXML
    void startGame (ActionEvent event) {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Select a Song");
        inputDialog.setHeaderText("Please type a song name");
        inputDialog.setContentText("Song:");
        Optional<String> res = inputDialog.showAndWait();
        System.out.println(res.get());
        System.out.println(inputDialog.getDefaultValue());
        res.ifPresent(System.out::println);
        res.ifPresent(s -> answerLabel.setText(s));
        ArrayList<Canciones> cancion = XML_parser.get_songs("Nombre", "1", res.get(), "false", "LOL");
        if (cancion.isEmpty()) {
            return;
        }
        System.out.println(cancion.get(0).letra);
        algoritmo = new Genetic_algorithm(cancion.get(0).letra);
        algoritmo.analize_cancion();
        answerLabel.setText(algoritmo.random().palabra);
        //EJEMPLO YA ESTA EL DIALOG Y AHORITA AGARRA EL RESULTADO Y LO PONE EN answerLabel.
    }
}