/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenge_1;

import com.jfoenix.controls.JFXSlider;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author cadenkeese
 */
//this class was created as a wrpper for jfxslider so could store info relating to the slider being clicked
public class SliderContainer extends JFXSlider {
    private Boolean isClicked = false;
    public void setClicked (Boolean clicked){
        this.isClicked = clicked;
    }
    public Boolean getClicked() {
        return this.isClicked;
    }
    public SliderContainer(){
        super();
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) ->{
            this.setClicked(true);
        });
        this.addEventHandler(MouseEvent.MOUSE_RELEASED, (e) ->{
            this.setClicked(false);
        });
    }
}
