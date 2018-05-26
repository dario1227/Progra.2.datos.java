package view;

import XML.XML_parser;
import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.Metadata;

import java.io.File;
import java.util.*;

public class AppController {
    
    ObservableList<Metadata> songs = FXCollections.observableArrayList();
    
    @FXML
    private ImageView playPauseBtn;
    
    @FXML
    private MaterialDesignIconView searchDialog;
    
    @FXML
    private JFXSlider songSlider;
    
    @FXML
    private JFXListView<?> friendsList;
    
    @FXML
    private JFXButton vizButton;
    
    
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
    
    @FXML
    private void openSearchDialog (ActionEvent actionEvent) {
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SearchDialog.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Search");
            stage.setScene(new Scene(root1, 400, 300));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
