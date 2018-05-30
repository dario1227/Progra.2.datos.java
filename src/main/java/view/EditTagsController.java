package view;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.Metadata;

import java.util.ArrayList;
import java.util.List;

public class EditTagsController {
    
    @FXML
    private JFXTextArea lyricsLabel;
    
    @FXML
    private JFXTextField titleLabel;
    
    @FXML
    private JFXTextField artistLabel;
    
    @FXML
    private JFXTextField albumLabel;
    
    @FXML
    private JFXTextField genreLabel;
    
    @FXML
    private JFXButton saveBtn;
    
    @FXML
    private JFXButton cancelBtn;
    private List<String> data = new ArrayList<>();
    
    @FXML
    void discardChanges (ActionEvent event) {
        data.clear();
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void saveChanges () {
        
        if (! titleLabel.getText().equals(data.get(0))) {
            data.set(0, titleLabel.getText());
        }
        if (! artistLabel.getText().equals(data.get(1))) {
            data.set(1, artistLabel.getText());
        }
        if (! albumLabel.getText().equals(data.get(2))) {
            data.set(2, albumLabel.getText());
        }
        if (! genreLabel.getText().equals(data.get(3))) {
            data.set(3, genreLabel.getText());
        }
        if (! lyricsLabel.getText().equals(data.get(4))) {
            data.set(4, lyricsLabel.getText());
        }
        
        //ENVIAR AL SERVER LOS CAMBIOS
//        new Thread(() -> {
//            XML_parser.getXML_Archive(data.get(5), data.get(6), data.get(4), data.get(2), data.get(1), data.get(3));
//            data.clear();
//        }).start();
        
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
        
    }
    
    public void load (Metadata metadata) {
        titleLabel.setText(metadata.title);
        artistLabel.setText(metadata.artist);
        albumLabel.setText(metadata.album);
        genreLabel.setText(metadata.genre);
        lyricsLabel.setText(metadata.lyrics);
        
        data.add(titleLabel.getText());
        data.add(artistLabel.getText());
        data.add(albumLabel.getText());
        data.add(genreLabel.getText());
        data.add(lyricsLabel.getText());
        
    }
    
    public Metadata retrieve () {
        
        return null;
    }
}
