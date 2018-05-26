package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Metadata;

import java.io.IOException;

public class DetailsDialog {
    
    private Stage dialog;
    private FXMLLoader loader;
    
    DetailsDialog () throws IOException {
        loader = new FXMLLoader(getClass().getResource("MP3ID3Tag.fxml"));
        
        dialog = new Stage();
        dialog.initOwner(Main.getmStage());
        dialog.initModality(Modality.WINDOW_MODAL);
        
        Parent window = loader.load();
        
        Scene scene = new Scene(window, 600, 400);
        dialog.setTitle("Details");
        dialog.setScene(scene);
        dialog.setResizable(false);
    }
    
    public Metadata showAndWait (Metadata metadata) {
        MP3ID3TagController controller = loader.getController();
        controller.load(metadata);
        dialog.showAndWait();
        return controller.retrieve();
    }
    
    
}
