
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import model.Metadata;

public class MP3ID3TagController {
    
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
    private JFXTextField yearLabel;
    
    @FXML
    private JFXButton saveBtn;
    
    @FXML
    private JFXButton cancelBtn;
    
    @FXML
    private ImageView coverArtImg;
    
    public void load (Metadata metadata) {
        titleLabel.setText(metadata.title);
        artistLabel.setText(metadata.artist);
        yearLabel.setText(metadata.year);
        albumLabel.setText(metadata.album);
        genreLabel.setText(metadata.genre);
        lyricsLabel.setText(metadata.lyrics);
        
        //TODO setImage(metadata.cover);
    }
    
    public Metadata retrieve () {
//        return new Metadata();
        return null;
    }
    
    @FXML
    void discardChanges (ActionEvent event) {
    
    }
    
    @FXML
    void saveChanges (ActionEvent event) {
    
    }
    
}
