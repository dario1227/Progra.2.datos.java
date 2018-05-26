package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SearchDialogController {
    
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
        typeCombo.getItems().add(new Label("Genre"));
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
    }
    
}

