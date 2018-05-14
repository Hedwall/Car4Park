package DataViz;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.util.Random;

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
    @FXML
    private Button readingButton;

    private Image green = new Image("DataViz/Green.png");
    private Image red = new Image("DataViz/Red.png");
    private Image yellow = new Image("DataViz/Yellow.png");


    public void startReading() {


    }
    public void testButton()  {
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {

            int testVal = rand.nextInt(65);

            if (testVal < 10) {
                testVal += rand.nextInt(15);
                setSensorBack(testVal);
            }else if (testVal <25) {
                setSensorFront(testVal);
            }else{
                testVal += rand.nextInt(15)-20;
                setSensorSide(testVal);
            }
        }
    }

    public void setDirection(int value){

    }

    public void setSensorFront(int value) {
        String text = ""+value;

        if (value > 0 && value < 10 ){
            sensorFront.setImage(red);
            valueFront.setFill(Color.RED);
        }else if (value > 10 && value < 20){
            sensorFront.setImage(yellow);
            valueFront.setFill(Color.YELLOW);
        }else if(value > 20){
            sensorFront.setImage(green);
            valueFront.setFill(Color.GREEN);
        }
        valueFront.setText(text);
    }

    public void setSensorBack(int value) {
        String text = ""+value;
        if (value > 0 && value < 10 ){
            sensorBack.setImage(red);
            valueBack.setFill(Color.RED);
        }else if (value > 10 && value < 20){
            sensorBack.setImage(yellow);
            valueBack.setFill(Color.YELLOW);
        }else if(value > 20){
            sensorBack.setImage(green);
            valueBack.setFill(Color.GREEN);
        }
        valueBack.setText(text);


    }

    public void setSensorSide(int value) {
        String text = ""+value;
        if (value > 0 && value < 10 ){
            sensorSide.setImage(red);
        }else if (value > 10 && value < 20){
            sensorSide.setImage(yellow);
        }else if(value > 20){
            sensorSide.setImage(green);
        }
        valueSide.setText(text);


    }


}
