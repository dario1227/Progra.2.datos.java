package view;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import player.OdysseyPlayer;
import util.CustomSliderSkin;

import java.net.URL;
import java.util.ResourceBundle;

public class VolumePopupController implements Initializable {
    
    @FXML
    private Slider volumeSlider;
    @FXML
    private Region frontVolumeTrack;
    @FXML
    private Label volumeLabel;
    @FXML
    private Pane muteButton;
    @FXML
    private Pane mutedButton;
    
    @Override
    public void initialize (URL location, ResourceBundle resources) {
        try {
    
            CustomSliderSkin sliderSkin = new CustomSliderSkin(volumeSlider);
            volumeSlider.setSkin(sliderSkin);
            frontVolumeTrack
                    .prefWidthProperty()
                    .bind(
                            volumeSlider
                                    .widthProperty()
                                    .subtract(30)
                                    .multiply(volumeSlider.valueProperty().divide(volumeSlider.maxProperty())));
            volumeSlider
                    .valueProperty()
                    .addListener(
                            (x, y, z) -> {
                                volumeLabel.setText(Integer.toString(z.intValue()));
                            });
            volumeSlider.setOnMousePressed(
                    x -> {
                        if (mutedButton.isVisible()) {
                            muteClick();
                        }
                    });
            
        } catch (Exception ex) {
    
            ex.printStackTrace();
        }
    }
    
    Slider getSlider () {
        return volumeSlider;
    }
    
    @FXML
    private void volumeClick () {
        OdysseyPlayer.getMainController().volumeClick();
    }
    
    @FXML
    private void muteClick () {
        
        PseudoClass muted = PseudoClass.getPseudoClass("muted");
        boolean isMuted = mutedButton.isVisible();
        muteButton.setVisible(isMuted);
        mutedButton.setVisible(! isMuted);
        volumeSlider.pseudoClassStateChanged(muted, ! isMuted);
        frontVolumeTrack.pseudoClassStateChanged(muted, ! isMuted);
        volumeLabel.pseudoClassStateChanged(muted, ! isMuted);
        OdysseyPlayer.mute(isMuted);
    }
}
