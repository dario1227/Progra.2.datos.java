package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Metadata;

import java.io.IOException;

public class EditTagsDialog {
    
    private Stage dialog;
    private FXMLLoader loader;
    
    EditTagsDialog () throws IOException {
        loader = new FXMLLoader(getClass().getResource("EditTags.fxml"));
        
        dialog = new Stage();
        dialog.initOwner(Main.getmStage());
        dialog.initModality(Modality.WINDOW_MODAL);
        
        Parent window = loader.load();
        
        Scene scene = new Scene(window, 400, 470);
        dialog.setTitle("Edit Tags");
        dialog.setScene(scene);
        dialog.setResizable(false);
    }
    
    public Metadata showAndWait (Metadata metadata) {
        EditTagsController controller = loader.getController();
        controller.load(metadata);
        dialog.showAndWait();
        return controller.retrieve();
    }
    
    
}
