package data;

import Model.dataStore;

public class readerThread extends Thread {

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
