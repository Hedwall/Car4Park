package data;

import java.util.logging.Level;
import java.util.logging.Logger;
import Model.dataStore;
import jssc.SerialPort;
import jssc.SerialPortException;

public class SerialConnection {
   private static boolean conn;

    public void run(dataStore carVal){
        SerialPort serialPort = new SerialPort("COM3");
        //SerialPort serialPort = new SerialPort("/dev/tty.usbmodem1441");
        try {
            System.out.println("Port opened: " + serialPort.openPort());
            conn = true;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SerialConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
            byte[] buffer;
            System.out.println("Params setted: " + serialPort.setParams(9600, 8, 1, 0));

            serialPort.addEventListener(new SerialListener(serialPort, carVal));
            /*conn = false;
            buffer = serialPort.readBytes(25);
            String s = new String(buffer);
            System.out.println(s);
            while(true){
                buffer = serialPort.readBytes(35);
                s = new String(buffer);
                System.out.println(s);
                System.out.println("\"Hello World!!!\" successfully writen to port: " + serialPort.writeBytes("SerialConnection".getBytes()));
                System.out.println("Port closed: " + serialPort.closePort());

            }*/
        }
        catch (SerialPortException ex){
            System.out.println(ex);
        }
    }
}
