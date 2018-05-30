package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SearchDialogController {
    public static String actualPage = "1";
    public static String orden = "Nada";
    public static String parametro = "Nada";
    public static String nombre = "Nada";
    public static String sorted = "Nada";
    
    @FXML
    private JFXTextField searchTextField;
    
    @FXML
    private JFXComboBox<Label> typeCombo;
    
    @FXML
    private JFXComboBox<Label> sortCombo;
    
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
    
        sortCombo.getItems().add(new Label("Lyrics"));
        sortCombo.getItems().add(new Label("Title"));
        sortCombo.getItems().add(new Label("Album"));
        sortCombo.getItems().add(new Label("Artist"));
        sortCombo.setEditable(false);
        
        
    }
    
    @FXML
    void disableNo (ActionEvent event) {
        noRadioBtn.setSelected(false);
        sortCombo.setDisable(false);
        
    }
    
    @FXML
    void disableYes (ActionEvent event) {
        yesRadioBtn.setSelected(false);
        sortCombo.setDisable(true);
    }
    
    @FXML
    void saveSettings (ActionEvent event) {
        String cosa;
        String parametro2 = typeCombo.getValue().getText();
    
        if (yesRadioBtn.isSelected()) {
            orden = "true";
        } else {
            orden = "false";
        }
        nombre = searchTextField.getText();
        parametro = metodo_busqueda(parametro2);
        try {
            sorted = metodo_busqueda(sortCombo.getValue().getText());
        } catch (Exception e) {
            sorted = " ";
        }
        //  ArrayList<Canciones> canciones = XML_parser.get_songs(parametro_parseado, actualPage, busqueda, cosa);
        AppController.instance.populateTable();
    }
    
    String metodo_busqueda (String ingles) {
        if (ingles.contains("Album")) {
            return "Album";
        }
        if (ingles.contains("Artist")) {
            return "Autor";
        }
        if (ingles.contains("Title")) {
            return "Nombre";
        }
        if (ingles.contains("Lyrics")) {
            return "Letra";
        }
        return " null";
    }
    
    
}

