/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenge_1;

import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author cadenkeese
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private JFXHamburger ham1;
    @FXML
    private JFXDrawer toolbar;
    @FXML
    private StackPane container;
    @FXML
    private VBox menuContainer;
    @FXML
    private JFXDrawer panel;
    @FXML
    private JFXButton brightness;
    @FXML
    private JFXButton contrast;
    
    private VBox btn_container = new VBox();
    private JFXButton btn_import = new JFXButton();
    private JFXButton btn_export = new JFXButton();
    private Separator btn_sep = new Separator();
    
   private BorderPane borderContainer = new BorderPane();
   private VBox sideVContainer = new VBox();
   private Text sideContainerTitle = new Text();
   private Separator sideSeparator = new Separator();
   private HBox sliderHBox = new HBox();
   private MaterialDesignIconView sideSmallIcon = new MaterialDesignIconView();
   private SliderContainer sideSlider = new SliderContainer();
   private MaterialDesignIconView sideLargeIcon = new MaterialDesignIconView();
   private JFXButton save = new JFXButton();
 /*   
 <VBox prefWidth="300.0" xmlns="http://javafx.com/javafx/8.0.111" xmlns:fx="http://javafx.com/fxml/1">
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
    
    
    Insets(double top, double right, double bottom, double left)
   */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_import.setText("Import");
        btn_export.setText("Export");
        btn_import.setPrefSize(100.0, 39.0);
        btn_export.setPrefSize(100.0, 39.0);
        btn_sep.setPrefWidth(200.0);
        btn_container.getChildren().addAll(btn_import, btn_sep, btn_export);
        toolbar.setSidePane(btn_container);
        
        sideLargeIcon.setGlyphName("WHITE_BALANCE_SUNNY");
        sideLargeIcon.setGlyphSize(30);
        HBox.setMargin(sideLargeIcon, new Insets(2,0,0,15));
        
        sideSlider.setPrefSize(203.0, 16.0);
        sideSlider.setMin(-100);
        sideSlider.setValue(0);
        sideSlider.getStyleClass().add("jfx-slider-style");
        HBox.setMargin(sideSlider, new Insets(10,0,5,15));
        
        sideSmallIcon.setGlyphName("WHITE_BALANCE_SUNNY");
        sideSmallIcon.setGlyphSize(20);
        HBox.setMargin(sideSmallIcon, new Insets(8,0,0,10));
        
        sliderHBox.setPrefSize(200, 40);
        VBox.setMargin(sliderHBox, new Insets(25,0,0,0));
        
        sideSeparator.setPrefWidth(200.0);
        
        sideContainerTitle.setStrokeType(StrokeType.OUTSIDE);
        sideContainerTitle.setStrokeWidth(0.0);
        sideContainerTitle.setText("Brightness");
        //sideContainerTitle.setWrappingWidth(162.4173583984375);
        sideContainerTitle.setFont(Font.font("Josefin Sans", 40.0));
        //System.out.println(sideContainerTitle.getStyleClass());
        VBox.setMargin(sideContainerTitle, new Insets(10,10,10,8));
        
        sideVContainer.setPrefSize(300.0, 80.0);
   
        panel.setDefaultDrawerSize(300.0);
        sliderHBox.getChildren().addAll(sideSmallIcon, sideSlider,sideLargeIcon);
        
        save.setText("Save");
        save.setRipplerFill(Color.web("#ffdd71"));
        save.setStyle("-fx-background-color: #ffab40");
        BorderPane.setAlignment(save, Pos.CENTER_RIGHT);
        BorderPane.setMargin(save, new Insets(0,20,20,0));
        
        sideVContainer.getChildren().addAll(sideContainerTitle, sideSeparator, sliderHBox, save);
        borderContainer.setTop(sideVContainer);
        borderContainer.setBottom(save);
        
        panel.setSidePane(borderContainer);
        
        HamburgerBasicCloseTransition burgerTask = new HamburgerBasicCloseTransition(ham1);
        burgerTask.setRate(-1);
        
        
        
        
        ham1.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            burgerTask.setRate(burgerTask.getRate() * -1);
            burgerTask.play();
            ObservableList<Node> initList = container.getChildren();
            //toolbar.setMaxSize(20.0, 20.0);
            if (toolbar.isShown()) {
                toolbar.close();
                ObservableList<Node> list = container.getChildren();
                Node temp = list.get(0);
                list.remove(0);
                list.add(temp);
            } else {
                ObservableList<Node> list = container.getChildren();
                if(list.indexOf(menuContainer) == 0){
                    Node temp = list.get(0);
                    list.remove(0);
                    list.add(temp);
                }
                toolbar.open();
                
            }

        });
        
        brightness.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            if (!panel.isShown()){
                panel.open();
            }
            if(!sideContainerTitle.getText().equals("Brightness")){
                sideContainerTitle.setText("Brightness");
                sideLargeIcon.setGlyphName("WHITE_BALANCE_SUNNY");
                sideSmallIcon.setGlyphName("WHITE_BALANCE_SUNNY");
                sideSlider.setMin(-100);
                sideSlider.setValue(0);
                panel.open();
            }
        });
        //http://docs.oracle.com/javafx/2/ui_controls/slider.htm
        //on change method
        /*
        sideSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
        });
        */
        
    
         contrast.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            if (!panel.isShown()){
                panel.open();
            }
            if(!sideContainerTitle.getText().equals("Contrast")){
                sideContainerTitle.setText("Contrast");
                sideLargeIcon.setGlyphName("CONTRAST_CIRCLE");
                sideSmallIcon.setGlyphName("CONTRAST_CIRCLE");
                sideSlider.setMin(0);
                sideSlider.setValue(50);
                panel.open();
            }
        });
        save.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            System.out.println(sideContainerTitle.getText() + ": " + sideSlider.getValue());
        });
        

    }

}
