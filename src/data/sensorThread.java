package data;

import DataViz.Controller;
import Model.dataStore;

public class sensorThread extends Thread {

    Controller apply;
    dataStore carVal;


    public sensorThread(dataStore carVal, Controller apply) {
        this.carVal = carVal;
        this.apply = apply;
    }

    @Override
    public void run() {

        while (true) {
            apply.setSensorFront(carVal.getFront());
            try {
                this.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            apply.setSensorSide(carVal.getSide());
            try {
                this.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            apply.setSensorBack(carVal.getBack());
            try {
                this.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}