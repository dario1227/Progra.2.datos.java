package view;

import XML.XML_parser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Metadata;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

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
    private JFXButton saveBtn;
    
    @FXML
    private JFXButton cancelBtn;
    
    @FXML
    private ImageView coverArtImg;
    
    private List<String> data = new ArrayList<>();
    
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
    
        data.add(metadata.fullpath);
        data.add(metadata.filename);
    
        if (metadata.cover.getBinaryData() != null) {
        
            byte[] bytes = metadata.cover.getBinaryData();
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            Image img = new Image(in, 200, 200, true, true);
            coverArtImg.setImage(img);
        
            if (img.isError()) {
                img = new Image("util/img/albumsIcon.png", 200, 200, true, true);
                coverArtImg.setImage(img);
            }
        } else {
            Image img = new Image("util/img/albumsIcon.png", 200, 200, true, true);
            coverArtImg.setImage(img);
        }
        
    }
    
    public Metadata retrieve () {
//        return new Metadata();
        return null;
    }
    
    @FXML
    void discardChanges (ActionEvent event) {
    
        data.clear();
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
        
        
    }
    
    @FXML
    void saveChanges (ActionEvent event) {
    
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
    
        new Thread(() -> {
            XML_parser.getXML_Archive(data.get(5), data.get(6), data.get(4), data.get(2), data.get(1), data.get(3));
            data.clear();
        }).start();
        
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    
    }
    
}
