package view;

import XML.XML_parser;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Metadata;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AppController {
    
    private static AppController instance;
    TablePages[] tablePages = new TablePages[3];
    ObservableList<Metadata> tableList = FXCollections.observableArrayList();
    private JFXTreeTableColumn<Metadata, String> nameColumn = new JFXTreeTableColumn<>("Title");
    
    @FXML
    private ImageView playPauseBtn;
    
    
    @FXML
    private JFXSlider songSlider;
    
    @FXML
    private JFXListView<?> friendsList;
    
    @FXML
    private JFXButton vizButton;
    
    
    @FXML
    private JFXTreeTableView<Metadata> songList;
    
    public static AppController getInstance () {
        return instance;
    }
    
    private JFXTreeTableColumn<Metadata, String> artistColumn = new JFXTreeTableColumn<>("Artist");
    
    private JFXTreeTableColumn<Metadata, String> yearColumn = new JFXTreeTableColumn<>("Year");
    
    private JFXTreeTableColumn<Metadata, String> albumColumn = new JFXTreeTableColumn<>("Album");
    
    private JFXTreeTableColumn<Metadata, String> genreColumn = new JFXTreeTableColumn<>("Genre");
    
    private JFXTreeTableColumn<Metadata, String> lyricsColumn = new JFXTreeTableColumn<>("Lyrics");
    
    @FXML
    private void initialize () {
    
        nameColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Metadata, String> param) -> new ReadOnlyObjectWrapper<>(param.getValue().getValue().title));
    
    
        artistColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Metadata, String> param) -> new ReadOnlyObjectWrapper<>(param.getValue().getValue().artist));
    
    
        albumColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Metadata, String> param) -> new ReadOnlyObjectWrapper<>(param.getValue().getValue().album));
    
    
        yearColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Metadata, String> param) -> new ReadOnlyObjectWrapper<>(param.getValue().getValue().year));
    
        genreColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Metadata, String> param) -> new ReadOnlyObjectWrapper<>(param.getValue().getValue().genre));
    
    
        final TreeItem<Metadata> root = new RecursiveTreeItem<>(tableList, RecursiveTreeObject::getChildren);
    
    
        instance = this;
    
        songList.setRoot(root);
        songList.setShowRoot(false);
        songList.setEditable(false);
        songList.getColumns()
                .setAll(nameColumn, artistColumn, albumColumn, yearColumn, genreColumn, lyricsColumn);
    
        tablePages[0] = populateTable(0);
        tableList.addAll(tablePages[0].songs);
    
        tablePages[1] = populateTable(1);
        tableList.addAll(tablePages[1].songs);
    
        tablePages[2] = populateTable(2);
        tableList.addAll(tablePages[2].songs);
        
        
    }
    
    
    //TODO CONTEXT MENU PARA PROPIEDADES DE CADA CANCION
    
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
    
    @FXML
    public void startGeneticView () {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Genetic.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Generic Search");
            stage.setScene(new Scene(root1, 600, 400));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Integer chunknum = 0;
//        String songNombre = "13.mp3";
//        byte[] test = XML_parser.get_chunk_bytes(songNombre, chunknum.toString());
//        MP3Bytes mp3Bytes = new MP3Bytes(test, songNombre);
//        mp3Bytes.play();
    }
    
    @FXML
    void contextMenu (ContextMenuEvent event) {
        ContextMenu altMenu = new ContextMenu();
        MenuItem details = new MenuItem("Details");
        details.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                try {
                    Metadata selected = songList.getSelectionModel().getSelectedItem().getValue();
                    DetailsDialog dialog = new DetailsDialog();
                    dialog.showAndWait(selected);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        altMenu.getItems().add(details);
        altMenu.show(Main.getmStage(), event.getSceneX(), event.getSceneY());
    }
    
    
    
    
    
    
    
    
    
    
    
    
    //DALE ACA !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
    //METODO PARA CONECTAR CON LA PAGINACION XML
    private TablePages populateTable (int pageNumber) {
        TablePages page = null;
        try {
            page = new TablePages();

            page.pageNumber = pageNumber;
            page.totalSongs = //PEDIR POR XML
                    page.pages = //PEDIR POR XML
                            page.pageSize = //PEDIR POR XML

            //RECORRER CANCIONES E IR AGREGANDO
            for (songs:) {
                Metadata newSong = new Metadata();

                newSong.title = //PEDIR POR XML
                        newSong.album = //PEDIR POR XML
                                newSong.artist = //PEDIR POR XML
                                        page.songs.addAll(newSong);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
