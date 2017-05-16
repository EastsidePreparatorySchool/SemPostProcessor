/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SEMPostProcessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

/**
 * FXML Controller class
 *
 * @author jfellows
 */
public class GalleryLayoutController implements Initializable {

    @FXML
    TilePane tile_pane;

    /**
     * Initializes the controller class.
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

        ImageView imageView = null;
        imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(DEFAULT_THUMBNAIL_WIDTH);

        return imageView;
    }

}
