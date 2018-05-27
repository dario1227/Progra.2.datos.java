package view;

import XML.XML_parser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import model.Metadata;

import java.util.*;

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
    
    private List<String> data = new ArrayList<>();
    
    public void load (Metadata metadata) {
        titleLabel.setText(metadata.title);
        artistLabel.setText(metadata.artist);
        yearLabel.setText(metadata.year);
        albumLabel.setText(metadata.album);
        genreLabel.setText(metadata.genre);
        lyricsLabel.setText(metadata.lyrics);
    
        data.add(titleLabel.getText());
        data.add(artistLabel.getText());
        data.add(yearLabel.getText());
        data.add(albumLabel.getText());
        data.add(genreLabel.getText());
        data.add(lyricsLabel.getText());
    
        data.add(metadata.fullpath);
        data.add(metadata.filename);
        
        //TODO setImage(metadata.cover);
    }
    
    public Metadata retrieve () {
//        return new Metadata();
        return null;
    }
    
    @FXML
    void discardChanges (ActionEvent event) {
    
        data.clear();
        
    }
    
    @FXML
    void saveChanges (ActionEvent event) {
        if (! titleLabel.getText().equals(data.get(0))) {
            data.set(0, titleLabel.getText());
        }
        if (! artistLabel.getText().equals(data.get(1))) {
            data.set(1, artistLabel.getText());
        }
        if (! yearLabel.getText().equals(data.get(2))) {
            data.set(2, yearLabel.getText());
        }
        if (! albumLabel.getText().equals(data.get(3))) {
            data.set(3, albumLabel.getText());
        }
        if (! genreLabel.getText().equals(data.get(4))) {
            data.set(4, genreLabel.getText());
        }
        if (! lyricsLabel.getText().equals(data.get(5))) {
            data.set(5, lyricsLabel.getText());
        }
    
        XML_parser.getXML_Archive(data.get(6), data.get(7), data.get(5), data.get(3), data.get(1));
        data.clear();
    
    }
    
}
