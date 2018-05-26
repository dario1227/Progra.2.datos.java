package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Metadata;

public class TablePages {
    public int pageNumber;
    public int totalSongs;
    public int pages;
    public int pageSize;
    public ObservableList<Metadata> songs = FXCollections.observableArrayList();
    
    
}
