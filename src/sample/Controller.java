package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Controller {

    @FXML
    private Button startButton = new Button();

    @FXML
    private Button stopButton = new Button();

    @FXML
    private ImageView sensorUS1;

    private Image red = new Image("sample/Red.png");

    public void changeGreen (){
       sensorUS1.setImage(red);
    }


}
