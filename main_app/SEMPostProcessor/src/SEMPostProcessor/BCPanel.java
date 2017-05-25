/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SEMPostProcessor;

import SEMPostProcessor.resources.Brightness;
import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author cadenkeese
 */
/*
<BorderPane xmlns="http://javafx.com/javafx/8.0.111" xmlns:fx="http://javafx.com/fxml/1">
   <top>
      <VBox prefWidth="300.0" BorderPane.alignment="CENTER">
         <children>
            <Text strokeType="OUTSIDE" strokeWidth="0.0" text="Brightness" wrappingWidth="162.4173583984375">
               <font>
                  <Font name="Josefin Sans" size="40.0" />
               </font>
               <VBox.margin>
                  <Insets bottom="8.0" left="10.0" right="10.0" top="10.0" />
               </VBox.margin>
            </Text>
            <Separator prefWidth="200.0" />
            <HBox prefHeight="40.0" prefWidth="200.0">
               <children>
                  <MaterialDesignIconView glyphName="WHITE_BALANCE_SUNNY" size="20">
                     <HBox.margin>
                        <Insets left="10.0" top="6.0" />
                     </HBox.margin>
                  </MaterialDesignIconView>
                  <JFXSlider prefHeight="16.0" prefWidth="203.0">
                     <HBox.margin>
                        <Insets bottom="5.0" left="15.0" top="5.0" />
                     </HBox.margin>
                  </JFXSlider>
                  <MaterialDesignIconView glyphName="WHITE_BALANCE_SUNNY" size="30">
                     <HBox.margin>
                        <Insets bottom="2.0" left="15.0" />
                     </HBox.margin>
                  </MaterialDesignIconView>
               </children>
               <VBox.margin>
                  <Insets top="20.0" />
               </VBox.margin>
            </HBox>
         </children>
      </VBox>
   </top>
   <bottom>
      <JFXButton ripplerFill="#ffdd71" style="-fx-background-color: #ffab40;" text="Button" BorderPane.alignment="CENTER_RIGHT">
         <BorderPane.margin>
            <Insets bottom="20.0" right="20.0" />
         </BorderPane.margin>
      </JFXButton>
   </bottom>
</BorderPane>
 */
public class BCPanel extends BorderPane {

    private VBox sideVContainer = new VBox();
    private Text sideContainerTitle = new Text();
    private Separator sideSeparator = new Separator();
    private HBox sliderHBox = new HBox();
    private MaterialDesignIconView sideSmallIcon = new MaterialDesignIconView();
    private JFXSlider sideSlider = new JFXSlider();
    private MaterialDesignIconView sideLargeIcon = new MaterialDesignIconView();
    public JFXButton save = new JFXButton();
    private String type;
    private BufferedImage img;
    private ImageView imgView;
    private BCeventHandle handler = new BCeventHandle();
    //VVV call this at controller initialization VVV

    public void init() {

        //SETTING OPTIONS
        if (this.type.equals("Brightness")) {
            setLargeIconOptions("WHITE_BALANCE_SUNNY", new Insets(2, 0, 0, 15));
            setSmallIconOptions("WHITE_BALANCE_SUNNY", new Insets(8, 0, 0, 10));

            //slider options
            setSliderOptions(203.0, 16.0, 0, 100, 50, new Insets(10, 0, 5, 15));
        } else if (this.type.equals("Contrast")) {
            setLargeIconOptions("CONTRAST_CIRCLE", new Insets(2, 0, 0, 15));
            setSmallIconOptions("CONTRAST_CIRCLE", new Insets(8, 0, 0, 10));
            //slider options
            setSliderOptions(203.0, 16.0, 0, 100, 50, new Insets(10, 0, 5, 15));
        }
            VBox.setMargin(sliderHBox, new Insets(25, 0, 0, 0));
            //sets width of slider that separates text from slier
            sideSeparator.setPrefWidth(200.0);
            //panel title options
            setTitleOptions(this.type, Font.font("Josefin Sans", 40.0), new Insets(10, 10, 10, 8));
        
        //save button options adjusted
        setSaveButtonOptions("Save", "#ffdd71", "#ffab40", Pos.CENTER_RIGHT, new Insets(0, 20, 20, 0));
        //build side panel
        sliderHBox.setPrefSize(200, 40);
        sliderHBox.getChildren().addAll(sideSmallIcon, sideSlider, sideLargeIcon);

        sideVContainer.getChildren().addAll(sideContainerTitle, sideSeparator, sliderHBox, save);
        sideVContainer.setPrefSize(300.0, 80.0);
        this.setTop(sideVContainer);
        this.setBottom(save);
        //SETTING HANDLERS

        //http://docs.oracle.com/javafx/2/ui_controls/slider.htm
        //on change method
        /*
        sideSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
        });
         */
        
    }

    public BCPanel(String type) {
        super();
        this.type = type;
    }

    //helper functions 
    private void setLargeIconOptions(String glyphName, Insets margin) {
        sideLargeIcon.setGlyphName(glyphName);
        sideLargeIcon.setGlyphSize(30);
        HBox.setMargin(sideLargeIcon, margin);
    }

    private void setSmallIconOptions(String glyphName, Insets margin) {
        sideSmallIcon.setGlyphName(glyphName);
        sideSmallIcon.setGlyphSize(20);
        HBox.setMargin(sideSmallIcon, margin);
    }

    private void setSliderOptions(double width, double height, double min, double max, double initValue, Insets margin) {
        sideSlider.setPrefSize(width, height);
        sideSlider.setMax(max);
        sideSlider.setMin(min);
        sideSlider.setValue(initValue);
        sideSlider.getStyleClass().add("jfx-slider-style");
        HBox.setMargin(sideSlider, margin);
    }

    private void setTitleOptions(String text, Font font, Insets margin) {
        sideContainerTitle.setStrokeType(StrokeType.OUTSIDE);
        sideContainerTitle.setStrokeWidth(0.0);
        sideContainerTitle.setText(text);
        //sideContainerTitle.setWrappingWidth(162.4173583984375);
        sideContainerTitle.setFont(font);
        //System.out.println(sideContainerTitle.getStyleClass());
        VBox.setMargin(sideContainerTitle, margin);
    }

    private void setSaveButtonOptions(String text, String rippleColorHex, String backgroundColorHex, Pos position, Insets margin) {
        save.setText(text);
        save.setRipplerFill(Color.web(rippleColorHex));
        save.setStyle("-fx-background-color: " + backgroundColorHex);
        save.setFont(Font.font(15));
        save.setPrefSize(100, 40);
        BorderPane.setAlignment(save, position);
        BorderPane.setMargin(save, margin);
    }
    
    public void setImageView(ImageView img){
        handler.setImageView(img);
    }
    
    public void setBufferedImage(BufferedImage img){
        handler.setBufferedImage(img);
    }
   
    public BufferedImage getBufferedImage(){
        return handler.getBufferedImage();
    }
    
    private class BCeventHandle implements EventHandler<MouseEvent>{
            private BufferedImage img;
             private ImageView imgView;
            @Override
            public void handle(MouseEvent e) {
                BufferedImage changed = Brightness.changeBrightness(img,(int) Math.round(sideSlider.getValue()));
                Image changedImg = SwingFXUtils.toFXImage(changed, null );
                this.imgView.setImage(changedImg);
                System.out.println(sideContainerTitle.getText() + ": " + sideSlider.getValue());
            }
            public void setBufferedImage(BufferedImage img){
                this.img = img;
            }
            public BufferedImage getBufferedImage(){
             return this.img;
            }
            public ImageView getImageView(){
                return this.imgView;
            }
            public void setImageView(ImageView img){
                this.imgView =  img;
            }
            
     }
}

