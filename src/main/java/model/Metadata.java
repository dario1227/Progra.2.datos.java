package model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import java.io.File;

public class Metadata extends RecursiveTreeObject<Metadata> {
    
    public String title = "";
    public String artist = "";
    public String album = "";
    public String genre = "";
    public String lyrics = "Blah, Blah, Blah, Blah";
    public String fullpath = "";
    public String filename = "";
    
    
    public Metadata () {
    }
    
    public Metadata (String path) {
        
        File file = new File(path);
        
        try {
            
            AudioFile audioFile = AudioFileIO.read(file);
            Tag tag = audioFile.getTag();
            
            album = tag.getFirst(FieldKey.ALBUM);
            artist = tag.getFirst(FieldKey.ARTIST);
            title = tag.getFirst(FieldKey.TITLE);
            if (! tag.getFirst(FieldKey.LYRICS).equals("")) {
                lyrics = tag.getFirst(FieldKey.LYRICS);
            }
            
            genre = tag.getFirst(FieldKey.GENRE);
            lyrics = tag.getFirst(FieldKey.LYRICS);
    
            //FILE IO
            fullpath = file.getAbsolutePath();
            filename = file.getName();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public String getFilename () {
        return filename;
    }
}


