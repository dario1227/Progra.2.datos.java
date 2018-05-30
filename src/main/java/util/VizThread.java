package util;

import com.jfoenix.controls.JFXProgressBar;

public class VizThread extends Thread {
    private OdysseyPlayer player;
    private JFXProgressBar viz;
    
    public VizThread (OdysseyPlayer player, JFXProgressBar bar) {
        this.player = player;
        this.viz = bar;
        
    }
    
    @Override
    public void run () {
        while (isAlive()) {
            double amplitude = player.getAmplitude();
            if (amplitude == - 1) {
                try {
                    sleep(1000 / 60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            
            this.viz.setProgress(amplitude / 100);
            
            try {
                sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
