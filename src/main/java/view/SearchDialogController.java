package view;

import XML.Canciones;
import XML.XML_parser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class SearchDialogController {
    public static String actualPage = "0";
    public static String orden;
    public static String parametro;
    public static String nombre;
    @FXML
    private JFXTextField searchTextField;
    
    @FXML
    private JFXComboBox<Label> typeCombo;
    
    @FXML
    private JFXRadioButton yesRadioBtn;
    
    @FXML
    private JFXRadioButton noRadioBtn;
    
    @FXML
    private JFXButton searchButton;
    
    @FXML
    public void initialize () {
        typeCombo.getItems().add(new Label("Lyrics"));
        typeCombo.getItems().add(new Label("Title"));
        typeCombo.getItems().add(new Label("Album"));
        typeCombo.getItems().add(new Label("Artist"));
        typeCombo.setEditable(false);
    }
    
    @FXML
    void disableNo (ActionEvent event) {
        noRadioBtn.setSelected(false);
        
    }
    
    @FXML
    void disableYes (ActionEvent event) {
        yesRadioBtn.setSelected(false);
    }
    
    @FXML
    void saveSettings (ActionEvent event) {
        String cosa ;
        String parametro2 = typeCombo.getValue().getText();
        if(yesRadioBtn.isSelected()){
            orden = "true";
        }
        else{
            orden = "false";
        }
        nombre = searchTextField.getText();
        parametro = metodo_busqueda(parametro2);

      //  ArrayList<Canciones> canciones = XML_parser.get_songs(parametro_parseado, actualPage, busqueda, cosa);
        AppController.instance.populateTable(0);
    }
    String metodo_busqueda(String ingles){
        if(ingles.contains("Album")){
            return "Album";
        }
        if(ingles.contains("Artist")){
            return  "Autor";
        }
        if(ingles.contains("Title")){
return "Nombre";
        }
        if(ingles.contains("Lyrics")){
return "Letra";
        }
        return " null";
    }
    
    
}

