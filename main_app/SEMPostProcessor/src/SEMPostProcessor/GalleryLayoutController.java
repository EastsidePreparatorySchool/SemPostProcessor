/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SEMPostProcessor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author jfellows
 */
public class GalleryLayoutController implements Initializable {

    @FXML
    TilePane tile_pane;

    @FXML
    HBox image_details;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image testImage = new Image("http://eskipaper.com/images/california-san-francisco-bridge-1.jpg");
        for (int i = 0; i < 10; i++) {
            tile_pane.getChildren().add(createTile(testImage));
        }

    }

    private ImageView createTile(final Image image) {
        int DEFAULT_THUMBNAIL_WIDTH = 300;

        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(DEFAULT_THUMBNAIL_WIDTH);

        imageView.setOnMouseClicked((e) -> {
            image_details.getChildren().setAll(new Text(imageView.toString())); // demonstrating that I can alter the image detail area when an image is selected
        });

        return imageView;
    }

}
