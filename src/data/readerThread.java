package data;

import DataViz.Model.dataStore;
import jssc.SerialPort;

public class readerThread implements Runnable {

    dataStore carVal;

    public readerThread(dataStore carVal) {
        this.carVal = carVal;
    }

    @Override
    public void run(){
        SerialConnection virtue = new SerialConnection();
        virtue.run(carVal);
    }

}
