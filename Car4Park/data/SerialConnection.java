package data;

import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortException;

public class SerialConnection {
	static boolean conn;
	public static void main(String[] args) {
		SerialPort serialPort = new SerialPort("/dev/tty.usbmodem1441");
	        try {
	            System.out.println("Port opened: " + serialPort.openPort());
	            conn = true;
	            try {
	                Thread.sleep(3000);
	            } catch (InterruptedException ex) {
	                Logger.getLogger(SerialConnection.class.getName()).log(Level.SEVERE, null, ex);
	            }
	            byte[] buffer;
	            System.out.println("Params setted: " + serialPort.setParams(9600, 8, 1, 0));
	            
	            serialPort.addEventListener(new SerialListener(serialPort));
	            /*conn = false;
	            buffer = serialPort.readBytes(25);
	            String s = new String(buffer);
	            System.out.println(s);*/
	            while(true){ 
		            /*buffer = serialPort.readBytes(35);
		            s = new String(buffer);
		            System.out.println(s);
		            //System.out.println("\"Hello World!!!\" successfully writen to port: " + serialPort.writeBytes("SerialConnection".getBytes()));
		            //System.out.println("Port closed: " + serialPort.closePort());*/
	            }
	        }    
	        catch (SerialPortException ex){
	            System.out.println(ex);
	        }
	}	    
}

