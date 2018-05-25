package view;

import XML.XML_parser;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.Metadata;

import java.io.File;
import java.util.List;

public class AppController {
    
    ObservableList<Metadata> songs = FXCollections.observableArrayList();
    
    @FXML
    private ImageView playPauseBtn;
    
    @FXML
    private JFXSlider songSlider;
    
    @FXML
    private JFXListView<?> friendsList;
    
    @FXML
    private JFXTreeTableView<Metadata> songList;
    
    private JFXTreeTableColumn<Metadata, String> nameColumn = new JFXTreeTableColumn<>("Name");
    
    private JFXTreeTableColumn<Metadata, String> artistColumn = new JFXTreeTableColumn<>("Artist");
    
    private JFXTreeTableColumn<Metadata, String> yearColumn = new JFXTreeTableColumn<>("Year");
    
    private JFXTreeTableColumn<Metadata, String> albumColumn = new JFXTreeTableColumn<>("Album");
    
    private JFXTreeTableColumn<Metadata, String> genreColumn = new JFXTreeTableColumn<>("Genre");
    
    private JFXTreeTableColumn<Metadata, String> lyricsColumn = new JFXTreeTableColumn<>("Lyrics");
    
    @FXML
    private void initialize () {
        songList.setShowRoot(false);
        songList.setEditable(false);
        songList.getColumns()
                .setAll(nameColumn, artistColumn, albumColumn, yearColumn, genreColumn, lyricsColumn);
    }
    
    @FXML
    void nextSong (ActionEvent event) {
    }
    
    @FXML
    void playPauseSong (ActionEvent event) {
    }
    
    @FXML
    void prevSong (ActionEvent event) {
    }
    
    @FXML
    void uploadSong (ActionEvent event) {
        FileChooser browser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Audio files", "*.mp3");
        browser.getExtensionFilters().setAll(filter);
        browser.setTitle("Select songs to upload");
        List<File> files = browser.showOpenMultipleDialog(Main.getmStage());
        if (! files.isEmpty()) {
            for (File file : files) {
                uploadServer(file);
                System.out.println(file.getName());
            }
        }
    }
    
    private void uploadServer (File file) {
        
        try {
            Metadata data = new Metadata(file.toPath().toString());
            
            XML_parser.getXML_Archive(
                    file.getAbsolutePath(), file.getName(), data.lyrics, data.album, data.artist);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private ObservableList<Metadata> populateTable(){
//        XML_parser.get_songs(, , , )
//        return FXCollections.observableArrayList();
//    }

}
