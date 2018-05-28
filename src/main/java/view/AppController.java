package view;

import XML.Canciones;
import XML.XML_parser;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Metadata;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

public class AppController {
    
    public static String USER;
    public static AppController instance;
    TablePages[] tablePages = new TablePages[3];
    ObservableList<Metadata> tableList = FXCollections.observableArrayList();

    
    @FXML
    private ImageView playPauseBtn;
    
    @FXML
    private JFXButton prevPageBtn;
    
    @FXML
    private JFXButton nextPageBtn;
    
    @FXML
    private JFXSlider songSlider;
    
    @FXML
    private JFXListView<?> friendsList;
    
    @FXML
    private JFXButton vizButton;
    
    
    @FXML
    private JFXTreeTableView<Metadata> songList;
    private JFXTreeTableColumn<Metadata, String> nameColumn = new JFXTreeTableColumn<>("Title");
    private JFXTreeTableColumn<Metadata, String> artistColumn = new JFXTreeTableColumn<>("Artist");
    private JFXTreeTableColumn<Metadata, String> albumColumn = new JFXTreeTableColumn<>("Album");
    private JFXTreeTableColumn<Metadata, String> genreColumn = new JFXTreeTableColumn<>("Genre");
    private JFXTreeTableColumn<Metadata, String> lyricsColumn = new JFXTreeTableColumn<>("Lyrics");
    
    public static AppController getInstance () {
        return instance;
    }
    
    @FXML
    private void initialize () {
    
        nameColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Metadata, String> param) -> new ReadOnlyObjectWrapper<>(param.getValue().getValue().title));


        artistColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Metadata, String> param) -> new ReadOnlyObjectWrapper<>(param.getValue().getValue().artist));


        albumColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Metadata, String> param) -> new ReadOnlyObjectWrapper<>(param.getValue().getValue().album));


        genreColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Metadata, String> param) -> new ReadOnlyObjectWrapper<>(param.getValue().getValue().genre));
        lyricsColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Metadata, String> param) -> new ReadOnlyObjectWrapper<>(param.getValue().getValue().lyrics));
    
        final TreeItem<Metadata> root = new RecursiveTreeItem<>(tableList, RecursiveTreeObject::getChildren);
    
    
        instance = this;
    
        songList.setRoot(root);
        songList.setShowRoot(false);
        songList.setEditable(false);
        songList.getColumns()
                .setAll(nameColumn, artistColumn, albumColumn, genreColumn, lyricsColumn);
        //AQUI ES ESTO
        tablePages[0] = populateTable();
    //    tableList.addAll(tablePages[0].songs);

//        tablePages[1] = populateTable(1);
//        tableList.addAll(tablePages[1].songs);
//
//        tablePages[2] = populateTable(2);
//        tableList.addAll(tablePages[2].songs);
        
        
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
    
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MP3ID3Tag.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Details");
            stage.setScene(new Scene(root1, 600, 400));
    
            MP3ID3TagController controller = fxmlLoader.getController();
            controller.load(data);
    
            stage.show();
                
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
        details.setOnAction(event1 -> {
            try {
                Metadata selected = songList.getSelectionModel().getSelectedItem().getValue();
                DetailsDialog dialog = new DetailsDialog();
                dialog.showAndWait(selected);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        altMenu.getItems().add(details);
        altMenu.show(Main.getmStage(), event.getSceneX(), event.getSceneY());
    }
    
    
    //DALE ACA !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
    //METODO PARA CONECTAR CON LA PAGINACION XML
    public TablePages populateTable () {
        tableList.clear();
        final CountDownLatch latch = new CountDownLatch(1);
        final TablePages[] value = new TablePages[1];
    

        
            TablePages page = null;
            try {
                page = new TablePages();
            
                ArrayList<Canciones> canciones = XML_parser.get_songs(SearchDialogController.parametro, SearchDialogController.actualPage, SearchDialogController.nombre, SearchDialogController.orden);
            
                //RECORRER CANCIONES E IR AGREGANDO
                if (canciones != null) {
                    int x = 0;
                    while (x < canciones.size()) {
                        Metadata newSong = new Metadata();
                        newSong.title = canciones.get(x).nombre;//PEDIR POR XML
                        newSong.album = canciones.get(x).album;//PEDIR POR XML
                        newSong.artist = canciones.get(x).artista;//PEDIR POR XML
                        newSong.lyrics=canciones.get(x).letra;
                        page.songs.addAll(newSong);
                        x++;
                    }Metadata Redcomended =  new Metadata();
                    Redcomended.title="Recomended";
                    Redcomended.lyrics="";
                    page.songs.addAll(Redcomended);
                    canciones = XML_parser.get_songs("Random","1","Random","Random");
                    x=0;
                    while (x < canciones.size()) {
                        Metadata newSong = new Metadata();
                        newSong.title = canciones.get(x).nombre;//PEDIR POR XML
                        newSong.album = canciones.get(x).album;//PEDIR POR XML
                        newSong.artist = canciones.get(x).artista;//PEDIR POR XML
                        newSong.lyrics=canciones.get(x).letra;
                        page.songs.addAll(newSong);
                        x++;
                    }
                    value[0] = page;
                    tableList.addAll(page.songs);
                    latch.countDown();
                } else {
                    value[0] = null;

                }
            
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    
    
        return value[0];
    
    }
    
    @FXML
    void prevPageRequest (ActionEvent event) {
        if (SearchDialogController.actualPage.equals("1")) {
            //Do nothing
        } else {
            Integer i = Integer.parseInt(SearchDialogController.actualPage);
            i--;
            SearchDialogController.actualPage = i.toString();
            populateTable();
        }
    }
    
    @FXML
    void nextPageRequest (ActionEvent event) {
        if (Integer.parseInt(SearchDialogController.actualPage) >= 1) {
            Integer i = Integer.parseInt(SearchDialogController.actualPage);
            i++;
            SearchDialogController.actualPage = i.toString();
            populateTable();
        }
        
    }
    
    @FXML
    void addFriend (ActionEvent a) {
        
        TextInputDialog dialog = new TextInputDialog("Isaac");
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("Look, a Text Input Dialog");
        dialog.setContentText("Please enter your friend's name:");
        
        
        Optional<String> result = dialog.showAndWait();
        
        result.ifPresent(name -> System.out.println("FRIEND name: " + name));
        
        
    }
    
}
