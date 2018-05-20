package DataViz;

import Model.dataStore;
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
        FXMLLoader window = new FXMLLoader((getClass().getResource("Trial.fxml")));
        Parent root = window.load();
        Controller apply = (Controller) window.getController();
        apply.setCarVal(carVal);
        primaryStage.setTitle("Car4Park");
        primaryStage.setScene(new Scene(root, 600, 350));
        primaryStage.show();

        Thread t2 = new Thread(new sensorThread(carVal,apply));
        t2.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
