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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Metadata;
import util.OdysseyPlayer;

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
    ObservableList<Label> friends = FXCollections.observableArrayList();
    private int currentlyPlaying;

    
    @FXML
    private ImageView playPauseBtn;
    
    @FXML
    private JFXButton prevPageBtn;
    
    @FXML
    private JFXButton nextPageBtn;
    
    @FXML
    private JFXSlider songSlider;
    
    @FXML
    private JFXListView<Label> friendsList = new JFXListView<Label>();
    
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
    
        nameColumn.prefWidthProperty().bind(songList.widthProperty().divide(3));
        nameColumn.setStyle("-fx-alignment: CENTER;");
        nameColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Metadata, String> param) ->
                                               new ReadOnlyObjectWrapper<>(param.getValue().getValue().title));
    
        artistColumn.prefWidthProperty().bind(songList.widthProperty().divide(5));
        artistColumn.setStyle("-fx-alignment: CENTER;");
        artistColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Metadata, String> param) ->
                                                 new ReadOnlyObjectWrapper<>(param.getValue().getValue().artist));
    
        albumColumn.prefWidthProperty().bind(songList.widthProperty().divide(4));
        albumColumn.setStyle("-fx-alignment: CENTER;");
        albumColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Metadata, String> param) ->
                                                new ReadOnlyObjectWrapper<>(param.getValue().getValue().album));
    
        genreColumn.prefWidthProperty().bind(songList.widthProperty().divide(5));
        genreColumn.setStyle("-fx-alignment: CENTER;");
        genreColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Metadata, String> param) ->
                                                new ReadOnlyObjectWrapper<>(param.getValue().getValue().genre));
        
        final TreeItem<Metadata> root = new RecursiveTreeItem<>(tableList, RecursiveTreeObject::getChildren);
    
    
        instance = this;
    
        songList.setRoot(root);
        songList.setShowRoot(false);
        songList.setEditable(false);
        songList.getColumns().setAll(nameColumn, artistColumn, albumColumn, genreColumn);
        songList.setPlaceholder(new Label("Song library is empty. \n Please UPLOAD new songs"));
        
        
        
        //AQUI ES ESTO
        tablePages[0] = populateTable();
    
        songSlider.setMin(0);
        songSlider.setMax(100);
        songSlider.setValue(0);
        OdysseyPlayer.getInstance().setSlider(songSlider);
    
        friendsList.getItems().add(new Label("Zero Friends :/"));
    addFriendList();
    }
    
    
    //TODO CONTEXT MENU PARA PROPIEDADES DE CADA CANCION
    
    @FXML
    void nextSong (ActionEvent event) {
    
        if (currentlyPlaying == tableList.size()) {
            return;
        }
        Metadata metadata = tableList.get(++ currentlyPlaying);
        if (! checkRecommended(metadata)) {
            OdysseyPlayer.getInstance().play(metadata, 0);
        } else {
            //Do nothing
        }
    }
    
    @FXML
    void playPauseSong (ActionEvent event) {
    
        OdysseyPlayer player = OdysseyPlayer.getInstance();
        if (player.isPlaying()) {
            player.pause();
        } else {
            player.unpause();
        }
        
    }
    
    @FXML
    void playSong (MouseEvent event) {
        
        System.out.println("Is playing before " + OdysseyPlayer.getInstance().isPlaying());
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
            
            TreeItem<Metadata> treeItem = songList.getSelectionModel().getSelectedItem();
            if (treeItem == null) {
                return;
            }
            if (checkRecommended(treeItem.getValue())) {
                return;
            }
            OdysseyPlayer.getInstance().play(treeItem.getValue(), 0);
            currentlyPlaying = songList.getSelectionModel().getSelectedIndex();
            System.out.println("Is playing after " + OdysseyPlayer.getInstance().isPlaying());
        }
        
    }
    
    
    
    @FXML
    void prevSong (ActionEvent event) {
        if (currentlyPlaying == 0) {
            return;
        }
        
        Metadata metadata = tableList.get(-- currentlyPlaying);
        if (! checkRecommended(metadata)) {
            OdysseyPlayer.getInstance().play(metadata, 0);
        } else {
            //Do nothing
        }
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
        altMenu.show(songList.getScene().getWindow(), event.getSceneX(), event.getSceneY());
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
            
                ArrayList<Canciones> canciones = XML_parser.get_songs(SearchDialogController.parametro, SearchDialogController.actualPage, SearchDialogController.nombre, SearchDialogController.orden,SearchDialogController.sorted);
//
                //RECORRER CANCIONES E IR AGREGANDO
                if (canciones != null) {
                    int x = 0;
                    while (x < canciones.size()) {
                        Metadata newSong = new Metadata();
                        newSong.title = canciones.get(x).nombre;//PEDIR POR XML
                        newSong.album = canciones.get(x).album;//PEDIR POR XML
                        newSong.artist = canciones.get(x).artista;//PEDIR POR XML
                        newSong.lyrics=canciones.get(x).letra;
                        newSong.genre = canciones.get(x).genero;
                        page.songs.addAll(newSong);
                        x++;
                    }Metadata Redcomended =  new Metadata();
                    Redcomended.title = "Recommended";
                    Redcomended.lyrics="";
                    page.songs.addAll(Redcomended);
                    canciones = XML_parser.get_songs("Random","1","Random","Random","R");
                    x=0;
                    assert canciones != null;
                    while (x < canciones.size()) {
                        Metadata newSong = new Metadata();
                        newSong.title = canciones.get(x).nombre;//PEDIR POR XML
                        newSong.album = canciones.get(x).album;//PEDIR POR XML
                        newSong.artist = canciones.get(x).artista;//PEDIR POR XML
                        newSong.lyrics=canciones.get(x).letra;
                        newSong.genre = canciones.get(x).genero;

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
        if (result.isPresent()) {
            String amigo = result.get();
            String enviador = LoginController.usuario;
            boolean aceptado = XML_parser.sendFriendRequest(enviador, amigo);
            if (aceptado) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Friend");
                alert.setHeaderText(null);
                alert.setContentText("You have a new friend yay");
                alert.showAndWait();
                LoginController.friends.add(amigo);
                addFriendList();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Friend");
                alert.setHeaderText(null);
                alert.setContentText("Nobody likes you");
                alert.showAndWait();
            }
        }
    
    
    }
    
    private boolean checkRecommended (Metadata selected) {
        return selected.title.equals("Recommended");
    }
    
    @FXML
    void sliderChanged (MouseEvent event) {
        OdysseyPlayer.getInstance().forward((int) songSlider.getValue());
    }

    @FXML
    private void openViz () {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Viz.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Visualizer");
            stage.setScene(new Scene(root1, 400, 300));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void addFriendList () {
        friends.clear();;
    ArrayList<String> lista = LoginController.friends;
    int x = 0;
            while(x<lista.size()) {
                Label label = new Label(lista.get(x));
                friends.add(label);
                x++;
            }
        
//        String amigo = "Isaac";
//        String amigo1 = "Kenneth";
//        String amigo2 = "Dario";
//        String amigo3 = "Roger";
//
//        friends.add(new Label(amigo));
//        friends.add(new Label(amigo1));
//        friends.add(new Label(amigo2));
//        friends.add(new Label(amigo3));
        
        //friendsList.getItems().addAll(friends);
        friendsList.setItems(friends);
        
        System.out.println("AGREGAR");
        
    }
    
    
}
