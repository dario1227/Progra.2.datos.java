package XML;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;

public class MP3Bytes {
    
    private String filename;
    private Player player;
    private byte[] bytes;
    
    public MP3Bytes (byte[] bytes, String file) {
        this.bytes = bytes;
        this.filename = file;
    }
    
    public void close () {
        if (player != null) {
            player.close();
        }
    }
    
    public boolean isfinish () {
        return player.isComplete();
    }
    
    public void play () {
        try {
            ByteArrayInputStream data = new ByteArrayInputStream(bytes);
            BufferedInputStream bis = new BufferedInputStream(data);
            player = new Player(bis);
        } catch (Exception e) {
            System.out.println("Problem playing file " + filename);
            e.printStackTrace();
        }
        
        new Thread(() -> {
            try {
                player.play();
            } catch (Exception e) {
                System.out.println(e);
            }
        }).start();
        
    }
    
}
