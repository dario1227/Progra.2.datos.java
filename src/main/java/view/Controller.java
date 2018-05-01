package view;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML
    private Button playButton1;

    @FXML
    private FontAwesomeIconView playButtonIcon1;

    @FXML
    private ImageView albumImageView1;

    @FXML
    private Label titleLabel1;

    @FXML
    private Label artistLabel1;

    @FXML
    private ListView<?> GeneticAnswers;

    @FXML
    private Label timeLabel1;

    @FXML
    private ImageView albumImageViewLeft1;

    @FXML
    private ImageView albumImageViewRight1;

    @FXML
    private ImageView trackioLogo1;

    @FXML
    private ProgressBar progressBar1;

    @FXML
    private ToolBar mainToolBar1;

    @FXML
    private TableView<?> trackTableView1;

    @FXML
    private TableColumn<?, ?> titleColumn1;

    @FXML
    private TableColumn<?, ?> authorColumn1;

    @FXML
    private TableColumn<?, ?> albumColumn1;

    @FXML
    private TableColumn<?, ?> yearColumn1;

    @FXML
    void handleCredits(MouseEvent event) {

    }

    @FXML
    void handleNextTrack(MouseEvent event) {

    }

    @FXML
    void handlePrevTrack(MouseEvent event) {

    }

}


