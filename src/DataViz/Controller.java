package DataViz;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;


public class Controller {

    @FXML
    private Button startButton = new Button();

    @FXML
    private ImageView sensorFront;

    @FXML
    private ImageView sensorBack;

    @FXML
    private ImageView sensorSide;

    @FXML
    private ImageView car;

    @FXML
    private Text valueFront;

    @FXML
    private Text valueBack;

    @FXML
    private Text valueSide;

    private Image green = new Image("DataViz/Green.png");
    private Image red = new Image("DataViz/Red.png");
    private Image yellow = new Image("DataViz/Yellow.png");
    




}
