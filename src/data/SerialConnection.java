package data;

import java.util.logging.Level;
import java.util.logging.Logger;
import Model.dataStore;
import jssc.SerialPort;
import jssc.SerialPortException;

public class SerialConnection {
   private static boolean conn;
   private SerialPort serialPort = new SerialPort("COM3");
   private SerialPort serialPort0 = new SerialPort("/dev/ttyAMA0");
   private SerialPort serialPort1 = new SerialPort("/dev/ttyAMA1");

    public SerialPort getSerialPort() {
        return serialPort;
    }

    public void run(dataStore carVal) throws SerialPortException {

        try{
            serialPort0.openPort();
            serialPort = serialPort0;
        } catch (SerialPortException e){
            serialPort1.openPort();
            serialPort = serialPort1;
        }

        try {
            System.out.println("Port opened: " + serialPort.openPort());
            conn = true;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SerialConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
            byte[] buffer;


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
