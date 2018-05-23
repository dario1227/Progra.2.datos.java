package util;

import model.Song;

public interface SubView {
    
    void scroll (char letter);
    
    void play ();
    
    Song getSelectedSong ();
}
