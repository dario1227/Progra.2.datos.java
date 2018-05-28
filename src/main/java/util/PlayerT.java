package util;


import XML.XML_parser;
import com.Ostermiller.util.CircularByteBuffer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;


public class PlayerT extends Thread {
    
    public DoubleProperty currentPercent = new SimpleDoubleProperty(0);
    private String filenameRequest;
    private String initialChunk;
    private boolean paused = true;
    private int pausedChunk;
    private int bufferSize = 983040;
    
    public PlayerT (String filenameRequest, String initialChunk) {
        this.filenameRequest = filenameRequest;
        this.initialChunk = initialChunk;
    }
    
    @Override
    public void run () {
        paused = false;
        
        try {
            
            byte[] rawBytes = XML_parser.get_chunk_bytes(filenameRequest, initialChunk);
            
            CircularByteBuffer buffer = new CircularByteBuffer(bufferSize);
            if (rawBytes != null) {
                buffer.getOutputStream().write(rawBytes);
            }
            
            InputStream stream = buffer.getInputStream();
            
            
            Streamer streaming = new Streamer(buffer.getOutputStream(), this.filenameRequest, initialChunk, Integer.parseInt(initialChunk));
            streaming.start();
            
            AudioInputStream in = AudioSystem.getAudioInputStream(stream);
            AudioInputStream din = null;
            AudioFormat baseFormat = in.getFormat();
            AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false);
            din = AudioSystem.getAudioInputStream(decodedFormat, in);
            // Play now.
            rawplay(decodedFormat, din, streaming);
            in.close();
            
            this.pausedChunk = streaming.pause();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void rawplay (AudioFormat targetFormat, AudioInputStream din, Streamer stream) throws IOException, LineUnavailableException {
        byte[] data = new byte[4096];
        SourceDataLine line = getLine(targetFormat);
        if (line != null) {
            // Start
            line.start();
            int nBytesRead = 0, nBytesWritten = 0;
            while (nBytesRead != - 1 && ! paused) {
                nBytesRead = din.read(data, 0, data.length);
                if (nBytesRead != - 1) {
                    nBytesWritten = line.write(data, 0, nBytesRead);
                }
                currentPercent.setValue(100 * (stream.getChunk() - 2) / 50);
                
            }
            // Stop
            line.drain();
            line.stop();
            line.close();
            din.close();
        }
    }
    
    private SourceDataLine getLine (AudioFormat audioFormat) throws LineUnavailableException {
        SourceDataLine res = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        res = (SourceDataLine) AudioSystem.getLine(info);
        res.open(audioFormat);
        return res;
    }
    
    public int pause () {
        this.paused = true;
        while (isAlive()) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.pausedChunk - 2;
    }
    
}