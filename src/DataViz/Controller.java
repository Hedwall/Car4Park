package DataViz;

import Model.dataStore;
import data.SerialConnection;
import data.readerThread;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import jssc.SerialPort;
import jssc.SerialPortException;

import java.util.Random;

public class Controller {

    private SerialPort serialPort;

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
    private Button startParking;
    @FXML
    private Button parallelPark;


    private Image green = new Image("DataViz/Green.png");
    private Image red = new Image("DataViz/Red.png");
    private Image yellow = new Image("DataViz/Yellow.png");
    private dataStore carVal;

    public void setSerialPort(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    public void setCarVal(dataStore carVal) {
        this.carVal = carVal;
    }

    public void setSensorFront(int value) {
        String text = ""+value;


        if ((value >= 0) && (value < 11)){
            if(sensorFront.getImage() == red){
                //does not change the image as it is the right one
            }else {
                sensorFront.setImage(red);
            }
        }else if ((value > 10) && (value < 20)){
            if(sensorFront.getImage() == yellow){
                //does not change the image as it is the right one
            }else {
                sensorFront.setImage(yellow);
            }
        }else if(value > 19){
            if(sensorFront.getImage() == green){
                //does not change the image as it is the right one
            }else {
                sensorFront.setImage(green);
            }
        }
        valueFront.setText(text);
    }

    public void setSensorBack(int value) {
        String text = ""+value;

        if ((value >= 0) && (value < 11)){
            if(sensorBack.getImage() == red){
                //does not change the image as it is the right one
            }else {
                sensorBack.setImage(red);
            }
        }else if ((value > 10) && (value < 20)){
            if(sensorBack.getImage() == yellow){
                //does not change the image as it is the right one
            }else {
                sensorBack.setImage(yellow);
            }
        }else if(value > 19){
            if(sensorBack.getImage() == green){
                //does not change the image as it is the right one
            }else {
                sensorBack.setImage(green);
            }
        }
        valueBack.setText(text);
    }

    public void setSensorSide(int value) {

        String text = ""+value;

        if ((value >= 0) && (value < 11)){
            if(sensorSide.getImage() == red){
                //does not change the image as it is the right one
            }else {
                sensorSide.setImage(red);
            }
        }else if ((value > 10) && (value < 20)){
            if(sensorSide.getImage() == yellow){
                //does not change the image as it is the right one
            }else {
                sensorSide.setImage(yellow);
            }
        }else if(value > 19){
            if(sensorSide.getImage() == green){
                //does not change the image as it is the right one
            }else {
                sensorSide.setImage(green);
            }
        }
        valueSide.setText(text);
    }

    public void startPark( ) {
        String command = "5"+"\n";
        byte[] commands = command.getBytes();
        try {
            serialPort.writeBytes(commands);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    public void parallelPark(){
        String command = "6"+"\n";
        byte[] commands = command.getBytes();
        try {
            serialPort.writeBytes(commands);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

}
