package data;

import Model.dataStore;
import jssc.SerialPortException;

public class readerThread extends Thread {

    dataStore carVal;
    SerialConnection virtue = new SerialConnection();

    public readerThread(dataStore carVal) {
        this.carVal = carVal;
    }

    public SerialConnection getVirtue() {
        return virtue;
    }

    public void setVirtue(SerialConnection virtue) {
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
