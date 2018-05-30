package util;

import XML.XML_parser;
import javafx.scene.control.Slider;
import model.Metadata;


public class OdysseyPlayer {
    
    private static OdysseyPlayer instance;
    
    /**
     * Hilo unico del reproductor
     */
    PlayerT pThread;
    
    int currentChunk;
    
    Metadata currentSong;
    
    Slider slider;
    
    /**
     * Constructor privado del reproductor
     */
    private OdysseyPlayer () {
    }
    
    /**
     * Singleton para obtener la instancia del reproductor
     *
     * @return la instancia del reproductor
     */
    public static OdysseyPlayer getInstance () {
        if (instance == null) {
            instance = new OdysseyPlayer();
        }
        return instance;
    }
    
    /**
     * Reproduce una cancion
     *
     * @param song  Metadata de la cancion a reproducir
     * @param chunk Bloque desde el cual reproducir la cancion
     */
    public void play (Metadata song, Integer chunk) {
        currentSong = song;
        if (pThread != null && pThread.isAlive()) {
            pause();
        }
        if(chunk==-1){
            chunk =1;
        }
        System.out.println("PLAY CHUNK: " + chunk.toString());
        System.out.println("PLAY FILENAME: " + song.filename);
        
        String sChunk = chunk.toString();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pThread = new PlayerT(currentSong.title, sChunk);
        pThread.currentPercent.addListener((observable, oldValue, newValue) -> slider.adjustValue(newValue.doubleValue()));
        pThread.start();
    }
    
    public void pause () {
        currentChunk = pThread.pause();
    }
    
    public void unpause () {
        play(currentSong, currentChunk);
    }
    
    
    public boolean isPlaying () {
        if (pThread != null) {
            return pThread.isAlive();
        } else {
            return false;
        }
    }
    
    public void setSlider (Slider slider) {
        this.slider = slider;
    }
    
    public void forward (int slider) {
        int chunk = XML_parser.calcule_actual_page(slider / 100);
        play(currentSong, chunk);
    }
    
    
}
