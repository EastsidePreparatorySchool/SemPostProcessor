/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SEMPostProcessor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author jfellows
 */
public class Controller implements Initializable {

    @FXML
    private ImageView image_view;
    
    @FXML
    private VBox left;

    void updateImage(Image image) {
        image_view.setImage(image);
    }

    @FXML
    private StackPane right;
    
    private BCPanel brightness = new BCPanel("Brightness");
    private BCPanel contrast = new BCPanel("Contrast");
    
    @FXML
    protected void cropRotateAction(ActionEvent event) {
        updateImage(new Image("http://eskipaper.com/images/california-san-francisco-bridge-1.jpg"));
    }

    @FXML
    protected void brightnessAction(ActionEvent event) {
        right.getChildren().setAll(brightness);
    }

    @FXML
    protected void contrastAction(ActionEvent event) {
        right.getChildren().setAll(contrast);
    }

    @FXML
    protected void autoAction(ActionEvent event) {
        right.getChildren().setAll((new Text("Auto adjust button pressed")));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        image_view.fitWidthProperty().bind(Main.getStage().widthProperty().subtract(left.widthProperty().add(right.widthProperty())));
        brightness.init();
        contrast.init();
    }

}
