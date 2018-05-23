package view;

import XML.XML_parser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.Library;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import util.ImportMusicTask;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Paths;

import static model.Library.isSupportedFileType;

/**
 * Dialog to import music library.
 */
public class ImportMusicDialogController {
    @FXML
    private Label label;
    @FXML
    private Button importMusicButton;
    @FXML
    private ProgressBar progressBar;
    
    private Stage dialogStage;
    private boolean musicImported = false;
    
    private String songName = "";
    private String artistName = "";
    private String extLyrics = "";
    
    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage (Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    /**
     * Returns true if the music library was imported successfully, false otherwise.
     *
     * @return
     */
    public boolean isMusicImported () {
        return musicImported;
    }
    
    private void addlyrics () {
        
        String BASE_URL = "http://api.chartlyrics.com/apiv1.asmx/SearchLyricDirect";
        
        try {
            String parameters =
                "?artist="
                    + URLEncoder.encode(artistName, "UTF-8")
                    + "&song="
                    + URLEncoder.encode(songName, "UTF-8");
            URL url = new URL(BASE_URL + parameters);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            
            StringBuffer content = new StringBuffer();
            
            int size;
            char[] buf = new char[4096];
            while ((size = in.read(buf)) != - 1) {
                content.append(new String(buf, 0, size));
            }
            in.close();
            
            extLyrics = content.toString();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void uploadServer (File file) {
        
        try {
            AudioFile audioFile = AudioFileIO.read(file);
            Tag tag = audioFile.getTag();
    
            String fullpath = "", path = "", album = "", artist = "", lyrics = "", song = "";
    
            path = file.getName();
            fullpath = file.getAbsolutePath();
            album = tag.getFirst(FieldKey.ALBUM);
            artist = tag.getFirst(FieldKey.ARTIST);
            song = tag.getFirst(FieldKey.TITLE);
    
            if (! song.equals("")) {
                songName = song;
            }
    
            if (! artist.equals("")) {
                artistName = artist;
            }
            lyrics = tag.getFirst(FieldKey.LYRICS);
    
            if (lyrics.equals("")) {
                addlyrics();
                lyrics = extLyrics;
            }
    
            if (fullpath != "" || path != "") {
                XML_parser.getXML_Archive(fullpath, path, lyrics, album, artist);
            }
        } catch (CannotReadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TagException e) {
            e.printStackTrace();
        } catch (ReadOnlyFileException e) {
            e.printStackTrace();
        } catch (InvalidAudioFrameException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleImport () {
        try {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            // Show file explorer.
            String musicDirectory = directoryChooser.showDialog(dialogStage).getPath();
    
            // SERVER!!!!!!!!!!!!!!!!!!!!!!!!
            File directory = new File(Paths.get(musicDirectory).toUri());
            File[] files = directory.listFiles();
    
            assert files != null;
            for (File file : files) {
                if (file.isFile() && isSupportedFileType(file.getName())) {
                    try {
                        uploadServer(file);
                        System.out.println(file.getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
    
            // Creates a task that is used to import the music library.
            ImportMusicTask<Boolean> task =
                new ImportMusicTask<Boolean>() {
                    @Override
                    protected Boolean call () {
                        // Creates library.xml file from user music library.
                        try {
                            Library.importMusic(musicDirectory, this);
                            return true;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return false;
                        }
                    }
                };
            
            // When the task (music importing) ends, the dialog is closed.
            task.setOnSucceeded(
                (x) -> {
                    // Sets the music as imported successfully and closes the dialog.
                    musicImported = true;
                    dialogStage.close();
                });
            
            task.updateProgress(0, 1);
    
            // Retrieves the task progress and adds that to the progress bar.
            progressBar.progressProperty().bind(task.progressProperty());
    
            // Creates a new thread with the import music task and runs it.
            Thread thread = new Thread(task);
            thread.start();
    
            label.setText("Importing music library...");
            // Makes the import music button invisible and the progress bar visible.
            // This happens as soon as the music import task is started.
            importMusicButton.setVisible(false);
            progressBar.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
