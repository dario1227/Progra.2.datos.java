package model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.images.Artwork;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Metadata extends RecursiveTreeObject<Metadata> {
    
    public String title = "";
    public String artist = "";
    public String album = "";
    public String genre = "";
    public String lyrics = "Blah, Blah, Blah, Blah";
    public String fullpath = "";
    public String filename = "";
    
    public Artwork cover;
    
    
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
            try {
                if (lyrics.equals("")) {
                    addLyrics();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    
            if (tag.getFirstArtwork() != null) {
                cover = tag.getFirstArtwork();
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
    
    public void addLyrics () {
        String BASE_URL = "http://api.chartlyrics.com/apiv1.asmx/SearchLyricDirect";
        
        try {
            String parameters = "?artist=" + URLEncoder.encode(artist, "UTF-8") + "&song=" + URLEncoder.encode(title, "UTF-8");
            URL url = new URL(BASE_URL + parameters);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            
            StringBuffer content = new StringBuffer();
            
            int size;
            char[] buf = new char[4096];
            while ((size = in.read(buf)) != - 1) {
                content.append(new String(buf, 0, size));
            }
            in.close();
            
            Document document = DocumentHelper.parseText(content.toString());
            Element root = document.getRootElement();
            String lyrics = root.elementIterator("Lyric").next().getText();
            this.lyrics = lyrics;
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}


