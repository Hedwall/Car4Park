package data;

import Model.dataStore;
import jssc.SerialPortException;

public class readerThread extends Thread {

    dataStore carVal;
    SerialConnection virtue;

    public readerThread(dataStore carVal, SerialConnection virtue) {
        this.carVal = carVal;
        this.virtue = virtue;
    }

    @Override
    public void run(){
        try {
            virtue.run(carVal);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

}
