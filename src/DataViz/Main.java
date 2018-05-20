package DataViz;

import Model.dataStore;
import data.SerialConnection;
import data.readerThread;
import data.sensorThread;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        dataStore carVal = new dataStore();
        SerialConnection virtue = new SerialConnection();
        FXMLLoader window = new FXMLLoader((getClass().getResource("Trial.fxml")));
        Parent root = window.load();
        Controller apply = (Controller) window.getController();
        apply.setCarVal(carVal);
        primaryStage.setTitle("Car4Park");
        primaryStage.setScene(new Scene(root, 600, 350));
        primaryStage.show();
        Thread t1 = new Thread(new readerThread(carVal, virtue));
        t1.start();
        Thread t2 = new Thread(new sensorThread(carVal,apply));
        t2.start();
        apply.setSerialPort(virtue.getSerialPort());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
