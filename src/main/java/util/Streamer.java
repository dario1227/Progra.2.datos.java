package util;

import XML.XML_parser;

import java.io.OutputStream;


public class Streamer extends Thread {
    
    private static Streamer instance;
    
    private Streamer () {
    }
    
    public static Streamer getInstance () {
        if (instance == null) {
            instance = new Streamer();
        }
        return instance;
    }
    
    private final int totalChunks = 50;
    OutputStream stream;
    String request;
    String chunkNumber;
    private Integer chunk;
    private boolean paused = false;
    
    public Streamer (OutputStream stream, String request, String chunkNumber, Integer initialChunk) {
        this.stream = stream;
        this.request = request;
        this.chunkNumber = chunkNumber;
        this.chunk = initialChunk;
    }
    
    @Override
    public void run () {
        while ((this.chunk < this.totalChunks) && ! paused) {
            this.chunkNumber = this.chunk.toString();
            
            System.out.println("WHILE REQUEST: " + request);
            System.out.println("WHILE CHUNKNUMBER: " + chunkNumber);
            
            try {
                
                byte[] decodedAudio = XML_parser.get_chunk_bytes(request, chunkNumber);
                if (! paused) {
                    assert decodedAudio != null;
                    stream.write(decodedAudio);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            chunk++;
        }
    
        instance = null;
    }
    
    public int pause () {
        this.paused = true;
        return chunk;
    }
    
    public int getChunk () {
        return chunk;
    }
}
