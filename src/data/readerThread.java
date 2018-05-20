package data;

import Model.dataStore;
import jssc.SerialPortException;

public class readerThread extends Thread {

    dataStore carVal;

    public readerThread(dataStore carVal) {
        this.carVal = carVal;
    }

    @Override
    public void run(){
        SerialConnection virtue = new SerialConnection();
        try {
            virtue.run(carVal);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

}
