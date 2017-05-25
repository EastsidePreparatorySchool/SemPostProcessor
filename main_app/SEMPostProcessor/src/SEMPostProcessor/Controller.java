/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SEMPostProcessor;

import SEMPostProcessor.resources.Brightness;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;

/**
 *
 * @author jfellows
 */
public class Controller implements Initializable {
    
    @FXML
    private ImageView image_view;

    void updateImage(Image image) {
        image_view.setImage(image);
    }

    @FXML
    private StackPane right;
    private BCPanel brightness = new BCPanel("Brightness");
    private BCPanel contrast = new BCPanel("Contrast");
    private BufferedImage imageToEdit;
    
    @FXML
    protected void cropRotateAction(ActionEvent event) {
        // A little note about some of what I've done
        TextArea ta = new TextArea("Crop and rotate button pressed. Note how image can change. There is an updateImage method that can be called from the event handlers for button presses. Additionally, these events can create JavaFX objects in the right panel. This provides a framework to link Caden's sliders and Patrick's image editing.");
        ta.setWrapText(true);
        ta.setEditable(false);
        //right.getChildren().setAll(ta);
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
        String mac = "/Users/cadenkeese/Downloads/grayscale.png";
        String pc = "";
        imageToEdit = loadImage(mac);
       // updateImage(SwingFXUtils.toFXImage(imageToEdit, null ));
        brightness.save.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
           imageToEdit = Brightness.changeBrightness(imageToEdit, 100);
            Image image = SwingFXUtils.toFXImage(imageToEdit, null);
            updateImage(image);
            //updateImage(SwingFXUtils.toFXImage(Brightness.changeBrightness(imageToEdit, 20), null ));
        });
        brightness.setBufferedImage(imageToEdit);
        brightness.setImageView(image_view);
        brightness.init();
        contrast.init();
        
    }
    
    private static BufferedImage loadImage(String filename) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(filename));
            return image;
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("failed to load image.");
        }
        return null;
    }
    
}
